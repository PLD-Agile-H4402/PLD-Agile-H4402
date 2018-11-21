package fr.insa.lyon.pld.agile.model;

/**
 *
 * @author scheah
 */
public class Delivery {
    private Node node;
    private int duration;
    private DeliveryMan deliveryMan;

    public Delivery(Node node, int duration, DeliveryMan deliveryMan) {
        this.node = node;
        this.duration = duration;
        this.deliveryMan = deliveryMan;
    }

    public Node getNode() {
        return node;
    }

    public int getDuration() {
        return duration;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }
    
    
}
