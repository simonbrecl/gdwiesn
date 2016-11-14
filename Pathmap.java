import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pathmap {
    private final static int I = 999;

    private static int[][][] edges = loadEdges("paths/MyWorld.xml");

    private static int[][][] loadEdges(String file) {
        List<int[][]> edges = new ArrayList<>();

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

            NodeList cells = doc.getElementsByTagName("mxCell");

            for (int i = 0; i < cells.getLength(); i++) {
                Node cell = cells.item(i);

                Node edge = cell.getAttributes().getNamedItem("edge");

                if (edge == null || !edge.getTextContent().equals("1")) {
                    continue;
                }

                int[][] coordinates = new int[2][2];

                NodeList points = cell.getFirstChild().getChildNodes();

                for (int j = 0; j < 2; j++) {
                    NamedNodeMap point = points.item(j).getAttributes();

                    coordinates[j] = new int[] {Integer.valueOf(point.getNamedItem("x").getTextContent()), Integer.valueOf(point.getNamedItem("y").getTextContent())};
                }

                edges.add(coordinates);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return edges.toArray(new int[edges.size()][2][2]);
    }

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
