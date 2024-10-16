public class Book {
    private int nrOfPages;
    private String author;
    private String title;

    public Book(int nrOfPages) {
        this.nrOfPages = nrOfPages;
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Book) && ((Book) o).title == title && ((Book) o).author == author;
    }

    @Override
    public String toString() {
        return "Book[Title: " + title + ", Author: " + author + ", Pages: " + nrOfPages + "]";
    }
}

class Square {
    private int side;

    public Square() {
        this.side = 15;
    }

    public Square(int side) {
        this.side = side;
    }

    public void print() {
        System.out.println("Square side " + side + " area " + (side * side));
    }
}

class Pyramid {
    private int nrElems;

    public Pyramid(int nrElems) {
        this.nrElems = nrElems;
    }

    @Override
    public String toString() {
        String pirStr= new String();
        for (int i = nrElems; i > 0; i--) {
            for (int j = 0; j < i; j++)
                pirStr=pirStr+i+" ";
            pirStr=pirStr+"\n";
        }
        return pirStr;
    }
}

class Sum {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static int sum(int a, int b, int c) {
        return sum(a, b) + c;
    }

    public static int sum(int a, int b, int c, int d) {
        return sum(a, b, c) + d;
    }
}

class Set {
    private int nrMaxEl;
    private int nrCurrEl;
    private Book[] Els;

    public Set(int nrMaxEl) {
        this.nrMaxEl = nrMaxEl;
        this.Els = new Book[nrMaxEl];
        this.nrCurrEl = 0;
    }

    public boolean existsBook(Book givenBook) {
        for (int i = 0; i < nrCurrEl; i++) {
            if (Els[i].equals(givenBook)) return true;
        }
        return false;
    }

    public boolean add(Book bookToAdd) {
        System.out.println(bookToAdd.toString());
        System.out.println(nrCurrEl);
        System.out.println(existsBook(bookToAdd));
        if (nrCurrEl >= nrMaxEl){ 
            
            return false;
        }
        else if (existsBook(bookToAdd)) {
        
            return false;
        }
        Els[nrCurrEl] = bookToAdd;
        nrCurrEl++;
        return true;
    }

    public Set union(Set otherBookSet) {
        Set newSet = new Set(this.nrMaxEl + otherBookSet.nrMaxEl);
        int maxSize = Math.max(this.nrCurrEl, otherBookSet.nrCurrEl);

        System.out.println(maxSize);
        for (int i = 0; i < this.nrCurrEl; i++) {
            if (this.nrMaxEl >= i) newSet.add(this.Els[i]);
            if (otherBookSet.nrMaxEl >= i) newSet.add(otherBookSet.Els[i]);
        }
        
        return newSet;
    }

    

    public void printBooks() {
        for (int i = 0; i < nrCurrEl; i++) {
            System.out.println(Els[i].toString());
        }
    }
    
}

class MainClass {
    public static void main(String[] args) {
        // Book obj1 = new Book(255);
        // Book obj2 = new Book(255);

        // Square s = new Square();
        // s.print();

        // Pyramid pyramid = new Pyramid(4);
        // System.out.println(pyramid.toString());

        // int a = 3;
        // int b = 5;
        // int c = 7;
        // int d = 2;

        // System.out.println("Sum a,b:" + Sum.sum(a, b));
        // System.out.println("Sum a,b,c:" + Sum.sum(a, b, c));
        // System.out.println("Sum a,b,c,d:" + Sum.sum(a, b, c, d));

        // System.err.println(obj1.equals(obj2));

        Set set1 = new Set(3);
        Set set2 = new Set(3);

        Book book1 = new Book("Aventurile lui Guliver", "Jonathan Swift");
        Book book2 = new Book("Mandrie si prejudecata", "Jane Austin");
        Book book3 = new Book("Poemele luminii", "Lucian Blaga");

        set1.add(book1);
        set1.add(book2);

        set2.add(book2);
        set2.add(book3);

        System.out.println("Books in Set 1:");
        set1.printBooks();

        System.out.println("\nBooks in Set 2:");
        set2.printBooks();

        Set unionSet = set1.union(set2);
        System.out.println("\nBooks in Union Set:");
        unionSet.printBooks();
    }
}
