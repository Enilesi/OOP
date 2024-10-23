public class File {
    private String information;
    private String name;

    public File(String information, String name) {
        this.information = information;
        this.name = name;
    }

    public String getContent() {
        return this.information;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof File) {
            File other = (File) o;
            return other.information.equals(this.information) && other.name.equals(this.name);
        }
        return false;
    }
}

class Folder {
    private File[] entries = new File[0]; 

    public boolean existsFile(File givenFile) {

        for (File entry:entries) {
            if (entry.equals(givenFile)) return true;
        }
        return false;
    }

    public boolean addFile(File fileToAdd) {
        if (existsFile(fileToAdd)) {
            return false;
        }
        File [] newentry= new File[entries.length+1];
        System.arraycopy(entries, 0, newentry, 0, entries.length);
        newentry[entries.length] = fileToAdd;
        entries=newentry;
        return true;
    }

    public String getContent() {
        String concating="";
        for (File entry:entries) {
            concating+="\n";
            concating += entry.getContent();
        }
        return concating.toString();
    }
}

class MainClass {
    public static void main(String[] args) {
        File f1 = new File("Ana are 10 mere", "f1.txt");
        File f2 = new File("Cana are 10 mere", "f2.txt");
        System.out.println(f1.getContent());

        Folder myFolder = new Folder();
        myFolder.addFile(f1);
        myFolder.addFile(f2);
        System.out.println(myFolder.getContent());
    }
}
