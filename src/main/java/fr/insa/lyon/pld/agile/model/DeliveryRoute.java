package fr.insa.lyon.pld.agile.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeliveryRoute {
    private Node destination;
    private List<Passage> passages;
    private boolean delivering;
    
    public DeliveryRoute(Node dest, boolean delivering) {
	this.destination = dest;
	passages = new ArrayList<Passage>();
	this.delivering = delivering;
    }
    
    public Node getDestination() {
	return destination;
    }
    
    public List<Passage> getPassages() {
        return Collections.unmodifiableList(passages);
    }
    
    public boolean isDelivering() {
        return delivering;
    }
    
    public void setDelivering(boolean delivering) {
	this.delivering = delivering;
    }
    
    public void addPassage(Passage passage) {
	passages.add(passage);
    }
}
