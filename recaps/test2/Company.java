import java.util.*;

abstract class Employee{
    private String name;
    private int id;
    public Employee(String name, int id){
        this.name=name;
        this.id=id;
    }


    public boolean equals(Object o){
        return o instanceof Employee && ((Employee)o).getName().equals(this.getName())&&((Employee)o).getId()==getId();
    }

    @Override
    public String toString(){
        return "Employee: "+name+" with the id: " +id+" ";
    }

    public String getName(){ return name;}
    public int getId(){return id;}

    public abstract double getSalary();

}

class Permanent extends Employee{
    private double salary;
    public Permanent(String name, int id, double salary){
        super(name,id);
        this.salary=salary;
    }

    @Override
    public String toString(){
        return "Employee: "+getName()+" with the id: " +getId()+" and the salary: "+salary+" ";
    }
    @Override
    public double getSalary(){ return salary;}
}

class HourlyPaidEmployees extends Employee{
    private int hours;
    private double rate;
    public HourlyPaidEmployees(String name, int id, double rate){
        super(name,id);
        this.rate=rate;
    }

    public void addHours(int hours){
        this.hours +=hours;
    }

    @Override
    public double getSalary(){
        return rate*hours;
    }

    @Override
    public String toString(){
        return "Employee: "+getName()+" with the id: " +getId()+" and the salary: "+getSalary()+" ";
    }

}

public class Company {
    ArrayList <Employee> employees;
    public Company(){
        employees=new ArrayList<>();
    }

    public boolean existsEmployee(Employee employeeToCheck){
        for(Employee e:employees){
            if(e.equals(employeeToCheck)) return true;
        }
        return false;
    }

    public void addEmployee(Employee employeeToAdd){
        if(!existsEmployee(employeeToAdd))
        employees.add(employeeToAdd);
    }

    public void printEmployees(){

        for(Employee e:employees){
           System.out.println(e.toString()); 
        }
    }


    public boolean isInCompany(Strategy strategy){
        for(Employee e : employees){
            if(strategy.isCondition(e)) return true;
        }
        return false;
    }


}

interface  Strategy {
    public boolean isCondition(Employee employee);
    
}

class SameName implements Strategy{
    String name;
   public SameName(String name){
    this.name=name;
   }

   @Override
    public boolean isCondition(Employee employee){
        return name.equals(employee.getName());
    }

}

class FixedAmount implements Strategy{
    @Override
    public boolean isCondition(Employee employee){
        if(employee.getSalary()==1000) return true;
        return false;
    }
}

class Main{
    public static void main(String [] args){
        Company comp=new Company();
        Employee e1= new HourlyPaidEmployees("Lucian", 10, 13);
        Employee e2=new Permanent("Andra", 134, 1300);

        ((HourlyPaidEmployees) e1).addHours(35);
       
        comp.addEmployee(e2);
        comp.addEmployee(e1);

        Strategy sameName= new SameName("Lucian");
        System.out.println("Is there an employee named Lucian? "+comp.isInCompany(sameName));

        Strategy fixedAmount=new FixedAmount();
        System.out.println("Is there a salary of 1000?"+comp.isInCompany(fixedAmount));
    }
}

