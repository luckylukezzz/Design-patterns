import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// The contextual object contains the extrinsic part of the tree
// state. An application can create billions of these since they
// are pretty small: just two integer coordinates and one
// reference field.
class Tree {
    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}

// The flyweight class contains a portion of the state of a
// tree. For instance, you won't find here the tree
// coordinates. But the texture and colors shared between many
// trees are here. Since this data is usually BIG, you'd waste a
// lot of memory by keeping it in each tree object. Instead, we
// can extract texture, color and other repeating data into a
// separate object which lots of individual tree objects can
// reference.
class TreeType {
    private String name;
    private Color color;
    private String otherTreeData;

    public TreeType(String name, Color color, String otherTreeData) {
        this.name = name;
        this.color = color;
        this.otherTreeData = otherTreeData;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 1, y, 3, 5);
        g.setColor(color);
        g.fillOval(x - 5, y - 10, 10, 10);
    }
}


// Flyweight factory decides whether to re-use existing
// flyweight or to create a new object.

class TreeFactory {
    static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, Color color, String otherTreeData) {
        TreeType result = treeTypes.get(name);
        if (result == null) {
            result = new TreeType(name, color, otherTreeData);
            treeTypes.put(name, result);
        }
        return result;
    }
}

// The Tree and the Forest classes are the flyweight's clients.
// You can merge them if you don't plan to develop the Tree
// class any further

class Forest extends JFrame {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color, String otherTreeData) {
        TreeType type = TreeFactory.getTreeType(name, color, otherTreeData);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Tree tree : trees) {
            tree.draw(graphics);
        }
    }
}
public class App {
    static int CANVAS_SIZE = 500;
    static int TREES_TO_DRAW = 1000000;
    static int TREE_TYPES = 2;

    // public static void main(String[] args) {
    //     Forest forest = new Forest();
    //     for (int i = 0; i < Math.floor(TREES_TO_DRAW / TREE_TYPES); i++) {
    //         forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
    //                 "Summer Oak", Color.GREEN, "Oak texture stub");
    //         forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
    //                 "Autumn Oak", Color.ORANGE, "Autumn Oak texture stub");
    //     }
    //     forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
    //     forest.setVisible(true);

    //     System.out.println(TREES_TO_DRAW + " trees drawn");
    //     System.out.println("---------------------");
    //     System.out.println("Memory usage:");
    //     System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
    //     System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
    //     System.out.println("---------------------");
    //     System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
    //             "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");
    // }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}