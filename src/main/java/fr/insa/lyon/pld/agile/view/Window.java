package fr.insa.lyon.pld.agile.view;

import fr.insa.lyon.pld.agile.XMLParser;
import fr.insa.lyon.pld.agile.controller.MainController;
import fr.insa.lyon.pld.agile.model.*;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author nmesnard, tzhang
 */
public class Window {
    JFrame frame;
    
    JButton btnOpenMap;
    JButton btnOpenLoc;
    
    JSpinner numDeliveries;
    JButton btnGenerate;

    JButton btnListAdd;
    JButton btnListMove;
    JButton btnListRemove;

    Map map = null;
    List<Delivery> deliveries = null;
    
    List<MapView> mapViews = new ArrayList<>();
    
    
    private class ButtonListener implements ActionListener{
        private final MainController controller;
        public ButtonListener(MainController controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controller.openMapFile(frame);
                stateRefresh();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public Window(Map map, MainController controller) {
        this.map = map;
        
        // CREATING COMPONENTS

        // Window
        frame = new JFrame();
        frame.setTitle("PLD Livraison à Domicile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top tool-bar
        JToolBar tlbTop = new JToolBar();
        tlbTop.setFloatable(false);
        // and its buttons
        btnOpenMap = new JButton(new ImageIcon("res/icons/map.png"));
        btnOpenLoc = new JButton(new ImageIcon("res/icons/pin.png"));

        // Centered map
        MapViewGraphical mapViewGraphical = new MapViewGraphical(controller);
        mapViews.add(mapViewGraphical);
        map.addPropertyChangeListener(mapViewGraphical);
        
        // Left panel
        JPanel panTools = new JPanel();

        // > Top settings
        JPanel panDeliveries = new JPanel();
        SpinnerModel model = new SpinnerNumberModel(3, 1, 6, 1);
        numDeliveries = new JSpinner(model);
        ((DefaultEditor) numDeliveries.getEditor()).getTextField().setEditable(false);
        JLabel lblDeliveries = new JLabel("livreurs");
        btnGenerate = new JButton("Générer");

        // > Main lists
        JPanel panLists = new JPanel();
        MapViewTextual mapViewTextual = new MapViewTextual();
        mapViews.add(mapViewTextual);
        map.addPropertyChangeListener(mapViewTextual);
        // > and their buttons
        btnListAdd = new JButton(new ImageIcon("res/icons/add.png"));
        btnListMove = new JButton(new ImageIcon("res/icons/move.png"));
        btnListRemove = new JButton(new ImageIcon("res/icons/delete.png"));


        // CREATING DISPLAY

        EmptyBorder spacer = new EmptyBorder(4, 4, 4, 4);

        // Top tool-bar
        tlbTop.setBorder(spacer);
        tlbTop.add(btnOpenMap);
        tlbTop.add(btnOpenLoc);

        // - Top settings
        panDeliveries.setBorder(spacer);
        panDeliveries.add(numDeliveries);
        panDeliveries.add(lblDeliveries);
        panDeliveries.add(btnGenerate);

        // - Main lists
        panLists.setBorder(spacer);
        panLists.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        panLists.add(mapViewTextual, c);
        c.weighty = 0;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 0;
        panLists.add(btnListAdd, c);
        c.gridx = 1;
        panLists.add(btnListMove, c);
        c.gridx = 2;
        panLists.add(btnListRemove, c);

        // Left panel
        panTools.setBorder(spacer);
        panTools.setLayout(new BorderLayout());
        panTools.add(panDeliveries, BorderLayout.NORTH);
        panTools.add(panLists, BorderLayout.CENTER);

        // Window
        JSplitPane panSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panTools, mapViewGraphical);
        frame.add(tlbTop, BorderLayout.NORTH);
        frame.add(panSplit, BorderLayout.CENTER);
        
        
        // EVENTS HANDLING
        
        // File opening
        btnOpenLoc.addActionListener(new ButtonListener(controller));

        
        // INITIAL STATE
        
        stateRefresh();
        
        
        // READY

        frame.pack();
        frame.setVisible(true);
    }

    protected void stateRefresh()
    {
        Boolean hasMap = (map != null);
        Boolean hasLoc = (deliveries != null);
        
        btnOpenMap.setEnabled(true);
        btnOpenLoc.setEnabled(hasMap);
        
        numDeliveries.setEnabled(true);
        btnGenerate.setEnabled(hasLoc);
        
        btnListAdd.setEnabled(hasLoc);
        btnListMove.setEnabled(hasLoc);
        btnListRemove.setEnabled(hasLoc);
    }
    
}
