import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class Person {
    private int id;
    private String name;
    private int age;
    private String email;

    public Person(int id, String name, int age, String email) {
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void printDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Email: " + email);
    }
}

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

class DataTypeException extends RuntimeException {
    public DataTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class ReadCSV {
    public static void main(String[] args) {
        Set<Person> personSet = new HashSet<>();
        try { 
            List<String> lines = Files.readAllLines(Paths.get("file.csv"));
            for (String line : lines) {
                try {
                    String[] fields = line.split(",");
                    if (fields.length != 4) {
                        throw new InvalidDataFormatException("The line is invalid: " + line + ". It has " + fields.length + " fields instead of 4.");
                    }
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    int age = Integer.parseInt(fields[2]);
                    String email = fields[3];
                    Person person = new Person(id, name, age, email);
                    personSet.add(person);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Data type found with cause: " + e.getMessage());
                } catch (InvalidDataFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        

        for (Person person : personSet) {
            person.printDetails();
        }
    }
}
