package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;

public class Maze{
  public final File PATH_FILE;
  public Maze(File fileName){
    this.PATH_FILE = fileName;
  }
  public void printMaze() throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader(this.PATH_FILE));
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
  }
  private MazeRunner get_runner(){
    return new MazeRunner(this.PATH_FILE);
  }
  public MazePath getPath(){
    return this.get_runner().getPath();
  }
  public boolean verifyPath(MazePath path){
    return this.get_runner().navigate(path);
  }
}