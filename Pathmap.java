import java.util.*;

public class Pathmap {
    private final static int I = 999;

    private static int[][][] edges = new int[][][] {
            {
                    {237, 58}, // bar left
                    {363, 51}, // bar right
            },
            {
                    {237, 58}, // bar left
                    {274, 167}, // between table 1&2
            },
            {
                    {274, 167}, // between table 1&2
                    {227, 167}, // table 1 right
            },
            {
                    {274, 167}, // between table 1&2
                    {323, 167}, // table 2 left
            },
            {
                    {274, 167}, // between table 1&2
                    {273, 317}, // between table 4&5
            },
            {
                    {273, 317}, // between table 4&5
                    {229, 317}, // table 4 right
            },
            {
                    {273, 317}, // between table 4&5
                    {312, 317}, // table 5 left
            },
            {
                    {273, 317}, // between table 4&5
                    {274, 468}, // between table 7&8
            },
            {
                    {274, 468}, // between table 7&8
                    {229, 468}, // table 7 right
            },
            {
                    {274, 468}, // between table 7&8
                    {317, 468}, // table 8 left
            },
            {
                    {363, 51}, // bar right
                    {496, 62}, // passage right
            },
            {
                    {496, 62}, // passage right
                    {523, 170}, // between table 2&3
            },
            {
                    {523, 170}, // between table 2&3
                    {481, 170}, // table 2 right
            },
            {
                    {523, 170}, // between table 2&3
                    {570, 170}, // table 3 left
            },
            {
                    {523, 170}, // between table 2&3
                    {530, 316}, // between table 5&6
            },
            {
                    {530, 316}, // between table 5&6
                    {482, 316}, // table 5 right
            },
            {
                    {530, 316}, // between table 5&6
                    {569, 316}, // table 6 left
            },
            {
                    {530, 316}, // between table 5&6
                    {530, 468}, // between table 8&9
            },
            {
                    {530, 468}, // between table 8&9
                    {480, 468}, // table 8 right
            },
            {
                    {530, 468}, // between table 8&9
                    {569, 468}, // table 9 left
            }
    };

    private static int[][] coordinates = distinctCoordinates(edges);
    private static int[][] paths = FloydAlgorithm(edges);

    private static int[][] FloydAlgorithm(int[][][] edges) {
        int[][] dist = new int[coordinates.length][coordinates.length];
        int[][] next = new int[coordinates.length][coordinates.length];

        // Initialize default values.
        for (int u = 0; u < dist.length; u++) {
            for (int v = 0; v < dist.length; v++) {
                dist[u][v] = I;
                next[u][v] = -1;
            }
        }

        // Build distances and next vertex from edges.
        for (int[][] edge : edges) {
            int u = findCoordinate(edge[0]);
            int v = findCoordinate(edge[1]);

            dist[u][u] = 0;
            dist[u][v] = 1;
            dist[v][v] = 0;
            dist[v][u] = 1;

            next[u][v] = v;
            next[v][u] = u;
        }

        // Floyd–Warshall algorithm.
        for (int k = 0; k < dist.length; k++) {
            for (int i = 0; i < dist.length; i++) {
                for (int j = 0; j < dist.length; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        return next;
    }

    private static int[][] distinctCoordinates(int[][][] edges) {
        Set<int[]> coordinates = new HashSet<>();

        for (int[][] edge : edges) {
            boolean edge0 = false, edge1 = false;

            for (int[] coordinate : coordinates) {
                if (coordinate[0] == edge[0][0] && coordinate[1] == edge[0][1]) {
                    edge0 = true;
                }

                if (coordinate[0] == edge[1][0] && coordinate[1] == edge[1][1]) {
                    edge1 = true;
                }

                if (edge0 && edge1) {
                    break;
                }
            }

            if (!edge0) {
                coordinates.add(edge[0]);
            }

            if (!edge1) {
                coordinates.add(edge[1]);
            }
        }

        return coordinates.toArray(new int[coordinates.size()][2]);
    }


    private static int findCoordinate(int[] coordinate) {
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i][0] == coordinate[0] && coordinates[i][1] == coordinate[1]) {
                return i;
            }
        }

        return -1;
    }

    private static int closestCoordinate(int x, int y) {
        int closest = -1;
        double distance = I;

        for (int i = 0; i < coordinates.length; i++) {
            double d = Math.sqrt(Math.pow(x - coordinates[i][0], 2) + Math.pow(y - coordinates[i][1], 2));

            if (d < distance) {
                closest = i;
                distance = d;
            }
        }

        return closest;
    }

    public static List<int[]> findPath(int fromX, int fromY, int toX, int toY) {
        int fromIndex = -1, toIndex = -1;

        // Check direct coordinate match.
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i][0] == fromX && coordinates[i][1] == fromY) {
                fromIndex = i;
            }

            if (coordinates[i][0] == toX && coordinates[i][1] == toY) {
                toIndex = i;
            }
        }

        List<int[]> path = new ArrayList<>();

        // If no from coordinate found, find the closest.
        if (fromIndex == -1) {
            fromIndex = closestCoordinate(fromX, fromY);
        }

        // If no to coordinate found, find the closest.
        if (toIndex == -1) {
            toIndex = closestCoordinate(toX, toY);
        }

        path.add(coordinates[fromIndex]);

        // Insert  path coordinates.
        while (fromIndex != toIndex) {
            path.add(coordinates[paths[fromIndex][toIndex]]);

            fromIndex = paths[fromIndex][toIndex];
        }

        path.add(coordinates[toIndex]);

        return path;
    }
}
