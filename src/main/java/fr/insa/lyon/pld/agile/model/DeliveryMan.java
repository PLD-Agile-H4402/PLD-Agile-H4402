/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.lyon.pld.agile.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author scheah
 */
public class DeliveryMan {
    private int id;
    private Round round;
    private List<Delivery> deliveries;

    public DeliveryMan(int id, Round round) {
        this.id = id;
        this.round = round;
        this.deliveries = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public Round getRound() {
        return round;
    }

    public List<Delivery> getDeliveries() {
        return Collections.unmodifiableList(deliveries);
    }
    
    
}
