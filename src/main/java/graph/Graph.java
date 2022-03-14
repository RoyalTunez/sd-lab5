package graph;

import drawing.DrawingApi;

import java.nio.file.Path;

public interface Graph {
    void readFromFile(Path file);

    void drawGraph(DrawingApi drawingApi);
}