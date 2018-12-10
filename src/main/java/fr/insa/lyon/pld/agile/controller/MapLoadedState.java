package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.XMLParser;
import fr.insa.lyon.pld.agile.model.Map;
import fr.insa.lyon.pld.agile.model.Node;
import fr.insa.lyon.pld.agile.view.Window;
import java.awt.geom.Point2D;
import java.io.File;

/**
 *
 * @author scheah
 */
public class MapLoadedState extends DefaultState{
    @Override
    public void leftClick(MainController controller, Map map, CommandList listeDeCdes, Window view, Point2D p) {
        double closestdistance = -1;
        Node closest = null;
        for (Node n : map.getNodes().values()) {
            double distance = Math.pow((p.getX() - n.getLongitude()), 2)
                            + Math.pow((p.getY() - n.getLatitude()), 2);
            if (closestdistance < 0 || distance < closestdistance) {
                closestdistance = distance;
                closest = n;
            }
        }

        if (closestdistance > 15.0) {
            closest = null;
        }
        view.selectNode(closest);
    }
    
    @Override
    public void loadMap(MainController controller, Map map, CommandList cmdList, Window view) throws Exception {
        File selectedFile = view.askFile("Chargement d'un plan");
        if (selectedFile != null)
        {
            map.clear();
            XMLParser.loadMap(map, selectedFile.toPath());
        }
        controller.setCurrentState(controller.MAP_LOADED_STATE);
        cmdList.reset();
    }
    
    @Override
    public void loadDeliveriesFile(MainController controller, Map map, CommandList cmdList, Window view) throws Exception {
        File selectedFile = view.askFile("Chargement de demandes de livraison");
        if (selectedFile != null)
        {
            map.clearDeliveries();
            XMLParser.loadDeliveries(map, selectedFile.toPath());
        }
        controller.setCurrentState(controller.DELIVERIES_LOADED_STATE);
        cmdList.reset();
    }
}
