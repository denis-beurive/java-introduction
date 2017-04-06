package com.beurive;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import java.io.FileInputStream;
import java.util.*;



public class Main {

    public static void main(String[] args) {

        try {

            // Parse the command line.
            Options options = new Options();
            options.addOption("i", "input", true, "Path to the input file");
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse( options, args);
            String inputPath = cmd.getOptionValue("i");

        } catch (Exception e) {
            System.out.println("Invalid command line!" + e.getMessage());
        }

    }
}
