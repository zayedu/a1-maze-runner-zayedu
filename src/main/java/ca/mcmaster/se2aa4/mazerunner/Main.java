package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try{
            if (!(args[0].equals("-i") || args[0].equals("--input"))){
                logger.info("**** You forgot to specify the input with -i or --input");
                logger.info("** End of MazeRunner");
                System.exit(0);
            }
        }catch(Exception e){
            logger.info("****ERROR: No argument given, please specify the input with -i or --input");
            logger.info("**ERROR: End of MazeRunner");
            System.exit(0);
        }
        logger.info("** Starting Maze Runner");
        try {
            
            logger.info("**** Reading the maze from file " + args[1]);
            BufferedReader reader = new BufferedReader(new FileReader(args[1]));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
