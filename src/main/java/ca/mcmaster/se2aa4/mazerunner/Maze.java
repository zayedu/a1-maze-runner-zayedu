package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final String path;
    private final String givenPath;

    public Maze(String path, String givenPath) {
        this.path= path;
        this.givenPath = givenPath;
    }


    public void printMaze() throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else{
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }

    }
    public List<int[]> create2DArray() {
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
    public void print2DArray(List<int[]> list) {
        for (int[] row : list) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    public int findStart(boolean east){
        int y =0;
        List<int[]> mazearr = create2DArray();
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
            }
        }
        return y;
    }

    public boolean verifyPath(String givenPath, boolean east) {
        boolean flag = false;
        Path Canonical = new Path(givenPath);
        String unzippedPath = Canonical.getCanonical();
        List<int[]> mazearr =create2DArray();

        int x;
        int y = findStart(true);
        String[] directions = {"E","N","W","S"};
        String direction = "E";
        if(east) {
            x = 0;
            direction = directions[0];
        }else {
            x = mazearr.size()-1;
            direction = directions[2];
        }

        for(int i =0; i<unzippedPath.length();i++){
            switch (direction) {
                case "E" -> {
                    if (unzippedPath.charAt(i) == 'F') {
                        x++;
                    } else if (unzippedPath.charAt(i) == 'L') {
                        direction = directions[1];
                    } else if (unzippedPath.charAt(i) == 'R') {
                        direction = directions[3];
                    }
                }
                case "N" -> {
                    if (unzippedPath.charAt(i) == 'F') {
                        y--;
                    } else if (unzippedPath.charAt(i) == 'L') {
                        direction = directions[2];
                    } else if (unzippedPath.charAt(i) == 'R') {
                        direction = directions[0];
                    }
                }
                case "W" -> {
                    if (unzippedPath.charAt(i) == 'F') {
                        x--;
                    } else if (unzippedPath.charAt(i) == 'L') {
                        direction = directions[3];
                    } else if (unzippedPath.charAt(i) == 'R') {
                        direction = directions[1];
                    }
                }
                case "S" -> {
                    if (unzippedPath.charAt(i) == 'F') {
                        y++;
                    } else if (unzippedPath.charAt(i) == 'L') {
                        direction = directions[0];
                    } else if (unzippedPath.charAt(i) == 'R') {
                        direction = directions[2];
                    }
                }
            }
            if(x<0 || y<0 || x>mazearr.size()-1 || y>mazearr.size()-1){
                return flag;
            }
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
        return  flag;
    }
    public boolean verifyPath(){
        return verifyPath(givenPath, false) || verifyPath(givenPath, true);
    }

}
