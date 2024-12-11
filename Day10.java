import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 extends Solution {

    List<String> input;
    int numOfColumns, numOfRows;
    int[][] map;
    static int foundPaths;


    @Override
    public void handleInput(String input) {
        this.input = new ArrayList<>();
        Pattern pattern = Pattern.compile(".+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            this.input.add(matcher.group());
        }
        numOfColumns = this.input.get(0).length();
        numOfRows = this.input.size();
        map = new int[numOfRows][numOfColumns];
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                map[i][j] = this.input.get(i).charAt(j) - '0';
            }
        }

    }


    public void findScorePartOne(int x, int y, boolean[][] trailheads) {
        trailheads[y][x] = true;
        // base case
        if (map[y][x] == 9) {
            foundPaths++;
            return;
        }

        // right
        if (x < numOfColumns - 1 && map[y][x + 1] == map[y][x] + 1 && !trailheads[y][x + 1]) {
            findScorePartOne(x + 1, y, trailheads);
        }
        // left
        if (x > 0 && map[y][x - 1] == map[y][x] + 1 && !trailheads[y][x - 1]) {
            findScorePartOne(x - 1, y, trailheads);
        }
        // up
        if (y > 0 && map[y - 1][x] == map[y][x] + 1 && !trailheads[y - 1][x]) {
            findScorePartOne(x, y - 1, trailheads);
        }
        // down
        if (y < numOfRows - 1 && map[y + 1][x] == map[y][x] + 1 && !trailheads[y + 1][x]) {
            findScorePartOne(x, y + 1, trailheads);
        }
    }

    public void findScorePartTwo(int x, int y) {
        // base case
        if (map[y][x] == 9) {
            foundPaths++;
            return;
        }

        // right
        if (x < numOfColumns - 1 && map[y][x + 1] == map[y][x] + 1) {
            findScorePartTwo(x + 1, y);
        }
        // left
        if (x > 0 && map[y][x - 1] == map[y][x] + 1) {
            findScorePartTwo(x - 1, y);
        }
        // up
        if (y > 0 && map[y - 1][x] == map[y][x] + 1) {
            findScorePartTwo(x, y - 1);
        }
        // down
        if (y < numOfRows - 1 && map[y + 1][x] == map[y][x] + 1) {
            findScorePartTwo(x, y + 1);
        }

    }

    @Override
    public long partOne() {
        long sum = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (map[i][j] == 0) {
                    boolean[][] trailheads = new boolean[numOfRows][numOfColumns];
                    foundPaths = 0;
                    findScorePartOne(j, i, trailheads);
                    sum += foundPaths;
                }
            }
        }
        return sum;
    }

    @Override
    public long partTwo() {
        long sum = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (map[i][j] == 0) {
                    foundPaths = 0;
                    findScorePartTwo(j, i);
                    sum += foundPaths;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        new Day10().solve(Files.readString(FileSystems.getDefault().getPath("", "day10.txt")));
    }
}
