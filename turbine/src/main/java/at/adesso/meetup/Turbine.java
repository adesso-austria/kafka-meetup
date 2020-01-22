package at.adesso.meetup;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Codified representation of a wind turbine and some of it's sensors
 * Sends a heartbeat via message to Kafka broker and randomizes sensor data
 * @author Dominik Dorfstetter <dominik.dorfstetter@adesso.at>
 */
public class Turbine implements IntTurbine {
    private String kafkaURL;
    private String turbineID;
    private String windparkID;
    private double lat;
    private double lng;
    private float tempOutside = 10.00F;
    private float tempInside = 55.00F;
    private boolean bats = false;
    private Timer timerOutside = new Timer();
    private Timer timerInside = new Timer();
    private Timer batTimer = new Timer();
    private Timer heartbeat = new Timer();
    private KafkaProducer<String, String> producer;

    public Turbine(String turbineID, String windparkID, String kafka_URL) {
        // set Kafka URL
        setKafkaURL(kafka_URL);

        // initialize Kafka producer
        this.setProducer(this.initializeProducer());

        this.setTurbineID(turbineID);
        this.setWindparkID(windparkID);

        // set position at random
        this.randomizePosition();
        // start sensors
        this.startSensors();
        // start heartbeat
        this.startHeartbeat();
    }

    KafkaProducer<String, String> initializeProducer () {
        Properties props = new Properties();

        props.put("bootstrap.servers", getKafkaURL());
        props.put("acks", "0"); // fire and forget or all
        props.put("retries", 0);
        props.put("batch.size", 1);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 100000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        return new KafkaProducer<String, String>(props);
    }

    @Override
    protected void finalize() throws Throwable {
        // cancel all the timers
        this.timerOutside.cancel();
        this.timerInside.cancel();
        this.batTimer.cancel();
        this.heartbeat.cancel();
        this.producer.close();

        super.finalize();
    }

    /**
     * Start timer that change sensor data over time
     */
    private void startSensors() {
        // --- START: temperature sensors ---
        this.timerInside.schedule(new TimerTask() {
            float prevTemp = 0.0F;

            @Override
            public void run() {
                this.prevTemp = getTempInside();
                float min = this.prevTemp - 0.5F, max = this.prevTemp + 0.5F;
                setTempInside(min + (float)Math.random() * (max - min));
            }
        }, 0, 500);

        this.timerOutside.schedule(new TimerTask() {
            float prevTemp = 0.0F;

            @Override
            public void run() {
                this.prevTemp = getTempOutside();
                float min = this.prevTemp - 0.5F, max = this.prevTemp + 0.5F;
                setTempOutside(min + (float)Math.random() * (max - min));
            }
        }, 0, 500);
        // --- END: temperature sensors ---

        // --- START: bat sensor ---
        this.batTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                setBats(Math.random() < 0.5);
            }
        }, 0, 20000);
        // --- END: bat sensor ---
    }

    /**
     * Start heartbeat of turbine; every second by default
     * Delay by default is 0.5 seconds
     */
    private void startHeartbeat() {
        DecimalFormat df = new DecimalFormat();
        String kafka_key = getWindparkID() + ";" + getTurbineID();

        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);

        this.heartbeat.schedule(new TimerTask() {
            @Override
            public void run() {
                String currentData = df.format(getTempOutside()) + ";" + df.format(getTempInside())
                        + ";" + hasBats() + ";" + getLat() + ";" + getLng();

                producer.send(new ProducerRecord<String, String>("turbine-raw", kafka_key, currentData));
            }
        }, 500, 1000);
    }

    /**
     * Randomize position of wind-turbine
     */
    private void randomizePosition() {
        Random r = new Random();
        double u = r.nextDouble();
        double v = r.nextDouble();

        this.setLat(Math.toDegrees(Math.acos(u*2-1)) - 90);
        this.setLng(360 * v - 180);
    }

    // --- GETTER & SETTER ---
    public void setTempOutside(float tempOutside) {
        this.tempOutside = tempOutside;
    }

    public float getTempOutside() {
        return this.tempOutside;
    }

    public void setTempInside(float tempInside) {
        this.tempInside = tempInside;
    }

    public float getTempInside() {
        return this.tempInside;
    }

    public String getTurbineID() {
        return turbineID;
    }

    public void setTurbineID(String turbineID) {
        if ( turbineID.length() > 0 ) this.turbineID = turbineID;
        else this.turbineID = "invalid";
    }

    public String getWindparkID() {
        return windparkID;
    }

    public void setWindparkID(String windparkID) {
        if ( windparkID.length() > 0 ) this.windparkID = windparkID;
        else this.windparkID = "invalid";
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setBats(boolean hasBats) {
        this.bats = hasBats;
    }

    public boolean hasBats() {
        return bats;
    }

    public KafkaProducer<String, String> getProducer() {
        return producer;
    }

    public void setProducer(KafkaProducer<String, String> producer) {
        this.producer = producer;
    }

    public String getKafkaURL() {
        return kafkaURL;
    }

    public void setKafkaURL(String kafkaURL) {
        this.kafkaURL = kafkaURL;
    }
}
