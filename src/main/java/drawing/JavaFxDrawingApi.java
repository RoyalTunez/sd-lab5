package drawing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class JavaFxDrawingApi extends Application implements DrawingApi {
    private static List<Consumer<GraphicsContext>> drawers = new ArrayList<>();

    private static int drawingAreaWidth;
    private static int drawingAreaHeight;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFxDrawingApi");
        Group root = new Group();
        Canvas canvas = new Canvas(drawingAreaWidth, drawingAreaHeight);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        drawers.forEach(drawer -> drawer.accept(graphicsContext));

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public JavaFxDrawingApi(int drawingAreaWidth, int drawingAreaHeight) {
        JavaFxDrawingApi.drawingAreaWidth = drawingAreaWidth;
        JavaFxDrawingApi.drawingAreaHeight = drawingAreaHeight;
    }

    public void visualize() {
        launch();
    }

    @Override
    public int getDrawingAreaWidth() {
        return drawingAreaWidth;
    }

    @Override
    public int getDrawingAreaHeight() {
        return drawingAreaHeight;
    }

    @Override
    public void drawCircle(float x, float y, float r) {
        drawers.add(graphicsContext -> {
            graphicsContext.setFill(Color.ORANGE);
            graphicsContext.fillOval(x - r, y - r, r * 2., r * 2);
        });

        drawers.add(graphicsContext -> {
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.strokeOval(x - r, y - r, r * 2., r * 2);
        });
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        drawers.add(graphicsContext -> {
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.strokeLine(x1, y1, x2, y2);
        });
    }
}