package fr.insa.lyon.pld.agile.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author scheah
 */
public class Round {
    private final List<DeliveryRoute> itinerary;

    public Round() {
        this.itinerary = new ArrayList<>();
    }

    public List<DeliveryRoute> getItinerary() {
        return Collections.unmodifiableList(itinerary);
    }
    
    void addDeliveryRoute(Node destination, List<Section> sections, boolean delivering) {
	List<Passage> passages = new ArrayList<Passage>();
	
    }
    
    final void createPassage(Section section) {
        double arrivalTime = section.getLength()/1000./15.*60.*60.;
        if (!itinerary.isEmpty()) {
            DeliveryRoute last = itinerary.get(itinerary.size()-1);
            arrivalTime += last.getPassages().get(last.getPassages().size()-1).getArrivalTime();
            
            if(last.isDelivering()) {
        	last.getDestination().getId()
        	arrivalTime +=;
            }
        }
        DeliveryRoute deliveryRoute = new DeliveryRoute(section.getDestination(),true);
        Passage passage = new Passage(section, deliveryDuration);
        deliveryRoute.addPassage(passage);
        itinerary.add(deliveryRoute);
    }
    
    void clear() {
        itinerary.clear();
    }
}
