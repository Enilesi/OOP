public class File {
    private String information;
    private String name;
    public File(String information, String name){
        this.information=information;
        this.name=name;
    }
    public String getContent(){
        return this.information;
    }
};

class MainClass{
    public static void main(String[] args){
        File f1=new File("Ana are 10 mere","f1");
        System.out.println(f1.getContent());
    }
}
