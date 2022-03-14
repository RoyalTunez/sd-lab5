package graph;

import drawing.DrawingApi;
import javafx.util.Pair;

public abstract class AbstractGraph implements Graph {
    protected DrawingApi drawingApi;

    public void drawGraph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;

        drawEdges();

        drawVertices();

        drawingApi.visualize();
    }

    public abstract int getVertexAmount();

    public abstract void drawEdges();

    private void drawVertices() {
        for (int i = 0; i < getVertexAmount(); i++) {
            Pair<Integer, Integer> vertexPos = getVertexPosition(i);
            drawingApi.drawCircle(vertexPos.getKey(), vertexPos.getValue(), getVertexRadius());
        }
    }

    private float getVertexRadius() {
        return (float) (getGraphRadius() * Math.sin(Math.PI / (getVertexAmount() * 2)));
    }

    private Pair<Integer, Integer> getVertexPosition(int i) {
        float graphRadius = getGraphRadius();
        double phi = Math.PI * 2 * i / getVertexAmount();
        int x = (int) (Math.sin(phi) * graphRadius + drawingApi.getDrawingAreaWidth() / 2);
        int y = (int) (-Math.cos(phi) * graphRadius + drawingApi.getDrawingAreaHeight() / 2);
        return new Pair<>(x, y);
    }

    private float getGraphRadius() {
        float c = (float) (1 + Math.sin(Math.PI / (getVertexAmount() * 2)));

        return Math.min(drawingApi.getDrawingAreaHeight(), drawingApi.getDrawingAreaWidth()) / 2 / c;
    }

    protected void drawEdge(int u, int v) {
        Pair<Integer, Integer> uPos = getVertexPosition(u);
        Pair<Integer, Integer> vPos = getVertexPosition(v);

        drawingApi.drawLine(uPos.getKey(), uPos.getValue(), vPos.getKey(), vPos.getValue());
    }
}
