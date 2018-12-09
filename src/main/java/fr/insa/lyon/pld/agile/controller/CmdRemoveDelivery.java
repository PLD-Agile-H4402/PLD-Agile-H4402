/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.Delivery;
import fr.insa.lyon.pld.agile.model.DeliveryMan;
import fr.insa.lyon.pld.agile.model.Map;

/**
 *
 * @author scheah
 */
public class CmdRemoveDelivery implements Command{
    private Map map;
    private DeliveryMan deliveryMan;
    private int ind;
    private Delivery delivery;
    
    public CmdRemoveDelivery(Map map, DeliveryMan deliveryMan, int ind){
        this.map = map;
        this.deliveryMan = deliveryMan;
        this.ind = ind;
        this.delivery = this.deliveryMan.getDeliveries().get(ind);
    }
    
    @Override
    public void doCmd(){
        deliveryMan.removeDelivery(ind);
        map.removeDelivery(deliveryMan, ind);
    }

    @Override
    public void undoCmd() {
        delivery.setDeliveryMan(deliveryMan);
        map.addDelivery(deliveryMan, delivery, ind);
    }
}
