package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeRunner {
    public List<int[]> create2DArray(String path) {
        List<int[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] row = new int[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    row[i] = line.charAt(i) == '#' ? 0 : 1;
                }
                list.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String findPath() {

        return null;
    }
    public void main() {
        List<int[]> maze = create2DArray("/Users/zayedumer/Documents/2AA4/a1-maze-runner-zayedu/examples/small.maz.txt");
        //Correctly print the maze list
        for (int[] row : maze) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}