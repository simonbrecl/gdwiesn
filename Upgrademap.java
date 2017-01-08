import greenfoot.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.awt.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

class Upgrademap {
    static BarUpgrade bar;
    static MoneyLeft money;
    static DoneButton doneButton;
    static DecorationsUpgrade decorations;
    static SecurityUpgrade security;
    static BandUpgrade band;
    static KitchenUpgrade kitchen;
    
    static List<Table> tables = new ArrayList<>();

    static void loadObjects(String file, World world) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Upgrademap.class.getResource(file).openStream());

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
                    case "BarUpgrade":
                        bar = new BarUpgrade();
                        world.addObject(bar, x, y);

                        break;

                    case "Table":
                        Table table = new Table(world, x, y, null);
                        tables.add(table);
                        world.addObject(table, x, y);

                        break;

                    

                    case "Money":

                        int amount = ((UpgradeScreen)world).getTentState().getMoney();
                        int newWidth = 160;
                        if(amount >= 100) {
                            newWidth = 180;
                        }
                        else if(amount >= 1000) {
                            newWidth = 200;
                        }
                        money = new MoneyLeft(newWidth, height, amount);
                        world.addObject(money, ((UpgradeScreen)world).getWidth()/2 - newWidth/2, y);

                        break;

                    case "DoneButton":
                        doneButton = new DoneButton(width, height);

                        world.addObject(doneButton, x, y);

                        break;
                        
                    case "KitchenUpgrade":
                        kitchen = new KitchenUpgrade();


                        world.addObject(kitchen, x, y);

                        break;
                        
                    case "SecurityUpgrade":
                        security = new SecurityUpgrade();


                        world.addObject(security, x, y);

                        break;

                    case "DecorationsUpgrade":
                        decorations = new DecorationsUpgrade();

                        world.addObject(decorations, x, y);

                        break;

                    case "BandUpgrade":
                        band = new BandUpgrade();

                        world.addObject(band, x, y);

                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
