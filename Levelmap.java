import greenfoot.World;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

class Levelmap {
    static Bar bar;
    static Clock clock;
    static Money money;
    static Waitress waitress;

    static List<Table> tables = new ArrayList<>();

    static void loadObjects(String file, World world) {
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

                Node aX = geometry.getNamedItem("x");
                Node aY = geometry.getNamedItem("y");
                Node aWidth = geometry.getNamedItem("width");
                Node aHeight = geometry.getNamedItem("height");

                int width = aWidth == null ? 0 : Integer.valueOf(aWidth.getTextContent());
                int height = aHeight == null ? 0 : Integer.valueOf(aHeight.getTextContent());

                int x = (aX == null ? 0 : Integer.valueOf(aX.getTextContent())) + width / 2;
                int y = (aY == null ? 0 : Integer.valueOf(aY.getTextContent())) + height / 2;

                switch (value.getTextContent()) {
                    case "Bar":
                        bar = new Bar();
                        world.addObject(bar, x, y);

                        break;

                    case "Table":
                        Table table = new Table(world, x, y);
                        tables.add(table);
                        world.addObject(table, x, y);

                        break;

                    case "Waitress":
                        waitress = new Waitress();
                        world.addObject(waitress, x, y);

                        break;

                    case "Clock":
                        clock = new Clock(2);
                        world.addObject(clock, x, y);

                        break;

                    case "Money":
                        money = new Money(width, height);
                        world.addObject(money, x, y);

                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
