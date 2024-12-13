import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Person {
        private int id;
        private String name;
        private int age;
        private String email;

    public Person(int id, String name, int age, String email)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o){
        return (o instanceof Person)&&((Person)o).getId()==this.getId();

    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    public void printDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Email: " + email);
    }

    
}


class InvalidDataFormatException extends Exception{
    public InvalidDataFormatException(String line, int nrOfFields) {
        super("The line is invalid:"+line+". It has "+nrOfFields+" fields instead of 4");
    }
}

class DataTypeException extends RuntimeException{
    public DataTypeException( Throwable cause){
        super("Invalid Data type found with cause: "+cause.getMessage(),cause);
    }
}


class Main {
     public static int convertStringToInteger(String string){
         try{ 
            return Integer.parseInt(string);
         } 
         catch(NumberFormatException e){
            throw new DataTypeException(e);
         }
     }

    public static void main(String[] args) {
        Set <Person>persons=new HashSet<>();
        try{
        List<String> lines = Files.readAllLines(Paths.get("file.csv"));
        for (String line : lines) {
            try {
                String[] fields = line.split(",");
                if (fields.length != 4) {
                    throw new InvalidDataFormatException(line,fields.length);
                }
                int id = convertStringToInteger(fields[0]);
                String name = fields[1];
                int age = convertStringToInteger(fields[2]);
                String email = fields[3];
                Person person = new Person(id, name, age, email);
                persons.add(person);
            }
             catch (DataTypeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDataFormatException e) {
                System.out.println(e.getMessage());
            } 
        }
    } catch (Exception e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
       
    for (Person person : persons) {
        person.printDetails();
    }
}
}






