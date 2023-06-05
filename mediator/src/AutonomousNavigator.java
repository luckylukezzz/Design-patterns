
class Vector { }
class Matrix { }
// this mediator object facilitates communication among all objects
// that require interaction to provide specific IMU services such as
// providing a location value by defining the required APIs
interface IMUMediator {
    public void setGyroscopeStatus(Boolean status);
    public void setAccelerometerStatus(Boolean status);
    public void setMagnetometerStatus(Boolean status);
    public void setPredictorStatus(Boolean status);
    public void setCorrectorStatus(Boolean status);
    public Boolean getGyroscopeStatus();
    public Boolean getAccelerometerStatus();
    public Boolean getMagnetometerStatus();
    public Boolean getPredictorStatus();
    public Boolean getCorrectorStatus();

    public void setGyroscopeValue(Vector v);
    public void setAccelerometerValue(Vector v);
    public void setMagnetometerValue(Vector v);
    public void setPredictorValue(Matrix m);
    public void setCorrectorValue(Vector v);
}

class SensorFusion implements IMUMediator {
    private Gyroscope gyro;
    private Accelerometer accelero;
    private Magnetometer magneto;
    private Predictor predict;
    private Corrector correct;
    private boolean gyroStatus, acceleroStatus, magnetoStatus,
    predictStatus, correctStatus;
    public SensorFusion() {
        predictStatus = true;// why is this required?
    }
    public void setGyroscopeStatus(Boolean status)
    { gyroStatus = status; }
    public void setAccelerometerStatus(Boolean status)
    { acceleroStatus = status; }
    public void setMagnetometerStatus(Boolean status)
    { magnetoStatus = status; }

    public void setPredictorStatus(Boolean status)
    { predictStatus = status; }
    public void setCorrectorStatus(Boolean status)
    { correctStatus = status; }
    public Boolean getGyroscopeStatus() {return gyroStatus; }
    public Boolean getAccelerometerStatus() {return acceleroStatus; }
    public Boolean getMagnetometerStatus() {return magnetoStatus; }
    public Boolean getPredictorStatus() {return predictStatus; }
    public Boolean getCorrectorStatus() {return correctStatus; }
    public void setGyroscopeValue(Vector v) {}
    public void setAccelerometerValue(Vector v) {}
    public void setMagnetometerValue(Vector v) {}
    public void setPredictorValue(Matrix m) {}
    public void setCorrectorValue(Vector v) {}
}

// this interface provide the application-independent API for
// different sensors
interface SensorCommands {
    public void getValue();
}
// specific sensors, implement the application-independent API
// and only communicate with/through mediator object
class Gyroscope implements SensorCommands {
    private IMUMediator imucom;
    public Gyroscope(IMUMediator imucom) { this.imucom = imucom; }
    public void getValue() {
        imucom.setGyroscopeStatus(true);
        System.out.println("Gyroscope output available");
        imucom.setGyroscopeValue(new Vector());
    }
}

class Accelerometer implements SensorCommands {
    private IMUMediator imucom;
    public Accelerometer(IMUMediator imucom) { this.imucom = imucom; }
    public void getValue() {
        imucom.setAccelerometerStatus(true);
        System.out.println("Accelerometer output available");
        imucom.setAccelerometerValue(new Vector());
    }
}
class Magnetometer implements SensorCommands {
    private IMUMediator imucom;
    public Magnetometer(IMUMediator imucom) { this.imucom = imucom; }
    public void getValue() {
        imucom.setMagnetometerStatus(true);
        System.out.println("Magnetometer output available");
        imucom.setMagnetometerValue(new Vector());
    }
}

class Predictor implements SensorCommands {
    private IMUMediator imucom;
    public Predictor(IMUMediator imucom) { this.imucom = imucom; }
    public void getValue() {
        if(imucom.getGyroscopeStatus() && imucom.getCorrectorStatus()) {
        imucom.setPredictorStatus(true);
        System.out.println("Predictor output available");
        imucom.setPredictorValue(new Matrix());
    } else {
        imucom.setPredictorStatus(false);
        System.out.println("Predictor output not available");
    }
    }
}

class Corrector implements SensorCommands {
    private IMUMediator imucom;
    public Corrector(IMUMediator imucom) { this.imucom = imucom; }
    public void getValue() {
        if(imucom.getAccelerometerStatus() && imucom.getMagnetometerStatus() && imucom.getPredictorStatus()) {
            imucom.setCorrectorStatus(true);
            System.out.println("Corrector output available\n");
            imucom.setCorrectorValue(new Vector());
        } else {
            imucom.setCorrectorStatus(false);
            System.out.println("Corrector output not available\n");
        }
    }
}

public class AutonomousNavigator {
    public static void main(String [] args) {
    // create the mediator object
        IMUMediator imucom = new SensorFusion();
        Boolean firstTime = true;
        // create the set of objects that communicate with each other
        Gyroscope gyro = new Gyroscope(imucom);
        Accelerometer accelero = new Accelerometer(imucom);
        Magnetometer magneto = new Magnetometer(imucom);
        Predictor predict = new Predictor(imucom);
        Corrector correct = new Corrector(imucom);

    for(int i=0; i<3; i++) {
    // this is the main IMU sensor fusion activity. However,
    // no communication among modules other than through
    // the mediator
        gyro.getValue(); // v1
        accelero.getValue(); // v2
        magneto.getValue(); // v3
        if(firstTime) {
        firstTime = false;
        correct.getValue(); // m1
        }
        predict.getValue(); // v4: this function requires v1 and m1
        correct.getValue(); // m1: this function requires v2, v3 and v4
    // at this point, a corrected output should be available
    }
    }
}