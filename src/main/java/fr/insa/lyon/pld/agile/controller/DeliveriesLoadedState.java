package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.Map;

/**
 *
 * @author scheah
 */
public class DeliveriesLoadedState extends MapLoadedState {
    @Override
    public void generateDeliveryMen(MainController controller, Map map, int deliveryMenCount, CommandList cmdList)
    {
        System.err.println("Génération avec " + deliveryMenCount + " livreurs.");
        map.setDeliveryManCount(deliveryMenCount);
        System.err.println("Distribution des livraisons...");
        map.distributeDeliveries();
        System.err.println("Raccourcissement des livraisons...");
        map.shortenDeliveriesInBackground();
        controller.setCurrentState(controller.DELIVERY_MEN_COMPUTING_STATE);
    }
}