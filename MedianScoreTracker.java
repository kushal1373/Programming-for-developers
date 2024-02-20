//QN 3a
import java.util.PriorityQueue;

public class MedianScoreTracker {

    private PriorityQueue<Double> lowerHalf; // stores the smaller half of the scores
    private PriorityQueue<Double> upperHalf; // stores the larger half of the scores

    public MedianScoreTracker() {
        lowerHalf = new PriorityQueue<>((a, b) -> Double.compare(b, a)); // max heap for lower half
        upperHalf = new PriorityQueue<>(); // min heap for upper half
    }

    public void addScore(double score) {
        if (lowerHalf.isEmpty() || score <= lowerHalf.peek()) {
            lowerHalf.offer(score);
        } else {
            upperHalf.offer(score);
        }

        // Balance the heaps to ensure their sizes differ by at most 1
        if (lowerHalf.size() > upperHalf.size() + 1) {
            upperHalf.offer(lowerHalf.poll());
        } else if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }

    public double getMedianScore() {
        if (lowerHalf.isEmpty() && upperHalf.isEmpty()) {
            throw new IllegalStateException("No scores available.");
        }

        if (lowerHalf.size() == upperHalf.size()) {
            // Even number of scores, return the average of the two middle scores
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        } else {
            // Odd number of scores, return the middle score from the lower half
            return lowerHalf.peek();
        }
    }

    public static void main(String[] args) {
        MedianScoreTracker scoreTracker = new MedianScoreTracker();
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        double median1 = scoreTracker.getMedianScore();
        System.out.println("Median 1: " + median1);

        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        double median2 = scoreTracker.getMedianScore();
        System.out.println("Median 2: " + median2);
    }
}
