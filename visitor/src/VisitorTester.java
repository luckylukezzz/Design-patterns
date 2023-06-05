
interface AbstractVisitor {
    public void doSpecialOp(AbstractElement ae);
    }
    // create a concrete class for the visitor
class Visitor implements AbstractVisitor {
    public void doSpecialOp(AbstractElement ae) {
        System.out.print("Visitor is doing the special operation on ");
        System.out.println(((Element)ae).getData());
    }
}
    
interface AbstractElement {
    public void accept(AbstractVisitor av);
    }
    // create a concrete class for the element
class Element implements AbstractElement {
    private String data;
    public Element(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
    public void accept(AbstractVisitor av) {
        av.doSpecialOp(this);
    }
}
    
class Client {
    private Element e;
    private Visitor v;
    public void action(String data) {
        e = new Element(data);
        v = new Visitor();
        e.accept(v);
    }
}
public class VisitorTester {
    public static void main(String [] args) {
        Client c = new Client();
        c.action("\ndata sent to element by client");
    }
}
    