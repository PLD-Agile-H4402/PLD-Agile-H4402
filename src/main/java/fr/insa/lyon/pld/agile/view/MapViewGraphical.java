package fr.insa.lyon.pld.agile.view;

import fr.insa.lyon.pld.agile.model.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.Point2D;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 *
 * @author nmesnard, tzhang
 */

public class MapViewGraphical extends JPanel implements MapView
{
    Map map;
    List<Delivery> deliveries;
    
    Boolean hasSize = false;
    Boolean hasData = false;
    
    Dimension preferred = null;
    
    Double latitudeMin;
    Double latitudeMax;
    Double longitudeMin;
    Double longitudeMax;
    
    Double ratioX;
    Double ratioY;
    Double ratio;
    
    Double deltaX;
    Double deltaY;
    
    Node sel = null;
    
    private MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            eventClicked(e);
        }
    };
    
    private ComponentListener resizeListener = new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
            eventResized();
        }
    };
    
    public MapViewGraphical()
    {
        this.addComponentListener(resizeListener);
        this.addMouseListener(mouseListener);
    }
    
    @Override
    public void setMap(Map newMap)
    {
        map = newMap;

        hasData = (map != null) && (map.getNodes() != null) && (!map.getNodes().isEmpty());
        
        if (hasData) {
            
            double totalDistance = 0.0;
            long totalCount = 0L;
            
            List<Double> latitudes = new ArrayList<>();
            List<Double> longitudes = new ArrayList<>();
            for (Node n : map.getNodes().values()) {
                latitudes.add(n.getLatitude());
                longitudes.add(n.getLongitude());
                
                for (Section s : n.getOutgoingSections()) {
                    totalDistance += getNodesDistance(n, s.getDestination());
                    totalCount++;
                }
            }
            
            latitudeMin = Collections.min(latitudes);
            latitudeMax = Collections.max(latitudes);
            longitudeMin = Collections.min(longitudes);
            longitudeMax = Collections.max(longitudes);
            
            eventResized();
            
            double avgDistance = totalDistance / totalCount;
            double preferredDistance = 15.0;
            
            // avgDistance / ratio = 10;
            // ratio / avgDistance = 1/10;
            // ratio = avgDistance/10;
            
            // ratio = coteDiff / taille
            // taille / coteDiff = 1 / ratio
            // taille = coteDiff / ratio
            
            preferred = new Dimension(
                (int) (preferredDistance * (longitudeMax - longitudeMin) / avgDistance),
                (int) (preferredDistance * (latitudeMax - latitudeMin) / avgDistance)
            );
            
        }
        else
        {
            preferred = null;
        }
        
        this.repaint();
    }
    
    @Override
    public void setDeliveries(List<Delivery> newDeliveries)
    {
        sel = null;
        
        // TODO
    }
    
    public void eventResized()
    {
        if (!hasData) return;
        
        ratioX = (longitudeMax - longitudeMin) / this.getWidth();
        ratioY = (latitudeMax - latitudeMin) / this.getHeight();
        ratio = (ratioX > ratioY ? ratioX : ratioY);
        
        deltaX = (this.getWidth() - (longitudeMax-longitudeMin)/ratio)/2;
        deltaY = (this.getHeight() - (latitudeMax-latitudeMin)/ratio)/2;
        
        hasSize = (ratio > 0);
    }
    
    public void eventClicked(MouseEvent e)
    {
        if (!(hasData && hasSize)) return;
        
        Point2D coord = getPixelToPoint(e.getX(),e.getY());
        
        double closestdistance = -1;
        Node closest = new Node(0, e.getX(), e.getY());
        for (Node n : map.getNodes().values()) {
            double distance = Math.pow((coord.getX() - n.getLongitude()), 2)
                            + Math.pow((coord.getY() - n.getLatitude()), 2);
            if (closestdistance < 0 || distance < closestdistance) {
                closestdistance = distance;
                closest = n;
            }
        }
        
        sel = closest;
        
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        if (!(hasData && hasSize)) return;
        
        if (sel != null) {
            int diameter = 15;
            Point coordssel = getCoordsToPixel(sel.getLongitude(),sel.getLatitude());
            g.drawOval((int) coordssel.getX()-diameter/2, (int) coordssel.getY()-diameter/2, diameter, diameter);
        }
        
        for (Node n : map.getNodes().values()) {
            Point coordsn = getCoordsToPixel(n.getLongitude(), n.getLatitude());
            
            for (Section s : n.getOutgoingSections()) {
                Node n2 = s.getDestination();
                Point coordsn2 = getCoordsToPixel(n2.getLongitude(),n2.getLatitude());
                
                g.drawLine(coordsn.x, coordsn.y, coordsn2.x, coordsn2.y);
            }
        }
    }
    
    protected static double getNodesDistance(Node n1, Node n2) {
        double distlong = n1.getLongitude() - n2.getLongitude();
        double distlat = n1.getLatitude() - n2.getLatitude();
        return Math.sqrt((distlong*distlong) + (distlat*distlat));
    }
    
    public Point getCoordsToPixel(double longitude, double latitude) {
        return new Point(
            (int) ((longitude - longitudeMin) / ratio + deltaX),
            (int) ((latitude - latitudeMin) / ratio + deltaY)
        );
    }
    
    public Point2D getPixelToPoint (double x, double y) {
        return new Point2D.Double(
            (x - deltaX) * ratio + longitudeMin,
            (y - deltaY) * ratio + latitudeMin
        );
    }
    
    @Override
    public Dimension getPreferredSize() {
        if (preferred != null) {
            return preferred;
        } else {
            return super.getPreferredSize();
        }
    }
    
}
