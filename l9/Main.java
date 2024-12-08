import java.util.ArrayList;

abstract class Employee {
    private String name;
    private String id;

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name + " " + this.id;
    }

    public abstract double getSalary();

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}

class Permanent extends Employee {
    private double salary;

    public Permanent(String name, String id, double salary) {
        super(name, id);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }
}

class Hourly extends Employee {
    private double hourlyRate;
    private int workedHours;

    public Hourly(String name, String id, double hourlyRate, int workedHours) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.workedHours = workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    @Override
    public double getSalary() {
        return hourlyRate * workedHours;
    }
}

class Company {
    ArrayList<Employee> employees;

    public Company() {
        employees = new ArrayList<>();
    }

    public boolean existsEmployee(Employee employeeToCheck) {
        for (Employee e : employees) {
            if (employeeToCheck.getName().equals(e.getName()) || employeeToCheck.getId().equals(e.getId()))
                return true;
        }
        return false;
    }

    public void addEmployee(Employee employeeToAdd) {
        if (!existsEmployee(employeeToAdd)) {
            employees.add(employeeToAdd);
        }
    }

    public void printEmployees() {
        for (Employee e : employees) {
            System.out.println(e + ", Salary: " + e.getSalary());
        }
    }

    public boolean isInCompany(Strategy strategy) {
        for (Employee e : employees) {
            if (strategy.isCondition(e)) return true;
        }
        return false;
    }
}

interface Strategy {
    boolean isCondition(Employee employee);
}

class NameStrategy implements Strategy {
    private String name;

    public NameStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean isCondition(Employee employee) {
        return employee.getName().equals(name);
    }
}

class SalaryThresholdStrategy implements Strategy {
    @Override
    public boolean isCondition(Employee employee) {
        return employee.getSalary() == 1000;
    }
}

public class Main {
    public static void main(String[] args) {
        Company company = new Company();

        Employee e1 = new Permanent("Alice", "123456", 1500);
        Employee e2 = new Hourly("Bob", "789012", 20, 50);

        company.addEmployee(e1);
        company.addEmployee(e2);

        System.out.println("Printing all employees:");
        company.printEmployees();

        System.out.println("\nChecking if an employee named 'Alice' exists:");
        System.out.println(company.isInCompany(new NameStrategy("Alice")));

        System.out.println("\nChecking if the amount earned by the employee during the current month is 1000:");
        System.out.println(company.isInCompany(new SalaryThresholdStrategy()));
    }
}
/*
 Advantages of Polymorphic calls in this situation:
 - both Hourly and Permanent employes can inherit Employee and get all the methods and atributes from Employee; 
 -using the interface Strategy, the isCondition method can be accesed by both SalaryThresholdStrategy and NameStrategy
 -the design enables easy addition or removal of classes that inherit the Employee class or implement the Strategy interface
 -using polymorphism for this application, we make sure that the code easier to understand and maintain
 */