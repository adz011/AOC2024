import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 extends Solution {

    HashMap<Long, Long> input;

    @Override
    public void handleInput(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        this.input = new HashMap<>();
        while (matcher.find()) {
            this.input.merge(Long.parseLong(matcher.group()), 1L, Long::sum);
        }
    }

    private HashMap<Long, Long> partOneHelper(HashMap<Long, Long> input) {
        HashMap<Long, Long> copy = new HashMap<>();
        for (Long x : input.keySet()) {
            if (x == 0) {
                copy.merge(1L, input.get(x), Long::sum);
                continue;
            }
            List<Long> splitCurrentInt = new ArrayList<>();
            long currentIntCopy = x;
            while (currentIntCopy / 10 > 0) {
                splitCurrentInt.add(0, currentIntCopy % 10L);
                currentIntCopy = currentIntCopy / 10L;
            }
            splitCurrentInt.add(0, currentIntCopy % 10L);
            if (splitCurrentInt.size() % 2 == 0) {
                StringBuilder stringInt = new StringBuilder();
                for (int j = 0; j < splitCurrentInt.size() / 2; j++) {
                    stringInt.append(splitCurrentInt.get(j));
                }
                copy.merge(Long.parseLong(String.valueOf(stringInt)), input.get(x), Long::sum);
                stringInt = new StringBuilder();
                for (int j = splitCurrentInt.size() / 2; j < splitCurrentInt.size(); j++) {
                    stringInt.append(splitCurrentInt.get(j));
                }
                copy.merge(Long.parseLong(String.valueOf(stringInt)), input.get(x), Long::sum);
            } else copy.merge(x * 2024, input.get(x), Long::sum);
        }

        return copy;
    }

    @Override
    public long partOne() {
        HashMap<Long, Long> copy = input;
        for (int i = 0; i <25; i++) {
            copy = partOneHelper(copy);
            System.out.println(i);
        }
        return copy.values().stream().mapToLong(Long::longValue).sum();
    }

    @Override
    public long partTwo() {
        HashMap<Long, Long> copy = input;
        for (int i = 0; i < 75; i++) {
            copy = partOneHelper(copy);
            System.out.println(i);
        }
        return copy.values().stream().mapToLong(Long::longValue).sum();
    }

    public static void main(String[] args) throws IOException {
        new Day11().solve(Files.readString(FileSystems.getDefault().getPath("", "day11.txt")));

    }
}
