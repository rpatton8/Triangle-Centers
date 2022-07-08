package ryan.trianglecenters;

import java.awt.Graphics;

public class Triangle {

    private int x1, y1, x2, y2, x3, y3;  // by default set to 0
    private boolean pt1, pt2, pt3;   // by default set to false
    private Point A, B, C;        //  by default set to null
    private boolean initialized;  //  by default set to false
    private int dragX, dragY;  //  by default set to 0
    private Point incenter, centroid, circumcenter, orthocenter, ninePointCenter;   //  by default set to null
    private double inscribedRadius, circumscribedRadius, ninePointRadius;  // by default set to 0..0
    public static int CANVAS_WIDTH = 654;
    public static int CANVAS_HEIGHT = 678;
    public static int CONTROL_WIDTH = 250;
    public static int PIXELS_PER_UNIT = 50;
    private static int POINT_SIZE = 8;

    public void addPoint(int x, int y, String point) {
        if (initialized && point == null) {
            modifyExistingPoint(x, y, null);
            return;
        } else if (initialized && point.equals("A")) {
            System.out.println("HereA1");
            modifyExistingPoint(x, y, "A");
            return;
        } else if (initialized && point.equals("B")) {
            System.out.println("HereB");
            modifyExistingPoint(x, y, "B");
            return;
        } else if (initialized && point.equals("C")) {
            modifyExistingPoint(x, y, "C");
            return;
        }
        if (!initialized) {
            if((A==null && point==null) || (point != null && point.equals("A"))) {
                System.out.println("HereA2");
                x1 = x;
                TriangleCenters.x1.setText(Double.toString(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT));
                TriangleCenters.x1Val = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
                y1 = y;
                TriangleCenters.y1.setText(Double.toString((CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT));
                TriangleCenters.y1Val = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;
                pt1 = true;
                A = new Point(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT, (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT);
            } else if ((B==null && point==null) || (point != null && point.equals("B"))) {
                x2 = x;
                TriangleCenters.x2.setText(Double.toString(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT));
                TriangleCenters.x2Val = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
                y2 = y;
                TriangleCenters.y2.setText(Double.toString((CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT));
                TriangleCenters.y2Val = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;
                pt2 = true;
                B = new Point(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT, (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT);
            } else if ((C==null && point ==null) || (point != null && point.equals("C"))) {
                x3 = x;
                TriangleCenters.x3.setText(Double.toString(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT));
                TriangleCenters.x3Val = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
                y3 = y;
                TriangleCenters.y3.setText(Double.toString((CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT));
                TriangleCenters.y3Val = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;
                pt3 = true;
                C = new Point(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT, (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT);
            }
            if (A != null & B != null & C != null) initialized = true;
        }
        if(initialized) {
            if (notCollinear()) calculateTriangleCenters();
            else {
                System.out.println("Collinear");
                displayCollinear();
            }
        }
    }

    public void modifyExistingPoint(int x, int y, String point) {
        if ((point != null && point.equals("A")) || (pt1 && java.lang.Math.abs(x - x1) <= 2 * POINT_SIZE && java.lang.Math.abs(y - y1) <= 2 * POINT_SIZE)) {
            System.out.println("Modifying point A");
            x1 = x;
            TriangleCenters.x1.setText(Double.toString(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT));
            TriangleCenters.x1Val = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
            y1 = y;
            TriangleCenters.y1.setText(Double.toString((CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT));
            TriangleCenters.y1Val = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;
            A.x = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
            A.y = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;

        } else if ((point != null && point.equals("B")) || (pt2 && java.lang.Math.abs(x - x2) <= 2 * POINT_SIZE && java.lang.Math.abs(y - y2) <= 2 * POINT_SIZE)) {
            System.out.println("Modifying point B");
            x2 = x;
            TriangleCenters.x2.setText(Double.toString(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT));
            TriangleCenters.x2Val = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
            y2 = y;
            TriangleCenters.y2.setText(Double.toString((CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT));
            TriangleCenters.y2Val = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;
            B.x = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
            B.y = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;
        } else if ((point != null && point.equals("C")) || (pt3 && java.lang.Math.abs(x - x3) <= 2 * POINT_SIZE && java.lang.Math.abs(y - y3) <= 2 * POINT_SIZE)) {
            System.out.println("Modifying point C");
            x3 = x;
            TriangleCenters.x3.setText(Double.toString(((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT));
            TriangleCenters.x3Val = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
            y3 = y;
            TriangleCenters.y3.setText(Double.toString((CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT));
            TriangleCenters.y3Val = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;
            C.x = ((double)x-CANVAS_WIDTH/2)/PIXELS_PER_UNIT;
            C.y = (CANVAS_HEIGHT/2-(double)y)/PIXELS_PER_UNIT;
        } else if(initialized && pointInTriangle(x, y)) {  // if point is inside triangle when dragged
            System.out.println("Point in Triangle! Modifying all points.");
            if (java.lang.Math.abs(x - dragX) <= 2 * POINT_SIZE && java.lang.Math.abs(y - dragY) <= 2 * POINT_SIZE) {
                initialized = false;
                modifyExistingPoint(x1 + x - dragX, y1 + y - dragY, "A");
                modifyExistingPoint(x2 + x - dragX, y2 + y - dragY, "B");
                modifyExistingPoint(x3 + x - dragX, y3 + y - dragY, "C");
                initialized = true;
            }
            dragX = x;
            dragY = y;
        } else return;
        if(initialized) {
            if (notCollinear()) calculateTriangleCenters();
            else {
                System.out.println("Collinear");
                displayCollinear();
            }
        }
    }

    private  boolean pointInTriangle(int x, int y) {
        double s = y1 * x3 - x1 * y3 + (y3 - y1) * x + (x1 - x3) * y;
        double t = x1 * y2 - y1 * x2 + (y1 - y2) * x + (x2 - x1) * y;
        if ((s < 0) != (t < 0)) return false;

        double A = -y2 * x3 + y1 * (x3 - x2) + x1 * (y2 - y3) + x2 * y3;
        if (A < 0.0) {
            s = -s;
            t = -t;
            A = -A;
        }
        return s > 0 && t > 0 && (s + t) <= A;
    }

    private boolean notCollinear() {
        double area = java.lang.Math.abs((A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y - B.y)) / 2);
        return ! ((java.lang.Math.round(10.0 * area) / 10.0) == 0.00);
    }

    private void displayCollinear() {
        double a = sqrt( (C.x-B.x) * (C.x-B.x) + (C.y-B.y) * (C.y-B.y) );
        TriangleCenters.lengthBC.setText("length of BC = " + round(a));
        double b = sqrt( (C.x-A.x) * (C.x-A.x) + (C.y-A.y) * (C.y-A.y) );
        TriangleCenters.lengthCA.setText("length of CA = " + round(b));
        double c = sqrt( (B.x-A.x) * (B.x-A.x) + (B.y-A.y) * (B.y-A.y) );
        TriangleCenters.lengthAB.setText("length of AB = " + round(c));
        TriangleCenters.perimeter.setText("perimeter = NA");
        TriangleCenters.area.setText("area = NA");
        TriangleCenters.incenter.setText("Incenter: (NA, NA)");
        TriangleCenters.centroid.setText("Centroid: (NA, NA)");
        TriangleCenters.circumcenter.setText("Circumcenter: (NA, NA)");
        TriangleCenters.orthocenter.setText("Orthocenter: (NA, NA)");
        TriangleCenters.ninePointCenter.setText("9-Point Center: (NA, NA)");
    }

    private void calculateTriangleCenters() {
        double a = sqrt( (C.x-B.x) * (C.x-B.x) + (C.y-B.y) * (C.y-B.y) );
        TriangleCenters.lengthBC.setText("length of BC = " + round(a));
        double b = sqrt( (C.x-A.x) * (C.x-A.x) + (C.y-A.y) * (C.y-A.y) );
        TriangleCenters.lengthCA.setText("length of CA = " + round(b));
        double c = sqrt( (B.x-A.x) * (B.x-A.x) + (B.y-A.y) * (B.y-A.y) );
        TriangleCenters.lengthAB.setText("length of AB = " + round(c));
        double perimeter = a + b + c;
        TriangleCenters.perimeter.setText("perimeter = " + round(perimeter));
        double area = java.lang.Math.abs((A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y - B.y)) / 2);
        TriangleCenters.area.setText("area = " + round(area));
        calculateIncenter(a, b, c);
        TriangleCenters.incenter.setText("Incenter: (" + round(incenter.x) + ", " + round(incenter.y) + ")");
        calculateCentroid();
        TriangleCenters.centroid.setText("Centroid: (" + round(centroid.x) + ", " + round(centroid.y) + ")");
        calculateCircumcenter();
        TriangleCenters.circumcenter.setText("Circumcenter: (" + round(circumcenter.x) + ", " + round(circumcenter.y) + ")");
        calculateOrthocenter();
        TriangleCenters.orthocenter.setText("Orthocenter: (" + round(orthocenter.x) + ", " + round(orthocenter.y) + ")");
        calculateNinePointCircle();
        TriangleCenters.ninePointCenter.setText("9-Point Center: (" + round(ninePointCenter.x) + ", " + round(ninePointCenter.y) + ")");
    }

    private double sqrt(double d) {
        return java.lang.Math.sqrt(d);
    }

    private double round(double d) {
        return java.lang.Math.round(1000.0 * d) / 1000.0;
    }

    private void calculateIncenter(double a, double b, double c) {
        double x = (a * A.x + b * B.x + c * C.x) / (a + b + c);
        double y = (a * A.y + b * B.y + c * C.y) / (a + b + c);
        if (incenter != null) {
            incenter.x = x;
            incenter.y = y;
        } else incenter = new Point(x, y);
        inscribedRadius = java.lang.Math.abs((A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y -B.y)) / (a + b + c));
        System.out.println("Calculating Incenter: (" + incenter.x + ", " + incenter.y + "), r = " + inscribedRadius);
    }

    private void calculateCentroid() {
        double x = (A.x + B.x + C.x) / 3;
        double y = (A.y + B.y + C.y) / 3;
        if (centroid != null) {
            centroid.x = x;
            centroid.y = y;
        } else centroid = new Point(x, y);
        System.out.println("Calculating Centroid: (" + centroid.x + ", " + centroid.y + ")");
    }
    
    private void calculateCircumcenter() {
        double x = 0.0;
        double y = 0.0;
        if (!(A.y==C.y) && !(B.y==C.y)) {
            x = ((B.x + C.x) * (B.x - C.x) / (2 * (C.y - B.y)) - (A.x + C.x) * (A.x - C.x) / (2 * (C.y-A.y))
                    + (A.y - B.y) / 2) / (((B.x - C.x) / (C.y - B.y)) - ((A.x - C.x) / (C.y - A.y)));
            y = (B.x - C.x) * (x - (B.x + C.x) / 2) / (C.y-B.y) + (B.y + C.y) / 2;
        } else if (!(A.y==B.y) && !(A.y==C.y)) {
            x = ((A.x + C.x) * (A.x - C.x) / (2 * (C.y - A.y)) - (A.x + B.x) * (A.x - B.x) / (2 * (B.y-A.y))
                    + (B.y - C.y) / 2) / (((A.x - C.x) / (C.y - A.y)) - ((A.x - B.x) / (B.y - A.y)));
            y = (A.x - C.x) * (x - (A.x + C.x) / 2) / (C.y-A.y) + (A.y + C.y) / 2;
        } else if (!(B.y==C.y) && !(A.y==B.y)) {
            x = ((A.x + B.x) * (A.x - B.x) / (2 * (B.y - A.y)) - (B.x + C.x) * (B.x - C.x) / (2 * (C.y-B.y))
                    + (C.y - A.y) / 2) / (((A.x - B.x) / (B.y - A.y)) - ((B.x - C.x) / (C.y - B.y)));
            y = (A.x - B.x) * (x - (A.x + B.x) / 2) / (B.y-A.y) + (A.y + B.y) / 2;
        }
        if (circumcenter != null) {
            circumcenter.x = x;
            circumcenter.y = y;
        } else circumcenter = new Point(x, y);
        circumscribedRadius = sqrt( (circumcenter.x - A.x) * (circumcenter.x - A.x) + (circumcenter.y - A.y) * (circumcenter.y - A.y) );
        System.out.println("Calculating Circumcenter: (" + circumcenter.x + ", " + circumcenter.y + "), r = " + circumscribedRadius);
    }

    private void calculateOrthocenter() {
        double x = 0.0;
        double y = 0.0;
        if (!(A.y==C.y) && !(B.y==C.y)) {
            x = (A.x * (B.x - C.x) / (C.y - B.y) - B.x * (A.x - C.x)/ (C.y-A.y) + B.y - A.y)
                    / (((B.x - C.x) / (C.y - B.y)) - ((A.x - C.x) / (C.y - A.y)));
            y = (B.x - C.x) * (x - A.x) / (C.y-B.y) + A.y;
        } else if (!(A.y==B.y) && !(A.y==C.y)) {
            x = (B.x * (A.x - C.x) / (C.y - A.y) - C.x * (A.x - B.x)/ (B.y-A.y) + C.y - B.y)
                    / (((A.x - C.x) / (C.y - A.y)) - ((A.x - B.x) / (B.y - A.y)));
            y = (A.x - C.x) * (x - B.x) / (C.y-A.y) + B.y;
        } else if (!(B.y==C.y) && !(A.y==B.y)) {
            x = (C.x * (A.x - B.x) / (B.y - A.y) - A.x * (B.x - C.x)/ (C.y-B.y) + A.y - C.y)
                    / (((A.x - B.x) / (B.y - A.y)) - ((B.x - C.x) / (C.y - B.y)));
            y = (A.x - B.x) * (x - C.x) / (B.y-A.y) + C.y;
        }
        if (orthocenter != null) {
            orthocenter.x = x;
            orthocenter.y = y;
        } else orthocenter = new Point(x, y);
        System.out.println("Calculating Orthocenter: (" + orthocenter.x + ", " + orthocenter.y + ")");
    }

    private void calculateNinePointCircle() {
        if (ninePointCenter != null) {
            ninePointCenter.x = (circumcenter.x + orthocenter.x) / 2;
            ninePointCenter.y = (circumcenter.y + orthocenter.y) / 2;
        } else ninePointCenter = new Point((circumcenter.x + orthocenter.x) / 2, (circumcenter.y + orthocenter.y) / 2);
        ninePointRadius = sqrt( (ninePointCenter.x - (A.x + B.x) / 2) * (ninePointCenter.x - (A.x + B.x) / 2)
                + (ninePointCenter.y - (A.y + B.y) / 2) * (ninePointCenter.y - (A.y + B.y) / 2) );
        System.out.println("9 Point Circle: center: (" + ninePointCenter.x + ", " + ninePointCenter.y + "), r = " + ninePointRadius);
    }

    public void printPoints() {
        if(A!=null) {
            System.out.println("A: " + A.x + ", " + A.y);
        }
        if (B!=null) {
            System.out.println("B: " + B.x + ", " + B.y);
        }
        if (C!=null) {
            System.out.println("C: " + C.x + ", " + C.y);
        }
    }

    public void draw(Graphics g) {
        //--Points of the triangle------------------------------------------------------------------
        if (pt1) g.fillOval(x1 - POINT_SIZE / 2, y1 - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
        if (pt2) g.fillOval(x2 - POINT_SIZE / 2, y2 - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
        if (pt3) g.fillOval(x3 - POINT_SIZE / 2, y3 - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
        if (initialized) {
            //--Sides of the triangle----------
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x2, y2, x3, y3);
            g.drawLine(x3, y3, x1, y1);
        }
        if (initialized && notCollinear()) {
            //--Incenter--------------------------------------------------------------------------
            g.fillOval((int)(incenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2) - POINT_SIZE / 2,
                    (int)(CANVAS_HEIGHT / 2 - incenter.y * PIXELS_PER_UNIT) - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
            //--Centroid--------------------------------------------------------------------------
            g.fillOval((int)(centroid.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2) - POINT_SIZE / 2,
                    (int)(CANVAS_HEIGHT / 2 - centroid.y * PIXELS_PER_UNIT) - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
            //--Circumcenter----------------------------------------------------------------------
            g.fillOval((int)(circumcenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2) - POINT_SIZE / 2,
                    (int)(CANVAS_HEIGHT / 2 - circumcenter.y * PIXELS_PER_UNIT) - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
            //--Orthocenter--------------------------------------------------------------------------
            g.fillOval((int)(orthocenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2) - POINT_SIZE / 2,
                    (int)(CANVAS_HEIGHT / 2 - orthocenter.y * PIXELS_PER_UNIT) - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
            //--Euler's Line----------------------------------------------------------------------
            g.drawLine((int)(circumcenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2),
                    (int)(CANVAS_HEIGHT / 2 - circumcenter.y * PIXELS_PER_UNIT),
                    (int)(orthocenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2),
                    (int)(CANVAS_HEIGHT / 2 - orthocenter.y * PIXELS_PER_UNIT));
            //--Inscribed Circle---------------------------------------------------------------------------------
            g.drawOval((int)(incenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2 - inscribedRadius * PIXELS_PER_UNIT),
                    (int)(CANVAS_HEIGHT / 2 - incenter.y * PIXELS_PER_UNIT - inscribedRadius * PIXELS_PER_UNIT),
                    (int)(2 * inscribedRadius * PIXELS_PER_UNIT), (int)(2 * inscribedRadius * PIXELS_PER_UNIT));
            //--Circumscribed Circle-----------------------------------------------------------------------------
            g.drawOval((int)(circumcenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2 - circumscribedRadius * PIXELS_PER_UNIT),
                    (int)(CANVAS_HEIGHT / 2 - circumcenter.y * PIXELS_PER_UNIT - circumscribedRadius * PIXELS_PER_UNIT),
                    (int)(2 * circumscribedRadius * PIXELS_PER_UNIT), (int)(2 * circumscribedRadius * PIXELS_PER_UNIT));
            //--Midpoints of each side (part of 9-point circle)----------------------------------------------
            g.fillOval((x1 + x2 - POINT_SIZE) / 2, (y1 + y2 - POINT_SIZE) / 2, POINT_SIZE, POINT_SIZE);
            g.fillOval((x1 + x3 - POINT_SIZE) / 2, (y1 + y3 - POINT_SIZE) / 2, POINT_SIZE, POINT_SIZE);
            g.fillOval((x2 + x3 - POINT_SIZE) / 2, (y2 + y3 - POINT_SIZE) / 2, POINT_SIZE, POINT_SIZE);
            //--Midpoints of orthocenter lines (part of 9-point circle)--------------------------------------
            int ox = (int) (orthocenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2);
            int oy = (int) (CANVAS_HEIGHT / 2 - orthocenter.y * PIXELS_PER_UNIT);
            g.fillOval((x1 + ox - POINT_SIZE) / 2, (y1 + oy - POINT_SIZE) / 2, POINT_SIZE, POINT_SIZE);
            g.fillOval((x2 + ox - POINT_SIZE) / 2, (y2 + oy - POINT_SIZE) / 2, POINT_SIZE, POINT_SIZE);
            g.fillOval((x3 + ox - POINT_SIZE) / 2, (y3 + oy - POINT_SIZE) / 2, POINT_SIZE, POINT_SIZE);
            //--Feet of each altitude (part of 9-point circle)--------------------------------------
            /*
            double altIX = ((A.x-B.x)*(A.x*C.x+A.y*C.y-orthocenter.x*A.x-orthocenter.y*A.y) + (orthocenter.y-C.y)*(A.x*B.y-A.y*B.x)) / ((C.x-orthocenter.x)*(A.x-B.x) - (C.y-orthocenter.y)*(B.y-A.y));
            double altIY = ((A.y-B.y)*(A.x*C.x+A.y*C.y-orthocenter.x*A.x-orthocenter.y*A.y) + (C.x-orthocenter.x)*(A.x*B.y-A.y*B.x)) / ((C.x-orthocenter.x)*(A.x-B.x) - (C.y-orthocenter.y)*(B.y-A.y));
            g.fillOval((int)(altIX * PIXELS_PER_UNIT + CANVAS_WIDTH / 2) - POINT_SIZE / 2,
                    (int)(CANVAS_HEIGHT / 2 - altIY * PIXELS_PER_UNIT) - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
            double altJX = ((B.x-C.x)*(B.x*A.x+B.y*A.y-orthocenter.x*B.x-orthocenter.y*B.y) + (orthocenter.y-A.y)*(B.x*C.y-B.y*C.x)) / ((A.x-orthocenter.x)*(B.x-C.x) - (A.y-orthocenter.y)*(C.y-B.y));
            double altJY = ((B.y-C.y)*(B.x*A.x+B.y*A.y-orthocenter.x*B.x-orthocenter.y*B.y) + (A.x-orthocenter.x)*(B.x*C.y-B.y*C.x)) / ((A.x-orthocenter.x)*(B.x-C.x) - (A.y-orthocenter.y)*(C.y-B.y));
            g.fillOval((int)(altJX * PIXELS_PER_UNIT + CANVAS_WIDTH / 2) - POINT_SIZE / 2,
                    (int)(CANVAS_HEIGHT / 2 - altJY * PIXELS_PER_UNIT) - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
            //g.fillOval(altitude3);*/
            //--9 Point Circle (center is midpoint of orthocenter and circumcenter)------------------------------
            g.fillOval((int)(ninePointCenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2) - POINT_SIZE / 2,
                    (int)(CANVAS_HEIGHT / 2 - ninePointCenter.y * PIXELS_PER_UNIT) - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
            g.drawOval((int)(ninePointCenter.x * PIXELS_PER_UNIT + CANVAS_WIDTH / 2 - ninePointRadius * PIXELS_PER_UNIT),
                    (int)(CANVAS_HEIGHT / 2 - ninePointCenter.y * PIXELS_PER_UNIT - ninePointRadius * PIXELS_PER_UNIT),
                    (int)(2 * ninePointRadius * PIXELS_PER_UNIT), (int)(2 * ninePointRadius * PIXELS_PER_UNIT));
        }
    }

}