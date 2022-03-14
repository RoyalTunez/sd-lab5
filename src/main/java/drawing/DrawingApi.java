package drawing;

public interface DrawingApi {

    int getDrawingAreaWidth();

    int getDrawingAreaHeight();

    void drawCircle(float x, float y, float r);

    void drawLine(float x1, float y1, float x2, float y2);

    void visualize();
}