import greenfoot.World;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

class Levelmap {
    private World world;

    private Bar bar;
    private Clock clock;
    private Money money;
    private Waitress waitress;
    private Goal goal;

    private List<Table> tables = new ArrayList<>();

    public Levelmap(String file, World world) {
        this.world = world;

        loadObjects(file);
    }

    private void loadObjects(String file) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Levelmap.class.getResource(file).openStream());

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
                        Table table = new Table(world, x, y, this);
                        tables.add(table);
                        world.addObject(table, x, y);

                        break;

                    case "Waitress":
                        waitress = new Waitress(world);
                        world.addObject(waitress, x, y);

                        break;

                    case "Clock":
                        clock = new Clock(5);
                        world.addObject(clock, x, y);

                        break;

                    case "Money":
                        money = new Money(width, height);
                        world.addObject(money, x, y);

                        break;

                    case "Goal":
                        goal = new Goal(width, height);
                        goal.setGoal(400);

                        world.addObject(goal, x, y);

                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public World getWorld() {
        return world;
    }

    public Bar getBar() {
        return bar;
    }

    public Clock getClock() {
        return clock;
    }

    public Money getMoney() {
        return money;
    }

    public Waitress getWaitress() {
        return waitress;
    }

    public Goal getGoal() {
        return goal;
    }

    public List<Table> getTables() {
        return tables;
    }
}
