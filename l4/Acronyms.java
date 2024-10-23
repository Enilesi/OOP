
// trebuie sa adaugam un contor pt dimensiune sau nu?
// in cazul asta trebuia sa fac contorul static ? daca da, cum il reinitializez?
//sapt urm dam cumv test? In ce va consta acesta? Avem voie sa cerem clarificari?
//ce inseamna ca cuvantul sa nu existe?

public class Acronyms {
    private String [] words;
    private String[] acronym;
    private int insertCount;

    public Acronyms(){
        words=new String[20];
        acronym=new String[20];
        insertCount=0;
    }
    public boolean addWord(String wordToInsert, String acroToInsert){
        if(insertCount>=20) return false;
        
        for (int i=0;i<insertCount;i++){
            if(words[i].equals(wordToInsert))
            {
                acronym[i]=acroToInsert;
                return true;
            }
        }
        words[insertCount] = wordToInsert;
        acronym[insertCount] = acroToInsert;
        insertCount++;
        return true;
    }
    public String getWord(String givenAcro){
        for(int i=0;i<insertCount;i++){
            if(acronym[i].equals(givenAcro)){
                return words[i];
            }
        }
        return "XXXX";
    }

}

class AcronymsClient {
    public static void main(String[] args) {
        Acronyms acronyms = new Acronyms();
        acronyms.addWord("HyperText Markup Language", "HTML");
        acronyms.addWord("Cascading Style Sheets", "CSS");
        acronyms.addWord("JavaScript", "JS");

        System.out.println(acronyms.getWord("HTML")); // Output: HyperText Markup Language
        System.out.println(acronyms.getWord("CSS"));  // Output: Cascading Style Sheets
        System.out.println(acronyms.getWord("JS"));   // Output: JavaScript
        System.out.println(acronyms.getWord("PHP"));  // Output: XXXX
    }
}
