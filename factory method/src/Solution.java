interface Component{
    void render();
    Component clone();
    void print();
}

class Circle implements Component{
    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void render() {
        System.out.println("rendering circle");
    }
    @Override
    public Component clone(){
        Circle target = new Circle();
        target.setRadius(this.radius);
        return target;
    }
    @Override
    public void print(){
        System.out.println("circle of radius "+radius);
    }
    
}

class Square implements Component{
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void render() {
        System.out.println("rendering square");
    }
    @Override
    public Component clone(){
        Square target = new Square();
        target.setLength(this.length);
        return target;
    }
    @Override
    public void print(){
        System.out.println("square of length "+length);
    }
    
    
}

class ContextMenu{
    public void duplicate(Component component){
        Component newComponent = component.clone();
        newComponent.print();
    }
}




public class Solution {
    public static void main(String[] args) {
        Circle circle1 = new Circle();
        circle1.setRadius(5);
        Square sq1 = new Square();
        sq1.setLength(6);
        ContextMenu menu1 = new ContextMenu();
        menu1.duplicate(sq1);
        menu1.duplicate(circle1);

    }
}
