//Qn 5b
import java.util.*;

public class NetworkDeviceImpact {

    public static List<Integer> findImpactedDevices(int[][] connections, int targetDevice) {
        Map<Integer, Set<Integer>> networkGraph = buildNetworkGraph(connections);
        Set<Integer> impactedDevices = new HashSet<>();

        dfs(networkGraph, targetDevice, impactedDevices);

        // Remove the target device itself
        impactedDevices.remove(targetDevice);

        return new ArrayList<>(impactedDevices);
    }

    private static Map<Integer, Set<Integer>> buildNetworkGraph(int[][] connections) {
        Map<Integer, Set<Integer>> networkGraph = new HashMap<>();

        for (int[] connection : connections) {
            int device1 = connection[0];
            int device2 = connection[1];

            networkGraph.computeIfAbsent(device1, k -> new HashSet<>()).add(device2);
            networkGraph.computeIfAbsent(device2, k -> new HashSet<>()).add(device1);
        }

        return networkGraph;
    }

    private static void dfs(Map<Integer, Set<Integer>> networkGraph, int currentDevice, Set<Integer> impactedDevices) {
        Stack<Integer> stack = new Stack<>();
        stack.push(currentDevice);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            Set<Integer> neighbors = networkGraph.getOrDefault(current, Collections.emptySet());

            for (int neighbor : neighbors) {
                if (!impactedDevices.contains(neighbor)) {
                    stack.push(neighbor);
                    impactedDevices.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] connections = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
        int targetDevice = 4;

        List<Integer> impactedDevices = findImpactedDevices(connections, targetDevice);
        System.out.println("Impacted Device List: " + impactedDevices);
    }
}
