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
public abstract class DefaultState implements State {
    
    public final MainController controller;
    public DefaultState(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void enterState(Window window) {
        window.setStatusMessage("");
        window.setButtonsState(true, true, true, true, true);
    }
    
    @Override
    public void addDelivery(Map map, Node node) {}
    @Override
    public void validateAddDelivery(Map map, DeliveryMan deliveryMan, int ind, CommandList cmdList) {}
    @Override
    public void cancelAddDelivery() {}
    @Override
    public void deleteDelivery(Map map, Delivery delivery, CommandList cmdList) {}
    @Override
    public void moveDelivery(Map map, Delivery delivery, DeliveryMan oldDeliveryMan, DeliveryMan newDeliveryMan, int oldIndice, int newIndice, CommandList cmdList) {}
    @Override
    public void generateDeliveryMen(Map map, int deliveryMenCount, CommandList cmdList) {}
    @Override
    public void stopGeneration(Map map) {}
    @Override
    public void generationFinished( Map map) {}
    @Override
    public void undo(CommandList cmdList) {}
    @Override
    public void redo(CommandList cmdList) {}
    @Override
    public void leftClick(Map map, CommandList listeDeCdes, Window view, Point2D p) {}
    @Override
    public void rightClick(Map map, CommandList cmdList, Window view, Point2D p) {}
    @Override
    public void loadMap(Map map, CommandList cmdList, Window view) throws Exception {}
    @Override
    public void loadDeliveriesFile(Map map, CommandList cmdList, Window view) throws Exception {}
}
