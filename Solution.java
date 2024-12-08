public abstract class Solution {
    public abstract void handleInput(String input);

    public abstract long partOne();

    public abstract long partTwo();

    public void solve(String input) {
        handleInput(input);
        long partOneStartTime = System.currentTimeMillis();
        long partOneAnswer = this.partOne();
        long partOneFinishTime = System.currentTimeMillis();

        long partTwoStartTime = System.currentTimeMillis();
        long partTwoAnswer = this.partTwo();
        long partTwoFinishTime = System.currentTimeMillis();

        System.out.println("Part one answer: " + partOneAnswer + " Time to calculate: " + (partOneFinishTime - partOneStartTime + "ms")
                + "\nPart two: " + partTwoAnswer + " Time to calculate: " + (partTwoFinishTime - partTwoStartTime) + "ms");
    }
}