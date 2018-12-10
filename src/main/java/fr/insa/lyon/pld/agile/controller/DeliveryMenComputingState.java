package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.view.Window;

/**
 *
 * @author scheah
 */
public class DeliveryMenComputingState extends DefaultState {

    public DeliveryMenComputingState(MainController controller) {
        super(controller);
    }
    
    @Override
    public void enterState(Window window) {
        window.setStatusMessage("Génération des tournées en cours...");
        window.setStatusButton("Interrompre");
        window.setButtonsState(false, false, false, false, false, false);
    }
    @Override
    public void btnStatusClick() {
        generationInterrupt();
    }
    
    @Override
    public void handleExternalEvent(String eventName) {
        switch (eventName) {
            case "shortenDeliveriesFinished":
                generationFinished();
                break;
        }
    }
    
    private void generationInterrupt() {
        System.out.println("Arrêt des calculs...");
        
        controller.getMap().stopShorteningDeliveries();
        controller.setCurrentState(controller.DELIVERY_MEN_GENERATED_STATE);
    }
    private void generationFinished() {
        controller.setCurrentState(controller.DELIVERY_MEN_GENERATED_STATE);
    }
    
}
