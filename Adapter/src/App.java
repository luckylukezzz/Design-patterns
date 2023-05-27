class XMLdata{
    private String xmlData;

    public XMLdata(String xmlData) {
        this.xmlData = xmlData;
    }

    public String getXmlData() {
        return xmlData;
    }
    

}

//clent needs to analyzedata with dataanaltics tool but i takes only json data
//we have xml data
class DataAnalyticsTool{

    private String jsonData;
    public DataAnalyticsTool(){} //needs a default constructor bcz cild class need to super this
    public DataAnalyticsTool(String jsonData) {
        this.jsonData = jsonData;
    }
    public void analyzeData(){
        System.out.println("analysing Data "+jsonData);
    }
    
}
class Adapter extends DataAnalyticsTool{
    private XMLdata xmldata;
    public Adapter(XMLdata xmldata) {
        this.xmldata = xmldata;
    }
    @Override //overriding dataanallyticstool class method to use xml data 
    public void analyzeData(){
        System.out.println("converting xml data  "+xmldata.getXmlData()+" to json");
        System.out.println("Analysing data...");
    }
}

class Client{
    public void processData(DataAnalyticsTool tool){
        tool.analyzeData();
    }
}





public class App {
    public static void main(String[] args) throws Exception {
        XMLdata xmlData = new XMLdata("'this is xml format'");
        //DataAnalyticsTool tool = new DataAnalyticsTool();
        //we make a tool of dataanalyticstool class with the inherited adapter class
        DataAnalyticsTool tool = new Adapter(xmlData);
        Client client1 = new Client();
        client1.processData(tool);
    }
}
