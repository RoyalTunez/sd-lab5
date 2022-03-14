package graph;

import javafx.util.Pair;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListGraph extends AbstractGraph {
    private List<Pair<Integer, Integer>> edges;
    private int vertexAmount;

    @Override
    public int getVertexAmount() {
        return vertexAmount;
    }

    @Override
    public void drawEdges() {
        for (Pair<Integer, Integer> edge: edges) {
            drawEdge(edge.getKey(), edge.getValue());
        }
    }

    @Override
    public void readFromFile(Path file) {
        edges = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            vertexAmount = scanner.nextInt();

            int edgeAmount = scanner.nextInt();

            for (int i = 0; i < edgeAmount; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();

                assert u >= 0 && u < vertexAmount;
                assert v >= 0 && v < vertexAmount;
                assert u != v;

                edges.add(new Pair<>(u, v));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
