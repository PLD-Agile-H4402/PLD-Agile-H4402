package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.Delivery;
import fr.insa.lyon.pld.agile.model.DeliveryMan;
import fr.insa.lyon.pld.agile.model.Map;

/**
 *
 * @author scheah
 */
public class CmdAddDelivery implements Command{
    private Map map;
    private Delivery delivery;
    private DeliveryMan deliveryMan;
    private int ind;
    
    public CmdAddDelivery(Map map, Delivery delivery, DeliveryMan deliveryMan, int ind){
        this.map = map;
        this.delivery = delivery;
        this.deliveryMan = deliveryMan;
        this.ind = ind;
    }
    
    @Override
    public void doCmd(){
        delivery.setDeliveryMan(deliveryMan);
        map.addDelivery(deliveryMan, delivery, ind);
    }

    @Override
    public void undoCmd() {
        map.removeDelivery(deliveryMan, ind);
    }
}
