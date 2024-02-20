//qn no1b
import java.util.PriorityQueue;

/**
 * This class represents the construction of engines using available engineers.
 */
public class EngineConstruction {

    /**
     * Calculates the minimum time needed to construct all engines using available engineers.
     * 
     * @param engineTimes An array representing the construction time for each engine.
     * @param splitCost   The cost of splitting an engineer into two.
     * @return            The minimum time needed to build all engines.
     */
    public static int calculateMinimumConstructionTime(int[] engineTimes, int splitCost) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Add each engine construction time to the priority queue
        for (int time : engineTimes) {
            pq.offer(time);
        }
        
        // Combine the two engines with the longest construction time until only one engine remains
        while (pq.size() > 1) {
            int combinedTime = pq.poll() + pq.poll() + splitCost; // Combine two engines and add split cost
            pq.offer(combinedTime); // Insert the combined time back into the priority queue
        }
        
        // The remaining element in the priority queue represents the minimum construction time
        return pq.poll();
    }

    /**
     * Main method to demonstrate the functionality of the engine construction algorithm.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] engineTimes = {1, 2, 3};
        int splitCost = 1;

        int minTime = calculateMinimumConstructionTime(engineTimes, splitCost);
        System.out.println("Minimum time needed to build all engines: " + minTime);
    }
}
