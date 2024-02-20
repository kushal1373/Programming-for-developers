//Qn2b
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IndividualFinder {

    /**
     * Finds individuals who have knowledge of a secret within given intervals.
     * @param totalIndividuals Total number of individuals.
     * @param intervals Intervals indicating individuals who share the secret.
     * @param originalOwner The individual who originally possessed the secret.
     * @return List of individuals who know the secret.
     */
    public static List<Integer> findIndividuals(int totalIndividuals, int[][] intervals, int originalOwner) {
        Set<Integer> knownIndividuals = new HashSet<>();
        knownIndividuals.add(originalOwner);

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // If the original owner is known, add individuals within the interval
            if (knownIndividuals.contains(originalOwner)) {
                for (int i = start; i <= end; i++) {
                    knownIndividuals.add(i % totalIndividuals); // Using modulo for circular nature of individuals
                }
            }
        }

        return new ArrayList<>(knownIndividuals);
    }

    public static void main(String[] args) {
        int totalIndividuals = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int originalOwner = 0;

        List<Integer> result = findIndividuals(totalIndividuals, intervals, originalOwner);
        System.out.println(result); 
    }
}
