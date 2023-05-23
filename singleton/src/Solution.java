import java.util.Map;
import java.util.HashMap;

class ConfigManager{
    private Map<String , Object> settings = new HashMap <String, Object>();
    private static ConfigManager  instance = new ConfigManager();

   
    private ConfigManager() {}
    
    public static ConfigManager getInstance() {
        return instance;
    }
    public void set(String key, Object value){
        settings.put(key, value);
    }
    public Object get(String key){
        return settings.get(key);
    }
}



public class Solution {
    public static void main(String[] args) {
        ConfigManager manager = ConfigManager.getInstance();
        manager.set("name" , "asd"); 
        System.out.println(manager.get("name"));
   }
}
