package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.*;
import fr.insa.lyon.pld.agile.view.Window;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Stanley
 */
public class MainController implements PropertyChangeListener{
    private Map map;
    private Window view;
    private State currentState;
    private CommandList cmdList;
    
    protected final InitialState INITIAL_STATE = new InitialState(this);
    protected final MapLoadedState MAP_LOADED_STATE = new MapLoadedState(this);
    protected final DeliveriesLoadedState DELIVERIES_LOADED_STATE = new DeliveriesLoadedState(this);
    protected final AddDeliveryState ADD_DELIVERY_STATE = new AddDeliveryState(this);
    protected final DeliveryMenComputingState DELIVERY_MEN_COMPUTING_STATE = new DeliveryMenComputingState(this);
    protected final DeliveryMenGeneratedState DELIVERY_MEN_GENERATED_STATE = new DeliveryMenGeneratedState(this);

    public MainController(Map map) {
        this.map = map;
        this.view = new Window(map, this);
        this.cmdList = new CommandList();
        setCurrentState(INITIAL_STATE);
        map.addPropertyChangeListener(this);
    }

    protected final void setCurrentState(State state) {
        currentState = state;
        state.enterState(view);
        System.out.println(currentState);
    }
    
    public void addDelivery(Node node) {
        currentState.addDelivery(map, node);
    }
    
    public void deleteDelivery(Delivery delivery) {
        currentState.deleteDelivery(map, delivery, cmdList);
    }
    
    public void moveDelivery(Delivery delivery, DeliveryMan oldDeliveryMan, DeliveryMan newDeliveryMan, int oldIndice, int newIndice) {
        currentState.moveDelivery(map, delivery, oldDeliveryMan, newDeliveryMan, oldIndice, newIndice, cmdList);
    }
     
    public void generateDeliveryMen(int deliveryMenCount) {
        currentState.generateDeliveryMen(map, deliveryMenCount, cmdList);
    }
    
    public void stopGeneration() {
        currentState.stopGeneration(map);
    }
    
    public void generationFinished(){
        currentState.generationFinished(map);
    }
            
    public void undo() {
        currentState.undo(cmdList);
    }
    
    public void redo() {
        currentState.redo(cmdList);
    }
    
    public void leftClick(Point2D p) {
        currentState.leftClick(map, cmdList, view, p);
    }
    
    public void rightClick(Point2D p) {
        currentState.rightClick(map, cmdList, view, p);
    }
    
    public void loadMap() throws Exception {
        currentState.loadMap(map, cmdList, view);
    }
    
    public void loadDeliveriesFile() throws Exception {
        currentState.loadDeliveriesFile(map, cmdList, view);
    }
    
    public void selectedNode(Node node) {
        view.selectNode(node);
    }
    
    public void selectedDeliveryMan(int deliveryManIndex) {
        view.selectDeliveryMan(deliveryManIndex);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        switch (propertyName) {
            case "shortenDeliveriesFinished":
                currentState.generationFinished(map);
        }
    }
    
}
