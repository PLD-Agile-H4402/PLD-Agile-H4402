package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.DeliveryMan;
import fr.insa.lyon.pld.agile.model.Map;
import fr.insa.lyon.pld.agile.view.Window;
import java.awt.geom.Point2D;

/**
 *
 * @author scheah
 */
public interface State {
    
    public void addDelivery(MainController controller, Map map, DeliveryMan deliveryMan, int ind, int duration);
    
    public void deleteDelivery(MainController controller, Map map, DeliveryMan deliveryMan, int ind, CommandList cmdList);
    
    public void changeDeliveryMan(MainController controller, Map map, DeliveryMan deliveryMan, int ind);
    
    public void generateDeliveryMen(MainController controller, Map map, int deliveryMenCount);
    
    public void undo(CommandList cmdList);
    
    public void redo(CommandList cmdList);
    
    public void leftClick(MainController controller, Map map, CommandList listeDeCdes, Window view, Point2D p);
    
    public void loadNodesFile(MainController controller, Map map, CommandList cmdList, Window view) throws Exception;
    
    public void loadDeliveriesFile(MainController controller, Map map, CommandList cmdList, Window view) throws Exception;
}
