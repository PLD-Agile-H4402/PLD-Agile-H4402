package fr.insa.lyon.pld.agile.view;

import fr.insa.lyon.pld.agile.model.Delivery;
import fr.insa.lyon.pld.agile.model.Map;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 *
 * @author nmesnard
 */
public interface MapView extends PropertyChangeListener{
    
    public void setMap(Map newMap);
    
    public void setDeliveries(List<Delivery> newDeliveries);
    
}
