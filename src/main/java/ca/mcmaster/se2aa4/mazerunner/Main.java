package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");
        try {
            Configuration config = configure(args);
            //Create maze from path

            logger.info("**** Printing maze");
            try {
                Maze maze = new Maze(config.mazeFile);
                maze.printMaze();
                maze.print2DArray(maze.create2DArray());
                MazeRunner mazeRunner = new MazeRunner(config.mazeFile);
                //Print path
                if(config.path != null) {
                    System.out.println(mazeRunner.checkPath(config.path));
                }
                System.out.println(mazeRunner.findPath());
            }catch (Exception e){
                logger.error("Error printing maze");
                logger.error(e.getMessage());
            }

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
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
        logger.info("**** Reading the path from file " + path);
          return new Configuration(mazeFile,path);
    }

    private record Configuration(String mazeFile,String path) {
    }
}
