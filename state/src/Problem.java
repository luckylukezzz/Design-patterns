
enum ToolType {
    SELECTION,
    BRUSH,
    ERASER
}


class Canvas{
    private ToolType currentTool;

    public void mouseDown(){
        if (currentTool == ToolType.SELECTION){
            System.out.println("selection icon");
        }
        else if (currentTool == ToolType.BRUSH){
            System.out.println("Brush Icon");
        }
        else if (currentTool == ToolType.ERASER){
            System.out.println("Eraser Icon");
        }
        
        
    }
    public void mouseUp(){
        if (currentTool == ToolType.SELECTION){
            System.out.println("Draw rectangle");
        }
        else if (currentTool == ToolType.BRUSH){
            System.out.println("Draw line");
        }
        else if (currentTool == ToolType.ERASER){
            System.out.println("Erase something");
        }

    }
    public ToolType getCurrenttool() {
        return currentTool;
    }

    public void setCurrentTool(ToolType currentTool) {
        this.currentTool = currentTool;
    }
    


}



public class Problem {
    public static void main(String[] args) throws Exception {
        Canvas canvas1 = new Canvas();
        canvas1.setCurrentTool(ToolType.SELECTION);
        canvas1.mouseDown();
}
}
