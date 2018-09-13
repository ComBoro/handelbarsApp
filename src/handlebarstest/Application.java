package handlebarstest;

import static handlebarstest.FilesHandler.*;
import static handlebarstest.HandlebarsUtility.compile;
import static handlebarstest.HandlebarsUtility.getOutput;
import static handlebarstest.InputHandler.*;


public class Application {

    public static void main(String[] args) {

        proceed(args);

        if (requestingHelp()) {
            System.out.println(getHelpMessage());
            System.exit(0);
        }

        if (!handleJsonFile(get("json"))) {
            System.err.println("Invalid Json File: " + get("json"));
            Runtime.getRuntime().exit(-1);
        }

        if (!handleHbsFile(get("hbs"))) {
            System.err.println("Invalid Hbs File: " + get("hbs"));
            Runtime.getRuntime().exit(-1);
        }

        if (!handleOutputFile(get("output"))) {
            System.err.println("Invalid Output File: " + get("output"));
            Runtime.getRuntime().exit(-1);
        }

        if (compile(getJson(), getHbs())) {
            String output = getOutput();
            if (appendToOutputFile(output)) {
                System.err.println("Failed to append to output file");
            }
            System.out.println(output);
        } else { //Eror
            System.err.println(getOutput());
        }
    }

}