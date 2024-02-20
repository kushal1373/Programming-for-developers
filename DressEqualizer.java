//Qn no2a
public class DressEqualizer {
    /**
     * Calculates the minimum number of moves required to equalize the number of dresses among sewing machines.
     * 
     * @param machines An array representing the number of dresses in each sewing machine.
     * @return         The minimum number of moves required to equalize the dresses, or -1 if equalization is not possible.
     */
    public static int minMovesToEqualizeDresses(int[] machines) {
        int totalDresses = 0;
        int numMachines = machines.length;

        // Calculate the total number of dresses among all sewing machines.
        for (int dresses : machines) {
            totalDresses += dresses;
        }

        // If the total number of dresses is not divisible evenly among machines, equalization is not possible.
        if (totalDresses % numMachines != 0) {
            return -1;
        }

        // Calculate the target number of dresses each machine should have for equalization.
        int targetDresses = totalDresses / numMachines;
        int balance = 0;

        // Calculate the balance by finding the absolute difference between each machine's dresses and the target.
        for (int dresses : machines) {
            balance += Math.abs(dresses - targetDresses);
        }

        // Return the half of the balance, as each move impacts two machines (one loses, and the other gains).
        return balance / 2;
    }

    /**
     * Main method to demonstrate the functionality of the dress equalization algorithm.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] sewingMachines = {1, 0, 5};
        int result = minMovesToEqualizeDresses(sewingMachines);
        System.out.println("Minimum moves required to equalize dresses: " + result);
    }
}
