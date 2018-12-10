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
public class DeliveryMenGeneratedState extends DeliveriesLoadedState {
    
    public DeliveryMenGeneratedState(MainController controller) {
        super(controller);
    }
    
    @Override
    public void enterState(Window window) {
        window.setStatusMessage("Prêt");
        window.setButtonsState(true, true, true, true, true);
    }
    
    @Override
    public void addDelivery(Map map, Node node) {
        controller.ADD_DELIVERY_STATE.enterAction(map, node);
        controller.setCurrentState(controller.ADD_DELIVERY_STATE);
    }
    
    @Override
    public void deleteDelivery(Map map, Delivery delivery, CommandList cmdList) {
        cmdList.addCommand(new CmdRemoveDelivery(map, delivery));
        controller.setCurrentState(controller.DELIVERY_MEN_GENERATED_STATE);
    }
    
    @Override
    public void moveDelivery(Map map, Delivery delivery, DeliveryMan oldDeliveryMan, DeliveryMan newDeliveryMan, int oldIndice, int newIndice, CommandList cmdList) {
        cmdList.addCommand(new CmdMoveDelivery(map, delivery, oldDeliveryMan, newDeliveryMan, oldIndice, newIndice));
        controller.setCurrentState(controller.DELIVERY_MEN_GENERATED_STATE);
    }
    
    @Override
    public void rightClick(Map map, CommandList cmdList, Window view, Point2D p) {
        Node closest = selectNode(map, cmdList, view, p);
        if(closest != null) {
            int deliveryManIndex = map.getNodeDeliveryManIndex(closest);
            if( deliveryManIndex != -1) {
                Delivery delivery = map.getDeliveries().get(closest.getId());
                deleteDelivery(map, delivery, cmdList);
            }
            //view.showOptionsNode(closest);
        }
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
