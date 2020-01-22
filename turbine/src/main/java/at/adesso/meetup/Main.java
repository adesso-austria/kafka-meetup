package at.adesso.meetup;

import org.apache.commons.cli.*;

public class Main {

    // Entry-Point
    public static void main(String[] args) {
        CLIArguments cliArgs = new CLIArguments();

        // parse arguments
        if ( !cliArgs.parseArguments(args) ) showHelpAndQuit();

        // start turbine simulation
        new Turbine( cliArgs.getTurbineID(), cliArgs.getWindparkID(), cliArgs.getKafkaURL() + ":" + cliArgs.getKafkaPORT() );
    }

    // print help message and quit
    public static void showHelpAndQuit() {
        printHelp();
        System.exit(0);
    }

    // print out help text
    private static void printHelp() {
        System.out.println("Usage: java -jar Turbine.jar -k KAFKA_URL:PORT -t TOPIC_NAME -m MODE");
        System.out.println("\t-k <URL:PORT>\tIP address and port of your Kafka broker");
        System.out.println("\t-t <TOPIC>\tThe name of the topic");
        System.out.println("\t-m <MODE>");
        System.out.println("\t\t0\twill run with no problems");
        System.out.println("\t\t1\twill eventually produce problems");
        System.out.println("\t\t2\twill eventually stop working");
    }
}
