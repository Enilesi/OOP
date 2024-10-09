public class Students {
    private String name, id;
    private int year;
    private static int nrOfStudents = 0;
    
    public Students(String n, String i, int y) {
        name = n;
        id = i;
        year = y;
        nrOfStudents++;
    }

    public static int getNrOfStudents() {
        return nrOfStudents;
    }

    public static void main(String[] args) {
        Students student = new Students("Ana", "LM0000", 2);
        System.out.println(Students.getNrOfStudents());
    }
}


class MainClass {
    public static void main(String[] args) {
        Students student = new Students("Ana", "LM0000", 2);
        System.out.println(Students.getNrOfStudents());
    }
}
