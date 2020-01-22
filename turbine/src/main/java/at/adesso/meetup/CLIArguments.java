package at.adesso.meetup;

import org.apache.commons.cli.*;

public class CLIArguments {
    private String kafkaURL = "";
    private String kafkaPORT = "";
    private int mode = 0;
    private String windparkID = "";
    private String turbineID = "";

    // --- getter & setter ---
    public void setKafkaPORT(String kafkaPORT) {
        this.kafkaPORT = kafkaPORT;
    }

    public String getKafkaPORT() {
        return kafkaPORT;
    }

    public void setKafkaURL(String kafkaURL) {
        if ( kafkaURL.length() > 0 ) this.kafkaURL = kafkaURL;
    }

    public String getKafkaURL() {
        return kafkaURL;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setWindparkID(String windparkID) {
        if ( windparkID.length() > 0 ) this.windparkID = windparkID;
    }

    public String getWindparkID() {
        return this.windparkID;
    }

    public String getTurbineID() {
        return this.turbineID;
    }

    public void setTurbineID(String turbineID) {
        if ( turbineID.length() > 0 )
            this.turbineID = turbineID;
        else
            this.turbineID = "invalid";
    }

    // --- parse command line arguments ---
    public boolean parseArguments(String[] args) {
        if ( args.length < 8 ) return false;
        Options options = new Options();

        Option url = new Option("k", "kafkaURL", true, "kafka url + port");
        url.setRequired(true);
        options.addOption(url);

        Option windpark = new Option("w", "windparkID", true, "windpark ID");
        windpark.setRequired(true);
        options.addOption(windpark);

        Option turbine = new Option("t", "turbineID", true, "turbine ID");
        turbine.setRequired(true);
        options.addOption(turbine);

        Option mode = new Option("m", "mode", true, "topic");
        mode.setRequired(true);
        options.addOption(mode);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            this.setKafkaURL(cmd.getOptionValue("kafkaURL").trim().split(":")[0]);
            this.setKafkaPORT(cmd.getOptionValue("kafkaURL").trim().split(":")[1]);
            this.setWindparkID(cmd.getOptionValue("windparkID").trim());
            this.setMode(Integer.parseInt(cmd.getOptionValue("mode").trim()));
            this.setTurbineID(cmd.getOptionValue("turbineID").trim());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
