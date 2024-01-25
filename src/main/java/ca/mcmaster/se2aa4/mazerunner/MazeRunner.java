package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeRunner {
    private final String path;
    public MazeRunner(String path) {
        this.path= path;
    }
    public static String getPath(String givenPath) {
        StringBuilder result = new StringBuilder();
        StringBuilder digitString = new StringBuilder();

        for (char ch : givenPath.toCharArray()) {
            if (Character.isDigit(ch)) {
                digitString.append(ch);
            } else {
                int count = digitString.length() > 0 ? Integer.parseInt(digitString.toString()) : 1;
                for (int i = 0; i < count; i++) {
                    result.append(ch);
                }
                digitString.setLength(0); // clear the digitString for the next number
            }
        }

        return result.toString();
    }
    public int findStart(boolean east){
        int y =0;
        Maze maze = new Maze(path);
        List<int[]> mazearr = maze.create2DArray();
        if (east){
            for (int i = 0; i < mazearr.size(); i++) {
                if (mazearr.get(i)[0] == 1) {
                    y = i;
                }
            }
        }
        else{

            for (int i = 0; i < mazearr.size(); i++) {
                if (mazearr.get(i)[mazearr.size()-1] == 1) {
                    y = i;
                }
            };
        }
        return y;
    }

    public boolean verifyPath(String givenPath, boolean east) {
    //    System.out.println("Starting verifyPath...\n");
    boolean flag = false;
    String unzippedPath = getPath(givenPath);
    Maze maze  = new Maze(path);
    List<int[]> mazearr = maze.create2DArray();

    int x;
    int y = findStart(east);
    String[] directions = {"E","N","W","S"};
    String direction = "E";
    if(east) {
        x = 0;
         direction = directions[0];
    }else {
        x = mazearr.size()-1;
         direction = directions[2];
    }
    //start point is (x,y)
    //start at start point and follow path F is x+1, L is , R is y-1
    //if you hit a wall, return false
     //   System.out.println("x: " + x + " y: " + y);
       // System.out.println("Position: " + mazearr.get(y)[x]);
        //System.out.println("Direction: " + direction);

    for(int i =0; i<unzippedPath.length();i++){
        if(direction == "E"){
            if(unzippedPath.charAt(i) == 'F'){
                x++;
            }
            else if(unzippedPath.charAt(i) == 'L'){
                direction = directions[1];
            }
            else if(unzippedPath.charAt(i) == 'R'){
                direction = directions[3];
            }
        }
        else if(direction == "N"){
            if(unzippedPath.charAt(i) == 'F'){
                y++;
            }
            else if(unzippedPath.charAt(i) == 'L'){
                direction = directions[2];
            }
            else if(unzippedPath.charAt(i) == 'R'){
                direction = directions[0];
            }
        }
        else if(direction == "W"){
            if(unzippedPath.charAt(i) == 'F'){
                x--;
            }
            else if(unzippedPath.charAt(i) == 'L'){
                direction = directions[3];
            }
            else if(unzippedPath.charAt(i) == 'R'){
                direction = directions[1];
            }
        }
        else if(direction == "S"){
            if(unzippedPath.charAt(i) == 'F'){
                y--;
            }
            else if(unzippedPath.charAt(i) == 'L'){
                direction = directions[0];
            }
            else if(unzippedPath.charAt(i) == 'R'){
                direction = directions[2];
            }
        }
       // System.out.println("x: " + x + " y: " + y);
        if(x<0 || y<0 || x>mazearr.size()-1 || y>mazearr.size()-1){
            return flag;
        }
       // System.out.println("Position: " + mazearr.get(y)[x]);
       // System.out.println("Direction: " + direction);

        if(mazearr.get(y)[x] == 0){
            return flag;
        }
        if(mazearr.get(y)[x] == 1) {
         if (east && x == mazearr.size() - 1) {
                flag = true;
                return flag;
            } else if (!east && x == 0) {
                flag = true;
                return flag;
            }

         }
        }


    return flag;
    }

    public boolean checkPath(String givenPath){
        return verifyPath(givenPath, false) || verifyPath(givenPath, true);
    }
    public String findPath() {
        Maze maze = new Maze(path);
        List<int[]> mazearr = maze.create2DArray();
        String correctPath = "";
        int x = 0;
        int y = findStart(true);
        String[] directions = {"E","N","W","S"};
        String direction = directions[0];

        while (x < mazearr.size()-1){
            switch (direction) {
                case "E" -> {
                    if (mazearr.get(y)[x + 1] == 1) {
                        correctPath += "F";
                        x++;
                    } else if (mazearr.get(y + 1)[x] == 1) {
                        correctPath += "R";
                        direction = directions[3];
                        y++;
                    } else if (mazearr.get(y - 1)[x] == 1) {
                        correctPath += "L";
                        direction = directions[1];
                        y--;
                    }
                }
                case "N" -> {
                    if (mazearr.get(y + 1)[x] == 1) {
                        correctPath += "F";
                        y++;
                    } else if (mazearr.get(y)[x - 1] == 1) {
                        correctPath += "R";
                        direction = directions[2];
                        x--;
                    } else if (mazearr.get(y)[x + 1] == 1) {
                        correctPath += "L";
                        direction = directions[0];
                        x++;
                    }
                }
                case "W" -> {
                    if (mazearr.get(y)[x - 1] == 1) {
                        correctPath += "F";
                        x--;
                    } else if (mazearr.get(y - 1)[x] == 1) {
                        correctPath += "R";
                        direction = directions[1];
                        y--;
                    } else if (mazearr.get(y + 1)[x] == 1) {
                        correctPath += "L";
                        direction = directions[3];
                        y++;
                    }
                }
                case "S" -> {
                    if (mazearr.get(y - 1)[x] == 1) {
                        correctPath += "F";
                        y--;
                    } else if (mazearr.get(y)[x + 1] == 1) {
                        correctPath += "R";
                        direction = directions[0];
                        x++;
                    } else if (mazearr.get(y)[x - 1] == 1) {
                        correctPath += "L";
                        direction = directions[2];
                        x--;
                    }
                }
            }
        }

        return correctPath;
    }

}