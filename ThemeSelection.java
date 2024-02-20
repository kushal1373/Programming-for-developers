//1a
public class ThemeSelection {
    /**
     * Finds the theme with the minimum cost for a venue, excluding the previous theme.
     *
     * @param costs         The array representing the costs of each theme for the venue.
     * @param previousTheme The index of the previous theme selected for the venue.
     * @return The index of the theme with the minimum cost, excluding the previous theme.
     */
    static int findMinimumTheme(int[] costs, int previousTheme) {
        int minCost = Integer.MAX_VALUE; // Initialize the minimum cost to a large value
        int minIndex = 0; // Initialize the index of the minimum cost theme to 0

        // Iterate through the costs array to find the theme with the minimum cost
        for (int i = 0; i < costs.length; i++) {
            // Check if the current theme cost is less than the minimum cost
            if (costs[i] < minCost) {
                // Check if the current theme index is different from the previous theme index
                if (i != previousTheme) {
                    minIndex = i; // Update the minimum index to the current index
                    minCost = costs[i]; // Update the minimum cost to the current cost
                }
            }
        }
        return minIndex;
    }

    /**
     * Calculates the minimum cost of selecting themes for venues.
     *
     * @param venues The 2D array representing the costs of themes for each venue.
     * @return The total minimum cost of selecting themes for all venues.
     */
    static int calculateTotalCost(int[][] venues) {
        int previousTheme = Integer.MIN_VALUE; // Initialize the index of the previous theme
        int totalCost = 0; // Initialize the total cost to 0

        // Iterate through the venues to calculate the total minimum cost
        for (int i = 0; i < venues.length; i++) {
            int currentTheme = findMinimumTheme(venues[i], previousTheme); // Find the minimum cost theme for the venue
            totalCost += venues[i][currentTheme]; // Add the cost of the selected theme to the total cost
            previousTheme = currentTheme; // Update the previous theme index
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[][] venues = {{1, 5, 3}, {2, 9, 4}};
        System.out.println("Total minimum cost of theme selection: " + calculateTotalCost(venues));
    }
}
