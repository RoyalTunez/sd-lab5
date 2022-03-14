import drawing.AwtDrawingApi;
import drawing.DrawingApi;
import drawing.JavaFxDrawingApi;
import graph.Graph;
import graph.ListGraph;
import graph.MatrixGraph;

import java.awt.*;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Should be 4 arguments: Api[awt | fx] GraphType[matrix | list] InputFile");
        }

        DrawingApi drawingApi;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        if (args[0].equals("fx")) {
            drawingApi = new JavaFxDrawingApi(width / 2, height / 2);
        } else {
            drawingApi = new AwtDrawingApi(width / 2, height / 2) ;
        }

        Graph g;

        if (args[1].equals("matrix")) {
            g = new MatrixGraph();
        } else {
            g = new ListGraph();
        }

        g.readFromFile(Paths.get(args[2]));

        g.drawGraph(drawingApi);
    }
}
