package fr.insa.lyon.pld.agile.controller;

import fr.insa.lyon.pld.agile.XMLParser;
import fr.insa.lyon.pld.agile.model.Map;
import fr.insa.lyon.pld.agile.view.Window;
import java.io.File;

/**
 *
 * @author scheah
 */
public class InitialState extends DefaultState{
    @Override
    public void loadNodesFile(MainController controller, Map map, CommandList cmdList, Window view) throws Exception {
        File selectedFile = view.askFile("Chargement d'un plan");
        if (selectedFile != null)
        {
            map.clear();
            XMLParser.loadNodes(map, selectedFile.toPath());
        }
        controller.setCurrentState(controller.mapLoadedState);
        cmdList.reset();
    }
}
