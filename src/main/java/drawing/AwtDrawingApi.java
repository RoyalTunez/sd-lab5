package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AwtDrawingApi extends JFrame implements DrawingApi {
    private List<Consumer<Graphics2D>> drawers = new ArrayList<>();

    public AwtDrawingApi(int drawingAreaWidth, int drawingAreaHeight) {
        super("AwtDrawingApi");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        JPanel drawingArea = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                drawers.forEach(drawer -> drawer.accept((Graphics2D) g));
            }
        };

        getContentPane().add(drawingArea);
        getContentPane().setPreferredSize(new Dimension(drawingAreaWidth, drawingAreaHeight));
        pack();
    }

    @Override
    public int getDrawingAreaWidth() {
        return getContentPane().getWidth();
    }

    @Override
    public int getDrawingAreaHeight() {
        return getContentPane().getHeight();
    }

    @Override
    public void drawCircle(float x, float y, float r) {
        drawers.add(g -> {
            g.setPaint(Color.ORANGE);
            g.fill(new Ellipse2D.Float(x - r, y - r, r * 2, r * 2));
        });

        drawers.add(g -> {
            g.setPaint(Color.BLACK);
            g.draw(new Ellipse2D.Float(x - r, y - r, r * 2, r * 2));
        });
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        drawers.add(g -> {
            g.setPaint(Color.BLACK);
            g.draw(new Line2D.Float(x1, y1, x2, y2));
        });
    }

    @Override
    public void visualize() {
        setVisible(true);
    }
}
