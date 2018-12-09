package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.model.Delivery;
import fr.insa.lyon.pld.agile.model.DeliveryMan;
import fr.insa.lyon.pld.agile.model.Map;
import fr.insa.lyon.pld.agile.model.Passage;
import fr.insa.lyon.pld.agile.model.Section;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author scheah
 */
public class CmdChangeDeliveryMan implements Command {
    private DeliveryRoute deliveryRoute;
    private List<Passage> oldPassages;
    private List<Passage> newPassages;
    
    public CmdChangeDeliveryMan(DeliveryRoute deliveryRoute, List<Passage> oldPassages, List<Passage> newPassages){
        this.deliveryRoute = deliveryRoute;
        this.oldPassages = oldPassages;
        this.newPassages = newPassages;
    }
    
    @Override
    public void doCmd(){
        deliveryRoute.setDeliveryRoute(newPassages);
    }

    @Override
    public void undoCmd() {
        deliveryRoute.setDeliveryRoute(oldPassages);
    }
}
