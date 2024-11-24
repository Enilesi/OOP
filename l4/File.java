import l8.File;
import l8.Folder;

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
        return concating;
    }
}


class FolderClient {
    public static void main(String[] args) {
        Folder folder = new Folder();
        folder.addFile(new File("Hello, ", "file1"));
        folder.addFile(new File("World!", "file2"));
        System.out.println(folder.getContent());
    }
}