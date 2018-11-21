package fr.insa.lyon.pld.agile;

import fr.insa.lyon.pld.agile.model.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author paul
 */
public class XMLParser {
    static private SAXParserFactory spf = SAXParserFactory.newInstance();
    
    static public Map loadMap(Path path) throws IOException
    {
        return loadMap(Files.newInputStream(path));
    }
    
    static public Map loadMap(InputStream stream) throws IOException {
        Map map = new Map();
        
        try {
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(stream, new MapHandler(map));
            
        } catch (ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
        
        return map;
    }

    private static class MapHandler extends DefaultHandler {
        private Map map;
        
        public MapHandler(Map map) {
            this.map = map;
        }
        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.err.println(uri + " " + localName + " " + qName);
        }
    }
    
    public static void main(String args[]) throws IOException
    {
        Path path = Paths.get("petitPlan.xml");
        XMLParser.loadMap(path);
    }
}
