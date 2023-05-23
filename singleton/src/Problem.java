import java.util.Map;
import java.util.HashMap;

class ConfigManager{
    private Map<String , Object> settings = new HashMap <String, Object>();

    public void set(String key, Object value){
        settings.put(key, value);
    }
    public Object get(String key){
        return settings.get(key);
    }
}


//we not need to make 2 objects of configmanager class only 1 needed (we use singleton for this)
public class Problem {
//     public static void main(String[] args) throws Exception {
//         ConfigManager manager = new ConfigManager();
//         manager.set("name","asd");
//         ConfigManager other = new ConfigManager();
//         System.out.println(other.get("name"));
//    }
}
