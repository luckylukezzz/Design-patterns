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



}

interface Builder{
    void reset();
    void setnoSeats(int no);
    void setTripComputer();
    void setGps();
    Car getProduct();

}

class CarBuilder implements Builder{
    private Car car;

    public void CarBuilder(){
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
    public Car getProduct(){
        Car product = this.car;
        this.reset();
        return product;
    }
}

class Director{

    public void constructRaceCar(Builder builder){
        builder.reset();
        builder.setnoSeats(2);
        builder.setTripComputer();
        builder.setGps();

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
        
    }
}        