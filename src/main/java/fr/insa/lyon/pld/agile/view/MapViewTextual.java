package fr.insa.lyon.pld.agile.view;

import fr.insa.lyon.pld.agile.model.*;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import java.util.List;

/**
 *
 * @author nmesnard
 */
public class MapViewTextual extends JTabbedPane implements MapView
{
    Map map;
    List<Delivery> deliveries;
    
    
    
    public MapViewTextual()
    {
        // this.addTab();
        
        for (int count=0; count<3; count++) {
            String livreurName = "Livreur " + (count+1);
            JPanel panLivreur = makeListPanel(livreurName);
            this.addTab(livreurName, panLivreur); //, null, panLivreur, livreurName);
        }
    }
    
    protected static JPanel makeListPanel(String text) {
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(1, 1));
        
        String list[] = {"Monday", "Tuesday", "Wednesday",
                         "Thursday", "Friday", "Saturday", "Sunday"};
        pan.add(new JList<>(list));
        return pan;
    }
    
    @Override
    public void setMap(Map newMap)
    {
        
    }
    
    @Override
    public void setDeliveries(List<Delivery> newDeliveries)
    {
        
    }
    
}
