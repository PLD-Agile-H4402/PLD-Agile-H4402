package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.Delivery;
import fr.insa.lyon.pld.agile.model.DeliveryMan;
import fr.insa.lyon.pld.agile.model.Map;
import fr.insa.lyon.pld.agile.model.Node;
import fr.insa.lyon.pld.agile.view.Window;
import java.awt.geom.Point2D;

/**
 *
 * @author scheah
 */
public class AddDeliveryState1 extends DefaultState {
    private DeliveryMan deliveryMan;
    private int ind;
    private int duration; 
    
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
        Delivery newDelivery = new Delivery(closest, duration);
        cmdList.addCommand(new CmdAddDelivery(map, newDelivery, deliveryMan, ind));
        controller.setCurrentState(controller.deliveriesLoadedState);
    }
    
    protected void enterAction(Map map, DeliveryMan deliveryMan, int ind, int duration) {
        this.deliveryMan = deliveryMan;
        this.ind = ind;
        this.duration = duration;
    }
}
