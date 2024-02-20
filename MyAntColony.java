//Qn 5a
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyAntColony {

    private double[][] myDistances;
    private double[][] myPheromones;
    private int myNumAnts;
    private double myDecayRate;
    private double myAlpha;
    private double myBeta;

    public MyAntColony(double[][] distances, int numAnts, double decayRate, double alpha, double beta) {
        this.myDistances = distances;
        this.myNumAnts = numAnts;
        this.myDecayRate = decayRate;
        this.myAlpha = alpha;
        this.myBeta = beta;

        int numCities = distances.length;
        myPheromones = new double[numCities][numCities];

        // Initialize pheromones
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                myPheromones[i][j] = 1.0;
            }
        }
    }

    public int[] solve(int numIterations) {
        int[] bestRoute = null;
        double bestRouteLength = Double.POSITIVE_INFINITY;

        for (int iteration = 0; iteration < numIterations; iteration++) {
            int[][] antRoutes = new int[myNumAnts][];

            for (int ant = 0; ant < myNumAnts; ant++) {
                antRoutes[ant] = generateAntRoute();
            }

            updatePheromones(antRoutes);

            for (int ant = 0; ant < myNumAnts; ant++) {
                double routeLength = calculateRouteLength(antRoutes[ant]);
                if (routeLength < bestRouteLength) {
                    bestRouteLength = routeLength;
                    bestRoute = antRoutes[ant].clone();
                }
            }

            decayPheromones();
        }

        return bestRoute;
    }

    private int[] generateAntRoute() {
        int numCities = myDistances.length;
        int startCity = new Random().nextInt(numCities);

        int[] route = new int[numCities];
        boolean[] visited = new boolean[numCities];
        route[0] = startCity;
        visited[startCity] = true;

        for (int i = 1; i < numCities; i++) {
            int nextCity = selectNextCity(route, visited);
            route[i] = nextCity;
            visited[nextCity] = true;
        }

        return route;
    }

    private int selectNextCity(int[] route, boolean[] visited) {
        int numCities = myDistances.length;
        int currentCity = route[route.length - 1];

        List<Integer> possibleCities = new ArrayList<>();

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                possibleCities.add(i);
            }
        }

        double[] probabilities = new double[possibleCities.size()];

        for (int i = 0; i < possibleCities.size(); i++) {
            int nextCity = possibleCities.get(i);
            probabilities[i] = Math.pow(myPheromones[currentCity][nextCity], myAlpha)
                    * Math.pow(1.0 / myDistances[currentCity][nextCity], myBeta);
        }

        double sum = 0.0;
        for (double probability : probabilities) {
            sum += probability;
        }

        double rand = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= sum;
            cumulativeProbability += probabilities[i];

            if (rand <= cumulativeProbability) {
                return possibleCities.get(i);
            }
        }

        // This should not happen, but just in case
        return possibleCities.get(possibleCities.size() - 1);
    }

    private void updatePheromones(int[][] antRoutes) {
        int numCities = myDistances.length;

        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                myPheromones[i][j] *= myDecayRate;
            }
        }

        for (int ant = 0; ant < myNumAnts; ant++) {
            double pheromoneDelta = 1.0 / calculateRouteLength(antRoutes[ant]);

            for (int i = 0; i < numCities - 1; i++) {
                int city1 = antRoutes[ant][i];
                int city2 = antRoutes[ant][i + 1];
                myPheromones[city1][city2] += pheromoneDelta;
                myPheromones[city2][city1] += pheromoneDelta;
            }
        }
    }

    private double calculateRouteLength(int[] route) {
        double length = 0.0;
        int numCities = myDistances.length;

        for (int i = 0; i < numCities - 1; i++) {
            int city1 = route[i];
            int city2 = route[i + 1];
            length += myDistances[city1][city2];
        }

        // Add distance from the last city back to the starting city
        int lastCity = route[numCities - 1];
        int startCity = route[0];
        length += myDistances[lastCity][startCity];

        return length;
    }

    private void decayPheromones() {
        int numCities = myDistances.length;

        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                myPheromones[i][j] *= myDecayRate;
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        double[][] distances = {
                {0, 2, 9, 10},
                {1, 0, 6, 4},
                {15, 7, 0, 8},
                {6, 3, 12, 0}
        };

        int numAnts = 10;
        double decayRate = 0.95;
        double alpha = 1;
        double beta = 2;

        MyAntColony myAntColony = new MyAntColony(distances, numAnts, decayRate, alpha, beta);
        int[] bestRoute = myAntColony.solve(1000);

        System.out.print("Best Route: ");
        for (int city : bestRoute) {
            System.out.print(city + " ");
        }
    }
}
