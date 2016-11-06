import java.util.ArrayList;
import java.util.List;

public class Pathmap {
    private final static int I = 999;

    private static int[][] coordinates = new int[][] {
            {237, 58}, // 0: bar left
            {363, 51}, // 1: bar right
            {496, 62}, // 2: passage right
            {227, 167}, // 3: table 1 right
            {274, 167}, // 4: between table 1&2
            {323, 167}, // 5: table 2 left
            {481, 170}, // 6: table 2 right
            {523, 170}, // 7: between table 2&3
            {570, 170}, // 8: table 3 left
            {229, 317}, // 9: table 4 right
            {273, 317}, // 10: between table 4&5
            {312, 317}, // 11: table 5 left
            {482, 316}, // 12: table 5 right
            {530, 316}, // 13: between table 5&6
            {569, 316}, // 14: table 6 left
            {229, 468}, // 15: table 7 right
            {274, 468}, // 16: between table 7&8
            {317, 468}, // 17: table 8 left
            {480, 468}, // 18: table 8 right
            {530, 468}, // 19: between table 8&9
            {569, 468}, // 20: table 9 left
    };

    private static int[][] adjacencies = new int[][] {
            //  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20
            {0,	1,	I,	I,	1,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 0
            {1,	0,	1,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 1
            {I,	1,	0,	I,	I,	I,	I,	1,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 2
            {I,	I,	I,	0,	1,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 3
            {1,	I,	I,	1,	0,	1,	I,	I,	I,	I,	1,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 4
            {I,	I,	I,	I,	1,	0,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 5
            {I,	I,	I,	I,	I,	I,	0,	1,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 6
            {I,	I,	1,	I,	I,	I,	1,	0,	1,	I,	I,	I,	I,	1,	I,	I,	I,	I,	I,	I,	I}, // 7
            {I,	I,	I,	I,	I,	I,	I,	1,	0,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 8
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	0,	1,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 9
            {I,	I,	I,	I,	1,	I,	I,	I,	I,	1,	0,	1,	I,	I,	I,	I,	1,	I,	I,	I,	I}, // 10
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	1,	0,	I,	I,	I,	I,	I,	I,	I,	I,	I}, // 11
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	0,	1,	I,	I,	I,	I,	I,	I,	I}, // 12
            {I,	I,	I,	I,	I,	I,	I,	1,	I,	I,	I,	I,	1,	0,	1,	I,	I,	I,	I,	1,	I}, // 13
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	1,	0,	I,	I,	I,	I,	I,	I}, // 14
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	0,	1,	I,	I,	I,	I}, // 15
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	1,	I,	I,	I,	I,	1,	0,	1,	I,	I,	I}, // 16
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	1,	0,	I,	I,	I}, // 17
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	0,	1,	I}, // 18
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	1,	I,	I,	I,	I,	1,	0,	1}, // 19
            {I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	I,	1,	0} // 20
    };

    private static int[][] paths = FloydAlgorithm(adjacencies);

    private static int[][] FloydAlgorithm(int[][] dist) {
        int[][] next = new int[dist.length][dist.length];

        for (int u = 0; u < dist.length; u++)
            for (int v = 0; v < dist.length; v++)
                next[u][v] = (dist[u][v] == I) ? -1 : v;

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
