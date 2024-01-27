package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class RightHandAlgorithm implements MazeRunner {
    private final String path;

    public RightHandAlgorithm(String path){
        this.path=path;
    }
    public String findPath(){
        Path correctPath = new Path("");
        Maze maze = new Maze(path, null);
        List<int[]> mazearr = maze.create2DArray();
        int x = 0;
        int y = maze.findStart(true);
        String[] directions = {"E", "N", "W", "S"};
        String direction = directions[0];

        while (x < mazearr.get(0).length - 1) {
            mazearr.get(y)[x] = 2;
            switch (direction) {
                case "E" -> {
                    if (mazearr.get(y + 1)[x] != 0) {
                        correctPath.add("RF");
                        direction = directions[3];
                        y++;

                    } else if (mazearr.get(y)[x + 1] != 0) {
                        correctPath.add("F");
                        x++;

                    } else if (mazearr.get(y - 1)[x] != 0) {
                        correctPath.add("L");
                        direction = directions[1];
                    } else {
                        correctPath.add("RR");
                        direction = directions[2];

                    }
                }
                case "N" -> {
                    if (mazearr.get(y)[x + 1] != 0) {
                        correctPath.add("RF");
                        direction = directions[0];
                        x++;

                    } else if (mazearr.get(y - 1)[x] != 0) {
                        correctPath.add("F");
                        y--;
                    } else if (mazearr.get(y)[x - 1] != 0) {
                        correctPath.add("L");
                        direction = directions[2];
                    } else {
                        correctPath.add("RR");
                        direction = directions[3];
                    }

                }
                case "W" -> {
                    if (mazearr.get(y - 1)[x] != 0) {
                        correctPath.add("RF");
                        direction = directions[1];
                        y--;
                    } else if (mazearr.get(y)[x - 1] != 0) {
                        correctPath.add("F");
                        x--;
                    } else if (mazearr.get(y + 1)[x] != 0) {
                        correctPath.add("L");
                        direction = directions[3];
                    } else {
                        correctPath.add("RR");
                        direction = directions[0];
                    }

                }
                case "S" -> {
                    if (mazearr.get(y)[x - 1] != 0) {
                        correctPath.add("RF");
                        direction = directions[2];
                        x--;
                    } else if (mazearr.get(y + 1)[x] != 0) {
                        correctPath.add("F");
                        y++;
                    } else if (mazearr.get(y)[x + 1] != 0) {
                        correctPath.add("L");
                        direction = directions[0];
                    } else {
                        correctPath.add("RR");
                        direction = directions[1];
                    }
                }
            }

        }

        return correctPath.getFactorized();

    }
}
