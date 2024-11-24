
interface Item{
    String getContent();
}

class File implements Item{
    private String information;
    public File(String information){
        this.information=information;
    }
    @Override
    public String getContent(){
        return this.information;
    }
}

class Folder implements Item{
    private Item[] entries;
    private int size;
    public Folder(){
        entries= new Item[10];
        size=0;
    }
    public void addFileORFolder(Item anItem){
        if(size==entries.length){
            Item[] newEntries=new Item[entries.length+10];

            for(int i=0;i<entries.length;i++){
                newEntries[i]=entries[i];
            }
            entries=newEntries;
        }
        entries[size++]=anItem;
    }

    @Override
    public String getContent(){
        String s=new String();
        for(int i=0;i<size;i++){
            if(entries[i]!=null){
                s+=entries[i].getContent();
                s+=" ";
            }
        }
        return s;
    }



}

class Main{
    public static void main(String[] args){
        File file1= new File("Content 1");
        File file2= new File("Content 2");
        File file3= new File("Content 3");
        File file4= new File("Content 4");

        Folder folder1= new Folder();
        folder1.addFileORFolder(file1);
        folder1.addFileORFolder(file2);

        Folder folder2= new Folder();
        folder2.addFileORFolder(file3);

        Folder parentFolder=new Folder();
        parentFolder.addFileORFolder(folder1);
        parentFolder.addFileORFolder(folder2);
    
        System.out.println("Content of the parent folder:"+folder1.getContent());
    }
}