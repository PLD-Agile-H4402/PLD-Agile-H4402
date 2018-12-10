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
public interface State {
    
    public void enterState(Window window);
    
    public void addDelivery(Map map, Node node);
    
    public void validateAddDelivery(Map map, DeliveryMan deliveryMan, int ind, CommandList cmdList);
    
    public void cancelAddDelivery();
    
    public void deleteDelivery(Map map, Delivery delivery, CommandList cmdList);
    
    public void moveDelivery(Map map, Delivery delivery, DeliveryMan oldDeliveryMan, DeliveryMan newDeliveryMan, int oldIndice, int newIndice, CommandList cmdList);
    
    public void generateDeliveryMen(Map map, int deliveryMenCount, CommandList cmdList);
    
    public void stopGeneration(Map map);
    
    public void generationFinished(Map map);
    
    public void undo(CommandList cmdList);
    
    public void redo(CommandList cmdList);
    
    public void leftClick(Map map, CommandList cmdList, Window view, Point2D p);
    
    public void rightClick(Map map, CommandList cmdList, Window view, Point2D p);
    
    public void loadMap(Map map, CommandList cmdList, Window view) throws Exception;
    
    public void loadDeliveriesFile(Map map, CommandList cmdList, Window view) throws Exception;
    
}
