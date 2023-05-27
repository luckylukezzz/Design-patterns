class Editor{
    private String content;
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void undo() {
        System.out.println("mmm");
        //need to go to back states of content attribute
    }
}



public class Problem {
    // public static void main(String[] args) throws Exception {
    //     var editor = new Editor();
    //     editor.setContent("aaa");
    //     editor.setContent("bbb");
    //     editor.setContent("ccc");
    //     editor.undo();

    // }
}
