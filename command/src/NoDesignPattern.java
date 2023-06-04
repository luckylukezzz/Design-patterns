class Remote {
    private boolean buttonOn;
    private LightBulb lightb;
    private Television tv;

    public Remote() {
        buttonOn = false;
        lightb = null;
        tv = null;
    }

    public void setOn(Device device) {
        buttonOn = true;
        if(device instanceof LightBulb) {
            lightb = (LightBulb) device;
            lightb.on();
        }
        else if(device instanceof Television) {
            tv = (Television) device;
            int channel = tv.getStoredPreferences("channel");
            int volume = tv.getStoredPreferences("volume");
            tv.on();
            tv.setChannel(channel);
            tv.setVolume(volume);
        }
    }
   
    public void setOff(Device device) {
        buttonOn = false;
        if(device instanceof LightBulb) {
            lightb = (LightBulb) device;
            lightb.off();
        }
        else if(device instanceof Television) {
            tv = (Television) device;
            tv.off();
        }
    }
}
    
interface Device {
    public void on();
    public void off();
}
    
class LightBulb implements Device {
    public void on() { System.out.println("Light is on"); }
    public void off() { System.out.println("Light is off"); }
}
class Television implements Device {
    public void on() { System.out.println("Television is on"); }
    public void off() { System.out.println("Television is off"); }
    public int getStoredPreferences(String param) {
    System.out.println("retrieving parameters for "+param);
    return 1;
    }
    public void setChannel(int channel) { }
    public void setVolume(int volume) { }   
}
    
public class NoDesignPattern {
    public static void main (String [] args) {
    LightBulb bulb1 = new LightBulb(); // we create a light bulb
    Television tv1 = new Television(); // we create a television
    Remote remote = new Remote(); // we create a "common" remote
    // the specific command of the remote is passed a particular
    // device, and the remote should "statically" know what to do
    // with the particular device for the given command.
    // This leads to "tight coupling".
    remote.setOn(bulb1);
    remote.setOn(tv1);
    remote.setOff(tv1);
    remote.setOff(bulb1);
}
}