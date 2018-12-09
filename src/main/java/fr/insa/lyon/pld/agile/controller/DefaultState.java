package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.DeliveryMan;
import fr.insa.lyon.pld.agile.model.Map;
import fr.insa.lyon.pld.agile.view.Window;
import java.awt.geom.Point2D;

/**
 *
 * @author scheah
 */
public abstract class DefaultState implements State{
    @Override
    public void addDelivery(MainController controller, Map map, DeliveryMan deliveryMan, int ind, int duration) {}
    @Override
    public void deleteDelivery(MainController controller, Map map, DeliveryMan deliveryMan, int ind, CommandList cmdList) {}
    @Override
    public void changeDeliveryMan(MainController controller, Map map, DeliveryMan deliveryMan, int ind) {}
    @Override
    public void generateDeliveryMen(MainController controller, Map map, int deliveryMenCount) {}
    @Override
    public void undo(CommandList cmdList) {}
    @Override
    public void redo(CommandList cmdList) {}
    @Override
    public void leftClick(MainController controller, Map map, CommandList listeDeCdes, Window view, Point2D p) {}
    @Override
    public void loadNodesFile(MainController controller, Map map, CommandList cmdList, Window view) throws Exception {}
    @Override
    public void loadDeliveriesFile(MainController controller, Map map, CommandList cmdList, Window view) throws Exception {}
}
