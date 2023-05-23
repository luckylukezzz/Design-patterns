interface Component{
    void render();


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
    
}

class ContextMenu{
    public void duplicate(Component component){
        // this is problematic because if we add another shape we have to do this again.
        if (component instanceof Circle){
            Circle source = (Circle)component;
            Circle target = new Circle();
            target.setRadius(source.getRadius());
        }
    }
}


public class Problem {
    // public static void main(String[] args) throws Exception {
    //     System.out.println("Hello, World!");
    // }
}
