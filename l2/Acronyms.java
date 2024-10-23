public class Acronyms {
    private String[] words;
    private String[] acronyms;
    private int wordCount;

    public Acronyms() {
        words = new String[20];
        acronyms = new String[20];
        wordCount = 0;
    }

    public boolean addWord(String word, String acronym) {
        if (wordCount >= 20) {
            return false;
        }

        for (int i = 0; i < wordCount; i++) {
            if (words[i].equals(word)) {
                acronyms[i] = acronym;
                return true;
            }
        }

        words[wordCount] = word;
        acronyms[wordCount] = acronym;
        wordCount++;
        return true;
    }

    public String getWordFromAcronym(String givenAcronym) {
        for (int i = 0; i < wordCount; i++) {
            if (acronyms[i].equals(givenAcronym)) {
                return words[i];
            }
        }
        return "XXXX";
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            result.append(words[i]).append(" (").append(acronyms[i]).append(")");
            if (i < wordCount - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}

class AcronymsClient {
    public static void main(String[] args) {
        Acronyms acronyms = new Acronyms();
        
        acronyms.addWord("National Aeronautics and Space Administration", "NASA");
        acronyms.addWord("International Business Machines", "IBM");
        acronyms.addWord("Light Amplification by Stimulated Emission of Radiation", "LASER");

        acronyms.addWord("Light Amplification by Stimulated Emission of Radiation", "Laser");

        System.out.println("Acronyms List: " + acronyms);

        System.out.println("Word for NASA: " + acronyms.getWordFromAcronym("NASA"));
        System.out.println("Word for IBM: " + acronyms.getWordFromAcronym("IBM"));
        System.out.println("Word for RADAR (nonexistent): " + acronyms.getWordFromAcronym("RADAR"));
    }
}
