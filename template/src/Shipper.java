class ShippingContainer { // this is a helper class for the example
    private String id;
    private double weight;
    private String destport;
    private int deadline;
    public ShippingContainer(String id, double weight, String destport,
    int deadline) {
    this.id = id; this.weight = weight; this.destport = destport;
    this.deadline = deadline;
    }
    public String getId() { return id; }
    public double getWeight() { return weight; }
    public String getDestport() { return destport; }
    public int getDeadline() { return deadline; }
    public void show() {
    System.out.println("["+id+"] "+weight+" KG TO:"+destport+
    " IN "+deadline+ " DAYS");
    }
    }

    // this is the template class
abstract class Sorter {
    private Object [] buf;
    private int size;
    public Sorter(int size, Object [] buf) {
        this.size = size;
        this.buf = buf;
    }
    public final void sort() {
        for(int i=1; i<size; i++) {
            for(int j=0; j<(size-i); j++)
                if(compare(buf[j],buf[j+1]) > 0)
                    swap(j,j+1);
        }
    }

    public void swap(int a, int b) {
        Object obj = buf[a];
        buf[a] = buf[b];
        buf[b] = obj;
    }
    public abstract int compare(Object obja, Object objb);
}

    // this is a concrete class derived from the template class
class DeadlineSorter extends Sorter {
    public DeadlineSorter(int size, Object [] buf) {
        super(size,buf);
    }
    public int compare(Object obja, Object objb) {
        ShippingContainer sca, scb;
        sca = (ShippingContainer) obja;
        scb = (ShippingContainer) objb;
        if(sca.getDeadline() > scb.getDeadline())
            return 1;
        else if(sca.getDeadline() < scb.getDeadline())
             return -1;
        else
            return 0;
    }
}
  
    // this is a concrete class derived from the template class
class WeightSorter extends Sorter {
    public WeightSorter(int size, Object [] buf) {
        super(size,buf);
    }
    public int compare(Object obja, Object objb) {
        ShippingContainer sca, scb;
        sca = (ShippingContainer) obja;
        scb = (ShippingContainer) objb;
        if(sca.getWeight() > scb.getWeight())
            return 1;
        else if(sca.getWeight() < scb.getWeight())
            return -1;
        else
            return 0;
    }
}

public class Shipper {
    public static void main(String [] args) {
        ShippingContainer [] manifest = new ShippingContainer[6];
        manifest[0] = new ShippingContainer("APM-01",12500,"P2Singapore",20);manifest[1] = new ShippingContainer("MSC-01",17500,"P3Colombo",25);
        manifest[2] = new ShippingContainer("CMA-01",15250,"P4Dubai",30);
        manifest[3] = new ShippingContainer("PIL-01",11500,"P2Singapore",15);manifest[4] = new ShippingContainer("MSC-02",10750,"P3Colombo",20);
        manifest[5] = new ShippingContainer("CMA-02",16750,"P4Dubai",25);
        for(int i=0; i<6; i++)
            manifest[i].show();
        System.out.println("---");
        DeadlineSorter ds = new DeadlineSorter(6,manifest);
        ds.sort();
        for(int i=0; i<6; i++)
            manifest[i].show(); 
        System.out.println("---");
        WeightSorter ws = new WeightSorter(6,manifest);
        ws.sort();
        for(int i=0; i<6; i++)
            manifest[i].show();
    }
}