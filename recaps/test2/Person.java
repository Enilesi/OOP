
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Person {
    int id;
    String name;
    int age;
    String email;

    public Person(int id, String name, int age, String email){
    
        this.id = id;
        this.name=name;
        this.age=age;
        this.email=email;

    }

    public int hashCode(){
        return Objects.hashCode(id);
    }

    @Override 
    public String toString(){
        return id+" "+name+" "+email+" "+age;
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof Person)&&(((Person)o).getId()==this.getId());
    }

    public int getId(){return id;}
    public String getName(){return name;}
    public int getAge(){return age;}
    public String getEmail(){return email;}
    
}

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String line, int nrOfFields){
        super("The line is invalid:"+ line+". It has "+nrOfFields+" fields instead of 4");
    } 
}

class DataTypeException extends RuntimeException{
    public DataTypeException(Throwable cause){
        super("Invalid Data type found with cause: "+cause.getMessage(),cause);
    }
}

class MainFile{

    public static int stringToInt(String s){
        try{
            return Integer.parseInt(s);
        } catch(NumberFormatException e){
            throw new DataTypeException(e);
        }
    }
    public static void main(String [] args){
    try{
        List <String> lines=Files.readAllLines(Paths.get("file.csv"));
        Set <Person> persons=new HashSet<>();
        for (String line:lines){
            try{
            String[] fields=line.split(",");
            
            if(fields.length!=4) throw new InvalidDataFormatException(line, fields.length);

            int id=stringToInt(fields[0]);
            String name=fields[1];
            int age=stringToInt(fields[2]);
            String email=fields[3];

            Person p=new Person(id, name, age, email);
            persons.add(p);
            }catch(InvalidDataFormatException|DataTypeException e){
                System.out.println(e.getMessage());
            }
        }

        for(Person p:persons){
            System.out.println(p);
        }
    }
    catch(Exception e){
        System.out.println("Error reading the file"+ e.getMessage());
    }
    }

}