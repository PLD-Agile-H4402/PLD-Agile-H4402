package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.*;
import fr.insa.lyon.pld.agile.view.Window;
import java.awt.geom.Point2D;

/**
 *
 * @author Stanley
 */
public class MainController {
    private Map map;
    private Window view;
    private State currentState;
    private CommandList cmdList;
    
    protected final InitialState INITIAL_STATE = new InitialState();
    protected final MapLoadedState MAP_LOADED_STATE = new MapLoadedState();
    protected final DeliveriesLoadedState DELIVERIES_LOADED_STATE = new DeliveriesLoadedState();
    protected final AddDeliveryState1 ADD_DELIVERY_STATE1 = new AddDeliveryState1();

    public MainController(Map map) {
        this.map = map;
        this.view = new Window(map, this);
        this.currentState = INITIAL_STATE;
        this.cmdList = new CommandList();
    }

    protected void setCurrentState(State state) {
        currentState = state;
    }
    
    public void addDelivery(DeliveryMan deliveryMan, int ind, int duration) {
        currentState.addDelivery(this, map, deliveryMan, ind, duration);
    }
    
    public void deleteDelivery(DeliveryMan deliveryMan, int ind) {
        currentState.deleteDelivery(this, map, deliveryMan, ind, cmdList);
    }
    
    public void changeDeliveryMan(DeliveryMan deliveryMan, int ind) {
        currentState.changeDeliveryMan(this, map, deliveryMan, ind);
    }
    
    public void generateDeliveryMen(int deliveryMenCount) {
        currentState.generateDeliveryMen(this, map, deliveryMenCount);
    }
    
    public void undo() {
        currentState.undo(cmdList);
    }
    
    public void redo() {
        currentState.redo(cmdList);
    }
    
    public void leftClick(Point2D p) {
        currentState.leftClick(this, map, cmdList, view, p);
    }
    
    public void loadNodesFile() throws Exception {
        currentState.loadNodesFile(this, map, cmdList, view);
    }
    
    public void loadDeliveriesFile() throws Exception {
        currentState.loadDeliveriesFile(this, map, cmdList, view);
    }
    /*public void loadMapFile() throws IOException, SAXException, ParserConfigurationException {
        File selectedFile = view.askFile("Chargement d'un plan");
        if (selectedFile != null)
        {
            map.clear();
            XMLParser.loadMap(map, selectedFile.toPath());
        }
    }
    
    public void loadDeliveriesFile() throws IOException, SAXException, ParserConfigurationException {
        File selectedFile = view.askFile("Chargement de demandes de livraison");
        if (selectedFile != null)
        {
            map.clearDeliveries();
            map.setDeliveryManCount(0);
            XMLParser.loadDeliveries(map, selectedFile.toPath());
        }
    }
    
    
    public void selectedNode(Node node) {
        view.selectNode(node);
    }
    
    public void selectedDeliveryMan(int deliveryManIndex) {
        view.selectDeliveryMan(deliveryManIndex);
    }
    
    public void generateDeliveryMen(int deliveryMenCount) {
<<<<<<< HEAD
        if (!map.isShorteningDeliveries()) {
            System.err.println("Génération avec " + deliveryMenCount + " livreurs.");
            map.setDeliveryManCount(deliveryMenCount);
            System.err.println("Distribution des livraisons...");
            map.distributeDeliveries();
            System.err.println("Raccourcissement des livraisons...");
            map.shortenDeliveriesInBackground();
        } else {
            System.err.println("Arrêt des calculs...");
            map.stopShorteningDeliveries();
        }
    }
=======
        System.err.println("Génération avec " + deliveryMenCount + " livreurs.");
        map.setDeliveryManCount(deliveryMenCount);
        System.err.println("Distribution des livraisons...");
        map.distributeDeliveries();
        System.err.println("Raccourcissement des livraisons...");
        map.shortenDeliveries();
    }*/
    
>>>>>>> Update Design Pattern Command and State
}
