import greenfoot.World;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

public class Levelmap {
    public static void loadObjects(String file, World world) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

            NodeList cells = doc.getElementsByTagName("mxCell");

            for (int i = 0; i < cells.getLength(); i++) {
                Node cell = cells.item(i);

                NamedNodeMap attributes = cell.getAttributes();

                Node vertex = attributes.getNamedItem("vertex");

                if (vertex == null || !vertex.getTextContent().equals("1")) {
                    continue;
                }

                Node value = attributes.getNamedItem("value");

                if (value == null || value.getTextContent().equals("")) {
                    continue;
                }

                NamedNodeMap geometry = cell.getFirstChild().getAttributes();

                int x = Integer.valueOf(geometry.getNamedItem("x").getTextContent()) + Integer.valueOf(geometry.getNamedItem("width").getTextContent()) / 2;
                int y = Integer.valueOf(geometry.getNamedItem("y").getTextContent()) + Integer.valueOf(geometry.getNamedItem("height").getTextContent()) / 2;

                switch (value.getTextContent()) {
                    case "Bar":
                        world.addObject(new Bar(), x, y);

                        break;

                    case "Table":
                        world.addObject(new Table(world, x, y), x, y);

                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
