

abstract class Dialog {
    public abstract Button createButton();
    public void render(){
        Button okButton = createButton();
        okButton.onClick();
        okButton.render();
    }
}
class WindowsDialog extends Dialog {
    @Override
    public Button createButton(){
        System.out.println("win button creaated");
        return new WindowsButton();
    } 
}
class WebDialog extends Dialog {
    @Override
    public Button createButton(){
        System.out.println("html button creaated");
        return new HtmlButton();
    }  
}
interface Button{
    void render();
    void onClick();

}

class WindowsButton implements Button{
    @Override
    public void render() {
        System.out.println("render button in windows style");
    }
    @Override
    public void onClick() {
        System.out.println("native OS click event");
    }
}
class HtmlButton implements Button{
    @Override
    public void render() {
        System.out.println("render button in HTML style");
    }
    @Override
    public void onClick() {
        System.out.println("web browser click event");
    }
}

public class App {
    private static Dialog dialog;
    public static void initialize()throws Exception {
        String config = "web";
        if (config == "windows"){
            dialog = new WindowsDialog();
        }
        else if (config == "web"){
            dialog = new WebDialog();
        }
        else{
            throw new Exception("Invalid configuration");
        }
    }
    public static void main(String[] args) throws Exception {
        initialize();
        dialog.render();
    }
}
