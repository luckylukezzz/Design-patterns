import java.util.ArrayList;
import java.util.List;

class Editor{
    private String content;
    
    public String getContent() {
        System.out.println(content);
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public EditorState createState(){
        return new EditorState(content);
    }
 
    public void undo(EditorState state) {
        content = state.getState();
    }


}
class EditorState{
    private String state;
    

    public EditorState(String state) {
        this.state = state;
    }


    public String getState() {
        return state;
    }
    

}
class History{
    private List<EditorState> states = new ArrayList<>();
    public void push(EditorState state){
        states.add(state);
    }
    public EditorState pop(){
        EditorState lastItem = states.get(states.size()-1);
        states.remove(lastItem);
        return lastItem;

    }

    
}



public class Solution {
    public static void main(String[] args) throws Exception {
        var editor = new Editor();
        var history = new History();

        editor.setContent("aaa");
        history.push(editor.createState());
        editor.setContent("bbb");
        history.push(editor.createState());
        editor.setContent("ccc");
        editor.undo(history.pop());
        
        editor.getContent();

    }
}
