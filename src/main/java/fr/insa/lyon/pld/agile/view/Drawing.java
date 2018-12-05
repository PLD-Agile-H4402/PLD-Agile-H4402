package fr.insa.lyon.pld.agile.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author nmesnard
 */
public class Drawing {
    
    public static Color getColor(int index, int count) {
        return Color.getHSBColor((float) index / count, 1.0f, 1.0f);
    }
    
    public static Color getColorBrighter(Color color) {
        float col[] = color.getRGBColorComponents(null);
        int r=(int)(col[0]*127), g=(int)(col[1]*127), b=(int)(col[2]*127);
        return new Color(r+128,g+128,b+128);
    }
    
    public static Color getColorDarker(Color color) {
        float col[] = color.getRGBColorComponents(null);
        int r=(int)(col[0]*127), g=(int)(col[1]*127), b=(int)(col[2]*127);
        return new Color(r,g,b);
    }
    
    protected static void drawLineThick(Graphics g, Point p1, Point p2) {
        for (int dx=-1; dx<=1; dx++) {
            for (int dy=-1; dy<=1; dy++) {
                g.drawLine(p1.x+dx, p1.y+dy, p2.x+dx, p2.y+dy);
            }
        }
    }
    protected static float drawLineArrows(Graphics g, Point p1, Point p2, float position) {
        float step = 150f;
        
        int vy = p2.y - p1.y;
        int vx = p2.x - p1.x;
        
        if ((vy == 0) && (vx == 0)) return position;
        
        float vlen = getVectorLength(vx,vy);
        float uvx = (float) vx/vlen;
        float uvy = (float) vy/vlen;
        
        int safety = 0;
        
        float total;
        float remaining = getPointsDistance(p1, p2);
        for (; (total = position+remaining) >= step;) {
            if (safety++ > 20) {
                System.out.println("DRAW LINE ARROWS");
                System.out.println("position: " + position);
                System.out.println("remaining: " + remaining);
                System.out.println("vx: " + vx);
                System.out.println("vy: " + vy);
                System.out.println("vlen: " + vlen);
                System.out.println("p1: " + p1.x + ", " + p1.y);
                System.out.println("p2: " + p2.x + ", " + p2.y);
                return position;
            }
            
            p1.x += ((step-position)*vx)/vlen;
            p1.y += ((step-position)*vy)/vlen;
            
            drawLineThick(g,
                new Point(p1.x+(int)((-uvy-uvx)*8), p1.y+(int)((uvx-uvy)*8)),
                new Point(p1.x, p1.y)
            );
            drawLineThick(g,
                new Point(p1.x, p1.y),
                new Point(p1.x+(int)((uvy-uvx)*8), p1.y+(int)((-uvx-uvy)*8))
            );
            
            position = 0;
        }
        
        return total; 
    }
    
    protected static float getPointsDistance(Point p1, Point p2) {
        int vx = p2.x - p1.x, vy = p2.y - p1.y;
        return getVectorLength(vx, vy);
    }
    protected static float getVectorLength(int vx, int vy) {
        return (float) Math.sqrt((double) ((vx*vx)+(vy*vy)));
    }
    
    protected static void drawLine(Graphics g, Point p1, Point p2) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
    
    protected static void drawDot(Graphics g, Point coords, int diameter) {
        g.fillOval((int) coords.getX()-diameter/2, (int) coords.getY()-diameter/2, diameter, diameter);
    }
    
    protected static void drawWarehouse(Graphics g, Point coords) {
        g.setColor(Color.black);
        drawDot(g, coords, 11);
    }
    
    protected static void drawDelivery(Graphics g, Point coords, Color color) {
        g.setColor(Drawing.getColorDarker(color));
        drawDot(g, coords, 11);
        g.setColor(color);
        drawDot(g, coords, 7);
    }
    
    protected static void drawSelectedNode(Graphics g, Point coords, Color color) {
        g.setColor(Color.black);
        drawDot(g, coords, 15);
        g.setColor(color);
        drawDot(g, coords, 11);
    }
    
}
