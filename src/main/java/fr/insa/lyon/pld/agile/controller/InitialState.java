package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.xml.XMLParser;
import fr.insa.lyon.pld.agile.model.Map;
import fr.insa.lyon.pld.agile.view.Window;
import java.io.File;

/**
 *
 * @author scheah
 */
public class InitialState extends DefaultState {

    public InitialState(MainController controller) {
        super(controller);
    }
    
    @Override
    public void enterState(Window window) {
        window.setStatusMessage("Prêt");
        window.setButtonsState(true, false, false, false, false);
    }
    
    @Override
    public void loadMap(Map map, CommandList cmdList, Window view) throws Exception {
        File selectedFile = view.promptFile("Chargement d'un plan");
        if (selectedFile == null) return;
        map.clear();
        XMLParser.loadMap(map, selectedFile.toPath());
        controller.setCurrentState(controller.MAP_LOADED_STATE);
        cmdList.reset();
    }
    
}
