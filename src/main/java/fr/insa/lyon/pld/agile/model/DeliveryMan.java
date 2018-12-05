package fr.insa.lyon.pld.agile.model;

import fr.insa.lyon.pld.agile.tsp.Dijkstra;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author scheah
 */
public class DeliveryMan {
    private final int id;
    private final Round round;
    private final List<Delivery> deliveries;

    public DeliveryMan(int id) {
        this.id = id;
        this.round = new Round();
        this.deliveries = new ArrayList<>();
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
    
    void addDelivery(Delivery delivery, Map map) {
        if (deliveries.contains(delivery))
            throw new RuntimeException("DeliveryMan already deliver there"); //TODO : Better error handling
        
        Node origin = null;
        if (deliveries.isEmpty())
            origin = map.getWarehouse();
        else
            origin = deliveries.get(deliveries.size()-1).getNode();
        
        List<Section> sections = Dijkstra.getPath(map.getNodes(), origin, delivery.getNode());
        round.addDeliveryRoute(delivery.getNode(), sections, true);
        deliveries.add(delivery);
    }
    
    void addNode(Node node, Map map) { // TODO : refactorer
        Node origin = null;
        if (deliveries.isEmpty())
            origin = map.getWarehouse();
        else
            origin = deliveries.get(deliveries.size()-1).getNode();
        
        List<Section> sections = Dijkstra.getPath(map.getNodes(), origin, node);
        round.addDeliveryRoute(node, sections, false);
    }
    
    void clear() {
        round.clear();
        deliveries.clear();
    }
}
