package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.XMLParser;
import fr.insa.lyon.pld.agile.model.DeliveryMan;
import fr.insa.lyon.pld.agile.model.Map;
import fr.insa.lyon.pld.agile.model.Node;
import fr.insa.lyon.pld.agile.view.Window;
import java.awt.geom.Point2D;
import java.io.File;

/**
 *
 * @author scheah
 */
public class DeliveriesLoadedState extends DefaultState{
    @Override
    public void addDelivery(MainController controller, Map map, DeliveryMan deliveryMan, int ind, int duration) {
        controller.addDeliveryState1.enterAction(map, deliveryMan, ind, duration);
        controller.setCurrentState(controller.addDeliveryState1);
    }
    @Override
    public void deleteDelivery(MainController controller, Map map, DeliveryMan deliveryMan, int ind, CommandList cmdList) {
        cmdList.addCommand(new CmdRemoveDelivery(map, deliveryMan, ind));
        controller.setCurrentState(controller.deliveriesLoadedState);
    }
    
    @Override
    public void changeDeliveryMan(MainController controller, Map map, DeliveryMan deliveryMan, int ind) {
        controller.changeDeliveryManState1.enterAction(map, deliveryMan, ind);
        controller.setCurrentState(controller.changeDeliveryManState1);
    }
    
    @Override
    public void generateDeliveryMen(MainController controller, Map map, int deliveryMenCount)
    {
        if (!map.isShorteningDeliveries()) {
            System.err.println("Génération avec " + deliveryMenCount + " livreurs.");
            map.setDeliveryManCount(deliveryMenCount);
            System.err.println("Distribution des livraisons...");
            map.distributeDeliveries();
            System.err.println("Raccourcissement des livraisons...");
            map.shortenDeliveriesInBackground();
        } else {
            System.err.println("Arrêt des calculs...");
            map.stopShorteningDeliveries();
        }
    }
    
    @Override
    public void rightClick(MainController controller, Map map, CommandList cmdList, Window view, Point2D p) {
        double closestdistance = -1;
        Node closest = null;
        for (Node n : map.getNodes().values()) {
            int deliveryman = map.getNodeDeliveryManIndex(n);
            if( deliveryman ==-1)
            {
                double distance = Math.pow((p.getX() - n.getLongitude()), 2)
                                + Math.pow((p.getY() - n.getLatitude()), 2);
                if (closestdistance < 0 || distance < closestdistance) {
                    closestdistance = distance;
                    closest = n;
                }
            }
        }

        if (closestdistance > 15.0) {
            closest = null;
        }
        if (closest != null)
        {
            view.selectNode(closest);
            view.showOptionsNode(closest);
        }   
    }
    
    @Override
    public void leftClick(MainController controller, Map map, CommandList cmdList, Window view, Point2D p) {
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
    public void loadNodesFile(MainController controller, Map map, CommandList cmdList, Window view) throws Exception {
        File selectedFile = view.askFile("Chargement d'un plan");
        if (selectedFile != null)
        {
            map.clear();
            XMLParser.loadNodes(map, selectedFile.toPath());
        }
        controller.setCurrentState(controller.mapLoadedState);
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
        controller.setCurrentState(controller.deliveriesLoadedState);
        cmdList.reset();
    }
    
    @Override
    public void undo(CommandList cmdList) {
        cmdList.undo();
    }
    @Override
    public void redo(CommandList cmdList) {
        cmdList.redo();
    }
}
