package fr.insa.lyon.pld.agile.model;

import fr.insa.lyon.pld.agile.tsp.KMeansV1;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 *
 * @author scheah
 */
public class Map {
    private final java.util.Map<Long, Node> nodes;
    private Node warehouse;
    private LocalTime startingHour;
    private final List<Delivery> deliveries;
    private final List<DeliveryMan> deliveryMen;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Map() {
        this(null, null);
    }
    
    public Map(Node warehouse, LocalTime startingHour) {
        this.nodes = new HashMap<>();
        this.warehouse = warehouse;
        this.startingHour = startingHour;
        this.deliveries = new ArrayList<>();
        this.deliveryMen = new ArrayList<>();
    }
    
     public void addPropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.addPropertyChangeListener(listener);
     }

     public void removePropertyChangeListener(PropertyChangeListener listener) {
         this.pcs.removePropertyChangeListener(listener);
     }
    
    public Node getNode(long id) {
        return nodes.get(id);
    }

    public java.util.Map<Long, Node> getNodes() {
        return Collections.unmodifiableMap(nodes);
    }

    public Node getWarehouse() {
        return warehouse;
    }

    public LocalTime getStartingHour() {
        return startingHour;
    }

    public List<Delivery> getDeliveries() {
        return Collections.unmodifiableList(deliveries);
    }
    
    public List<DeliveryMan> getDeliveryMen() {
        return Collections.unmodifiableList(deliveryMen);
    }
    
    public boolean addNode(Node node) {
        // putIfAbsent return null if the key was absent
        Node n = nodes.putIfAbsent(node.getId(), node);
        this.pcs.firePropertyChange("nodes", null, nodes);
        return n == null;
    }

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
        this.pcs.firePropertyChange("deliveries", null, deliveries);
    }
    
    public boolean setWarehouse(long id) {
        Node oldWarehouse = warehouse;
        Node newWarehouse = this.nodes.get(id);
        if (newWarehouse != null) {
            this.warehouse = newWarehouse;
            this.pcs.firePropertyChange("warehouse", oldWarehouse, warehouse);
            return true;
        } else {
            return false;
        }
    }
    
    public void setStartingHour(LocalTime startingHour) {
        LocalTime oldStartingHour = startingHour;
        this.startingHour = startingHour;
        this.pcs.firePropertyChange("startingHour", oldStartingHour, startingHour);
    }
    
    public void addDeliveryMan(int number) {
        for (int i = 0; i < number; i++)
            deliveryMen.add(new DeliveryMan(deliveryMen.size()));
        this.pcs.firePropertyChange("deliveryMen", null, deliveryMen);
    }
    
    public void distributeDeliveries() {
        List<Node> nodes = deliveries.stream().map(Delivery::getNode).collect(Collectors.toList());
        List<Integer> clusters = KMeansV1.kMeans(nodes, deliveryMen.size());
        for (int i = 0; i < clusters.size(); i++) {
            assignDelivery(deliveries.get(i), deliveryMen.get(clusters.get(i)));
        }
    }
    
    public void assignDelivery(Delivery delivery, DeliveryMan deliveryMan) {
        delivery.setDeliveryMan(deliveryMan);
        deliveryMan.addDelivery(delivery);
    }
    
    public void clear() {
        nodes.clear();
        Node oldWarehouse = warehouse;
        warehouse = null;
        LocalTime oldStartingHour = startingHour;
        startingHour = null;
        deliveries.clear();
        List<DeliveryMan> oldDeliveryMen = deliveryMen;
        deliveryMen.clear();
        this.pcs.firePropertyChange("nodes", null, nodes);
        this.pcs.firePropertyChange("warehouse", oldWarehouse, warehouse);
        this.pcs.firePropertyChange("startingHour", oldStartingHour, startingHour);
        this.pcs.firePropertyChange("deliveries", null, deliveries);
        this.pcs.firePropertyChange("deliveryMen", null, deliveryMen);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Map {\n");
        builder.append("  nodes: [\n    ");
        StringJoiner joiner = new StringJoiner(",\n    ");
        for (Node node : getNodes().values()) {
            joiner.add(node.toString());
        }
        builder.append(joiner);
        builder.append("\n  ],\n");
        builder.append("  warehouse: ");
        builder.append(warehouse != null ? warehouse.getId() : "null");
        builder.append(",\n");
        builder.append("  startingHour: ");
        builder.append(startingHour);
        builder.append(",\n");
        builder.append("  deliveries: [\n    ");
        joiner = new StringJoiner(",\n    ");
        for (Delivery delivery : getDeliveries()) {
            joiner.add(delivery.toString());
        }
        builder.append(joiner);
        builder.append("\n  ]\n");
        builder.append("}");
        
        return builder.toString();
    }
}
