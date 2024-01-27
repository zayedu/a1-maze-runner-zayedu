package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;
import org.apache.commons .cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");
        try {
            Configuration config = configure(args);
            try {

                if(config.path != null) {
                    Maze maze = new Maze(config.mazeFile, config.path);
                    if(maze.verifyPath()){
                        System.out.println("Correct path");
                    }else{
                        System.out.println("Incorrect path");
                    }
                }else{
                    MazeRunner mazeRunner = new RightHandAlgorithm(config.mazeFile);
                    System.out.println(mazeRunner.findPath());
                }

            }catch (Exception e){
                logger.error("Error accessing maze");
            }

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("** End of MazeRunner");

    }
    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", true, "maze input file");
        options.addOption("p", true, "path to follow");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String mazeFile = cmd.getOptionValue("i");
        String path = cmd.getOptionValue("p");
        logger.info("**** Reading the maze from file " + mazeFile);
        if(path != null) logger.info("**** Reading the path " + path);

        return new Configuration(mazeFile,path);
    }

    private record Configuration(String mazeFile,String path) {
    }
}
