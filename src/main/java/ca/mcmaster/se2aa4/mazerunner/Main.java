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
            Maze maze = new Maze(config.mazeFile);

            //Print maze
            logger.info("**** Printing maze");
            try {
                maze.printMaze();
                List <int[]> mazeRun = maze.create2DArray();

                for (int[] row : mazeRun) {
                    for (int i : row) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }

            }catch (Exception e){
                logger.error("Error printing maze");
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
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String mazeFile = cmd.getOptionValue("i");
        logger.info("**** Reading the maze from file " + mazeFile);
        return new Configuration(mazeFile);
    }

    private record Configuration(String mazeFile) {
    }
}
