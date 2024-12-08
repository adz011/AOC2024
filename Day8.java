
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 extends Solution {

    public class Pair {
        Integer x;
        Integer y;

        Pair(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }
    }

    List<String> splitLines = new ArrayList<>();
    Set<Character> uniqueChars;

    int numOfColumns, numOfRows;

    List<List<Pair>> listOfGrids;

    List<List<Integer>> locationsGrid;

    @Override
    public void handleInput(String input) {
        Pattern pattern = Pattern.compile(".+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            splitLines.add(matcher.group());
        }
        numOfColumns = splitLines.size();
        numOfRows = splitLines.get(0).length();
        locationsGrid = new ArrayList<>();

        listOfGrids = new ArrayList<>();
        uniqueChars = new HashSet<>();
        for (String s : splitLines) {
            for (int i = 0; i < s.length(); i++) {
                uniqueChars.add(s.charAt(i));
            }
        }
        uniqueChars.remove('.');
        for (Character c : uniqueChars) {
            List<Pair> currentGrid = new ArrayList<>();
            for (int j = 0; j < splitLines.size(); j++) {
                for (int i = 0; i < splitLines.get(0).length(); i++) {
                    if (splitLines.get(j).charAt(i) == c) {
                        currentGrid.add(new Pair(i, j));
                    }
                }
            }
            listOfGrids.add(currentGrid);
        }

        for (int i = 0; i < splitLines.size(); i++) {
            locationsGrid.add(new ArrayList<>());
            for (int j = 0; j < splitLines.get(0).length(); j++) {
                locationsGrid.get(i).add(0);
            }
        }
    }

    public void helperFunctionPartTwo(int x1, int y1, int x2, int y2) {
        locationsGrid.get(y1).set(x1, 1);
        locationsGrid.get(y2).set(x2, 1);
        int x1Offset = x1 - x2;
        int y1Offset = y1 - y2;
        int x2Offset = x2 - x1;
        int y2Offset = y2 - y1;
        x1 += x1Offset;
        x2 += x2Offset;
        y1 += y1Offset;
        y2 += y2Offset;
        while (x1 > -1 && x1 < numOfColumns && y1 > -1 && y1 < numOfRows) {
            locationsGrid.get(y1).set(x1, 1);
            x1 += x1Offset;
            y1 += y1Offset;
        }
        while (x2 > -1 && x2 < numOfColumns && y2 > -1 && y2 < numOfRows) {
            locationsGrid.get(y2).set(x2, 1);
            x2 += x2Offset;
            y2 += y2Offset;
        }

    }

    public void helperFunctionPartOne(int x1, int y1, int x2, int y2) {
        int x1Offset = x1 - x2;
        int y1Offset = y1 - y2;
        int x2Offset = x2 - x1;
        int y2Offset = y2 - y1;
        x1 += x1Offset;
        x2 += x2Offset;
        y1 += y1Offset;
        y2 += y2Offset;
        if (x1 > -1 && x1 < numOfColumns && y1 > -1 && y1 < numOfRows) {
            locationsGrid.get(y1).set(x1, 1);
        }
        if (x2 > -1 && x2 < numOfColumns && y2 > -1 && y2 < numOfRows) {
            locationsGrid.get(y2).set(x2, 1);
        }

    }

    @Override
    public long partOne() {
        int sum = 0;
        for (List<Pair> grid : listOfGrids) {
            for (int i = 0; i < grid.size() - 1; i++) {
                for (int j = i + 1; j < grid.size(); j++) {
                    helperFunctionPartOne(grid.get(i).x, grid.get(i).y, grid.get(j).x, grid.get(j).y);
                }
            }
        }

        for (List<Integer> list : locationsGrid) {
            System.out.println(list.stream().toList());
            for (Integer x : list) {
                if (x == 1) {
                    sum++;
                }
            }
        }
        return sum;
    }

    @Override
    public long partTwo() {
        int sum = 0;
        for (List<Pair> grid : listOfGrids) {
            for (int i = 0; i < grid.size() - 1; i++) {
                for (int j = i + 1; j < grid.size(); j++) {
                    helperFunctionPartTwo(grid.get(i).x, grid.get(i).y, grid.get(j).x, grid.get(j).y);
                }
            }
        }

        for (List<Integer> list : locationsGrid) {
            System.out.println(list.stream().toList());
            for (Integer x : list) {
                if (x == 1) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        String input = Files.readString(FileSystems.getDefault().getPath("", "day8.txt"));
        Day8 day8 = new Day8();
        day8.solve(input);
    }
}

