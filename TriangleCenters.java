package ryan.trianglecenters;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TriangleCenters extends JFrame {

    public static JTextField x1, y1, x2, y2, x3, y3;
    public static Double x1Val, y1Val, x2Val, y2Val, x3Val, y3Val;
    public static JLabel lengthAB, lengthBC, lengthCA, perimeter, area;
    public static JLabel incenter, centroid, circumcenter, orthocenter, ninePointCenter;

    public static void main(String[] arguments) {

        Triangle triangle = new Triangle();

        JFrame window = new JFrame("Triangle Centers");
        window.setLayout(new BorderLayout());
        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                triangle.draw(g);
            }
        };
        window.add(panel, BorderLayout.CENTER);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse Clicked: (" + e.getX() + ", " + e.getY() + ")");
                triangle.addPoint(e.getX(), e.getY(), null);
                triangle.printPoints();
                panel.repaint();
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("Mouse Dragged: (" + e.getX() + ", " + e.getY() + ")");
                triangle.modifyExistingPoint(e.getX(), e.getY(), null);
                triangle.printPoints();
                panel.repaint();
            }
        });
        panel.setBackground(Color.WHITE);

        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.PAGE_AXIS));
        input.setPreferredSize(new Dimension(Triangle.CONTROL_WIDTH, Triangle.CANVAS_HEIGHT / 4));

        JPanel inputA = new JPanel();
        inputA.setLayout(new BoxLayout(inputA, BoxLayout.LINE_AXIS));
        JPanel inputAwrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        inputAwrapper.add(inputA);

        x1 = new JTextField(4);
        x1.setMaximumSize(new Dimension(100,30));
        x1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                x1Val = tryParse(x1.getText());
                if (x1Val != null) System.out.println("X1 coord:" + x1Val);
                else System.out.println("Invalid entry");
                if (x1Val != null && y1Val != null) {
                    int x1Cord = (int) (x1Val * Triangle.PIXELS_PER_UNIT + Triangle.CANVAS_WIDTH / 2);
                    int y1Cord = (int) (Triangle.CANVAS_HEIGHT / 2 - y1Val * Triangle.PIXELS_PER_UNIT);
                    triangle.addPoint(x1Cord, y1Cord, "A");
                    triangle.printPoints();
                    panel.repaint();
                }
            }
        });
        inputA.add(x1);

        y1 = new JTextField(4);
        y1.setMaximumSize(new Dimension(100,30));
        y1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                y1Val = tryParse(y1.getText());
                if (y1Val != null) System.out.println("Y1 coord:" + y1Val);
                else System.out.println("Invalid entry");
                if (x1Val != null && y1Val != null) {
                    int x1Cord = (int) (x1Val * Triangle.PIXELS_PER_UNIT + Triangle.CANVAS_WIDTH / 2);
                    int y1Cord = (int) (Triangle.CANVAS_HEIGHT / 2 - y1Val * Triangle.PIXELS_PER_UNIT);
                    triangle.addPoint(x1Cord, y1Cord, "A");
                    triangle.printPoints();
                    panel.repaint();
                }
            }
        });
        inputA.add(y1);

        JPanel inputB = new JPanel();
        inputB.setLayout(new BoxLayout(inputB, BoxLayout.LINE_AXIS));
        JPanel inputBwrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        inputBwrapper.add(inputB);

        x2 = new JTextField(4);
        x2.setMaximumSize(new Dimension(100,30));
        x2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                x2Val = tryParse(x2.getText());
                if (x2Val != null) System.out.println("X2 coord:" + x2Val);
                else System.out.println("Invalid entry");
                if (x2Val != null && y2Val != null) {
                    int x2Cord = (int) (x2Val * Triangle.PIXELS_PER_UNIT + Triangle.CANVAS_WIDTH / 2);
                    int y2Cord = (int) (Triangle.CANVAS_HEIGHT / 2 - y2Val * Triangle.PIXELS_PER_UNIT);
                    triangle.addPoint(x2Cord, y2Cord, "B");
                    triangle.printPoints();
                    panel.repaint();
                }
            }
        });
        inputB.add(x2);

        y2 = new JTextField(4);
        y2.setMaximumSize(new Dimension(100,30));
        y2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                y2Val = tryParse(y2.getText());
                if (y2Val != null) System.out.println("Y2 coord:" + y2Val);
                else System.out.println("Invalid entry");
                if (x2Val != null && y2Val != null) {
                    int x2Cord = (int) (x2Val * Triangle.PIXELS_PER_UNIT + Triangle.CANVAS_WIDTH / 2);
                    int y2Cord = (int) (Triangle.CANVAS_HEIGHT / 2 - y2Val * Triangle.PIXELS_PER_UNIT);
                    triangle.addPoint(x2Cord, y2Cord, "B");
                    triangle.printPoints();
                    panel.repaint();
                }
            }
        });
        inputB.add(y2);

        JPanel inputC = new JPanel();
        inputC.setLayout(new BoxLayout(inputC, BoxLayout.LINE_AXIS));
        JPanel inputCwrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        inputCwrapper.add(inputC);

        x3 = new JTextField(4);
        x3.setMaximumSize(new Dimension(100,30));
        x3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                x3Val = tryParse(x3.getText());
                if (x3Val != null) System.out.println("X3 coord:" + x3Val);
                else System.out.println("Invalid entry");
                if (x3Val != null && y3Val != null) {
                    int x3Cord = (int) (x3Val * Triangle.PIXELS_PER_UNIT + Triangle.CANVAS_WIDTH / 2);
                    int y3Cord = (int) (Triangle.CANVAS_HEIGHT / 2 - y3Val * Triangle.PIXELS_PER_UNIT);
                    triangle.addPoint(x3Cord, y3Cord, "C");
                    triangle.printPoints();
                    panel.repaint();
                }
            }
        });
        inputC.add(x3);

        y3 = new JTextField(4);
        y3.setMaximumSize(new Dimension(100,30));
        y3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                y3Val = tryParse(y3.getText());
                if (y3Val != null) System.out.println("Y3 coord:" + y3Val);
                else System.out.println("Invalid entry");
                if (x3Val != null && y3Val != null) {
                    int x3Cord = (int) (x3Val * Triangle.PIXELS_PER_UNIT + Triangle.CANVAS_WIDTH / 2);
                    int y3Cord = (int) (Triangle.CANVAS_HEIGHT / 2 - y3Val * Triangle.PIXELS_PER_UNIT);
                    triangle.addPoint(x3Cord, y3Cord, "C");
                    triangle.printPoints();
                    panel.repaint();
                }
            }
        });
        inputC.add(y3);

        input.add(inputAwrapper);
        input.add(inputBwrapper);
        input.add(inputCwrapper);

        JPanel output1 = new JPanel();
        output1.setLayout(new BoxLayout(output1, BoxLayout.PAGE_AXIS));
        output1.setPreferredSize(new Dimension(Triangle.CONTROL_WIDTH, Triangle.CANVAS_HEIGHT / 3));

        lengthAB = new JLabel("length of AB = " + 0);
        JPanel lengthABwrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        lengthABwrapper.add(lengthAB);
        output1.add(lengthABwrapper);

        lengthBC = new JLabel("length of BA = " + 0);
        JPanel lengthBCwrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        lengthBCwrapper.add(lengthBC);
        output1.add(lengthBCwrapper);

        lengthCA = new JLabel("length of CA = " + 0);
        JPanel lengthCAwrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        lengthCAwrapper.add(lengthCA);
        output1.add(lengthCAwrapper);

        perimeter = new JLabel("perimeter = " + 0);
        JPanel perimeterWrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        perimeterWrapper.add(perimeter);
        output1.add(perimeterWrapper);

        area = new JLabel("area = " + 0);
        JPanel areaWrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        areaWrapper.add(area);
        output1.add(areaWrapper);

        JPanel output2 = new JPanel();
        output2.setLayout(new BoxLayout(output2, BoxLayout.PAGE_AXIS));
        output2.setPreferredSize(new Dimension(Triangle.CONTROL_WIDTH, Triangle.CANVAS_HEIGHT / 3));

        incenter = new JLabel("Incenter: (" + 0 + ", " + 0 + ")");
        JPanel incenterWrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        incenterWrapper.add(incenter);
        output2.add(incenterWrapper);

        centroid = new JLabel("Centroid: (" + 0 + ", " + 0 + ")");
        JPanel centroidWrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        centroidWrapper.add(centroid);
        output2.add(centroidWrapper);

        circumcenter = new JLabel("Circumcenter: (" + 0 + ", " + 0 + ")");
        JPanel circumcenterWrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        circumcenterWrapper.add(circumcenter);
        output2.add(circumcenterWrapper);

        orthocenter = new JLabel("Orthocenter: (" + 0 + ", " + 0 + ")");
        JPanel orthocenterWrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        orthocenterWrapper.add(orthocenter);
        output2.add(orthocenterWrapper);

        ninePointCenter = new JLabel("9-Point Center: (" + 0 + ", " + 0 + ")");
        JPanel ninePointCenterWrapper = new JPanel( new FlowLayout(FlowLayout.LEADING, 16, 0) );
        ninePointCenterWrapper.add(ninePointCenter);
        output2.add(ninePointCenterWrapper);

        JPanel sideWindow = new JPanel();
        sideWindow.setLayout(new BoxLayout(sideWindow, BoxLayout.PAGE_AXIS));
        sideWindow.setPreferredSize(new Dimension(Triangle.CONTROL_WIDTH, Triangle.CANVAS_HEIGHT));
        sideWindow.add(input, BorderLayout.CENTER);
        sideWindow.add(output1, BorderLayout.EAST);
        sideWindow.add(output2, BorderLayout.SOUTH);
        window.add(sideWindow, BorderLayout.EAST);

        window.setSize(Triangle.CANVAS_WIDTH + Triangle.CONTROL_WIDTH, Triangle.CANVAS_HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }

    public static Double tryParse(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}