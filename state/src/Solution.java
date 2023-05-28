interface Tool{
    void mouseUp();
    void mouseDown();

}

class SelectionTool implements Tool{
    @Override
    public void mouseDown(){
        System.out.println("selection icon");
    }
    
@Override
    public void mouseUp() {
        System.out.println("Draw rectangle");
        
    }
}
class BrushTool implements Tool{
    @Override
    public void mouseDown(){
        System.out.println("Brush Icon");
    }
    
@Override
    public void mouseUp() {
        System.out.println("Draw line");
        
    }
}
class Canvas{
    private Tool currentTool;

    public void mouseDown(){
        currentTool.mouseDown();
        
    }
    public void mouseUp(){
        currentTool.mouseUp();
        
    }
    public Tool getCurrenttool() {
        return currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }
    
}


public class Solution {
    public static void main(String[] args) throws Exception {
        Canvas canvas1 = new Canvas();
        canvas1.setCurrentTool(new SelectionTool());
        canvas1.mouseDown();
}
}
