package fr.insa.lyon.pld.agile.model;

/**
 *
 * @author paul
 */
public class UnreachableDeliveryException extends RuntimeException {
    private final Delivery delivery;

    public UnreachableDeliveryException(Delivery delivery) {
        this.delivery = delivery;
    }

    public Delivery getDelivery() {
        return delivery;
    }
}
