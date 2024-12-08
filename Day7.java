import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 extends Solution {

    List<String> splitLines = new ArrayList<>();

    @Override
    public void handleInput(String input) {
        Pattern pattern = Pattern.compile(".+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            splitLines.add(matcher.group());
        }
    }

    public boolean helperFunction(long previousSum, int currentIndex, List<Long> list, long testValue, boolean isPartTwo) {
        if (list.size() <= ++currentIndex) {
            return previousSum == testValue;
        } else {
            long currentSum, currentMultiplication, currentConcat;
            currentSum = previousSum + list.get(currentIndex);
            currentMultiplication = previousSum * list.get(currentIndex);
            currentConcat = Long.parseLong(String.valueOf(previousSum).concat(String.valueOf(list.get(currentIndex))));
            return (helperFunction(currentSum, currentIndex, list, testValue, isPartTwo) || helperFunction(currentMultiplication, currentIndex, list, testValue, isPartTwo) || helperFunction(currentConcat, currentIndex, list, testValue, isPartTwo) && isPartTwo);
        }
    }

    @Override
    public long partOne() {
        long sum = 0;
        boolean isPartOne = false;
        for (String line : splitLines) {
            Pattern pattern1 = Pattern.compile("\\d+");
            Matcher matcher1 = pattern1.matcher(line);

            long testValue = 0;
            ArrayList<Long> equationNumbers = new ArrayList<>();
            if (matcher1.find()) {
                testValue = Long.parseLong(matcher1.group());
            }
            while (matcher1.find()) {
                equationNumbers.add(Long.parseLong(matcher1.group()));
            }

            if (helperFunction(equationNumbers.get(0), 0, equationNumbers, testValue, isPartOne)) {
                sum += testValue;
            }
        }
        return sum;
    }


    @Override
    public long partTwo() {
        long sum = 0;
        boolean isPartOne = true;
        for (String line : splitLines) {
            Pattern pattern1 = Pattern.compile("\\d+");
            Matcher matcher1 = pattern1.matcher(line);

            long testValue = 0;
            ArrayList<Long> equationNumbers = new ArrayList<>();
            if (matcher1.find()) {
                testValue = Long.parseLong(matcher1.group());
            }
            while (matcher1.find()) {
                equationNumbers.add(Long.parseLong(matcher1.group()));
            }

            if (helperFunction(equationNumbers.get(0), 0, equationNumbers, testValue, isPartOne)) {
                sum += testValue;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        new Day7().solve(Files.readString(FileSystems.getDefault().getPath("", "day7.txt")));
    }
}