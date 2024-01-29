package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    public String path;
    public Path(String path) {
        this.path= path;
    }

    public  String getCanonical() {
        StringBuilder result = new StringBuilder();
        StringBuilder digitString = new StringBuilder();

        for (char ch : path.toCharArray()) {

            if (Character.isDigit(ch) && ch != ' ') {
                digitString.append(ch);
            } else if(ch != ' '){
                int count = !digitString.isEmpty() ? Integer.parseInt(digitString.toString()) : 1;
                for (int i = 0; i < count; i++) {
                    result.append(ch);
                }
                digitString.setLength(0);
            }

        }
        return result.toString();
    }
    public String getFactorized() {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == path.charAt(i - 1)) {
                count++;
            } else {
                if (count > 1) {
                    result.append(count);
                }
                result.append(path.charAt(i - 1)+" ");
                count = 1;
            }
        }
        if (count > 1) {
            result.append(count);
        }
        result.append(path.charAt(path.length() - 1)+" ");
        return result.toString();
    }
    public void add(String c){
        path+=c;
    }

}
