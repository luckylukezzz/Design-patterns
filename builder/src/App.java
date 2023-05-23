class Car {
    private boolean hasGps;
    private int noSeats;
    private String model;
    private boolean hasTripComputer;
    
    public void setHasGps(boolean hasGps) {
        this.hasGps = hasGps;
    }
    public void setHasTripComputer(int noSeats) {
        this.noSeats = noSeats;
    }
    public void setModel(String model) {
        this.model= model;
    }
    public void setHasTripComputer(boolean hasTripComputer) {
        this.hasTripComputer = hasTripComputer;
    }
    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }
    public void getDetails(){
        System.out.println("GPS: "+hasGps+" seats:"+noSeats+" model: "+model);
    }



}

interface Builder{
    void reset();
    void setnoSeats(int no);
    void setTripComputer();
    void setGps();
    void setModel(String modl);
    Car getProduct();

}

class CarBuilder implements Builder{
    private Car car;

    public CarBuilder(){
        this.reset();
    }
    @Override
    public void reset(){
        this.car = new Car();
    }
    @Override
    public void setnoSeats(int no){
        this.car.setNoSeats(no);
    }
    @Override
    public void setTripComputer(){
        this.car.setHasTripComputer(true);
    }
    @Override
    public void setGps(){
        this.car.setHasGps(true);
    }
    @Override
    public void setModel(String modl){
        this.car.setModel(modl);
    }

    @Override
    public Car getProduct(){
        Car product = this.car;
        this.reset();
        return product;
    }
}

// when you need to build various representations of the product,you can create several different builder classes that implement the same set of building steps(same interface),
// but in a different manner

// If you want extract a series of calls to the builder steps you use to construct a product into a separate class called director.

class Director{

    public void constructRaceCar(Builder builder){
        builder.reset();
        builder.setnoSeats(2);
        builder.setTripComputer();
        builder.setGps();
        builder.setModel("GTR");

    }

    public void constructSUV(Builder builder){
        builder.reset();
        builder.setnoSeats(4);
        builder.setTripComputer();
        builder.setGps();

    }

}

public class App{

    public static void main(String[] args) {
        Director director = new Director();
        CarBuilder builder1 = new CarBuilder();
        director.constructRaceCar(builder1);
        Car car = builder1.getProduct();
        car.getDetails();
        
    }
}        