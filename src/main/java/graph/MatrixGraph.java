package graph;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Scanner;

public class MatrixGraph extends AbstractGraph {
    private boolean[][] matrix;

    @Override
    public int getVertexAmount() {
        return matrix.length;
    }

    @Override
    public void drawEdges() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (matrix[i][j]) {
                    drawEdge(i, j);
                }
            }
        }
    }

    @Override
    public void readFromFile(Path file) {
        try (Scanner scanner = new Scanner(file)) {
            int size = scanner.nextInt();
            matrix = new boolean[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = (scanner.nextInt() == 1);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
