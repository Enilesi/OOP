public class Text {
    private String[] words;
    private int[] wordCounts;
    private int wordCount;
    static private int value = 0;


    public Text() {
        words = new String[20];
        wordCounts = new int[20];
        wordCount = 0;
    }


    public boolean addWord(String wordToAdd) {
        if (wordCount >= 20) {
            return false;
        }
        
        for (int i = 0; i < wordCount; i++) {
            if (words[i].equals(wordToAdd)) {
                wordCounts[i]++;
                return true;
            }
        }
        
        words[wordCount] = wordToAdd;
        wordCounts[wordCount] = 1;
        wordCount++;
        return true;
    }

    public String printText(){
        String stringToRet="";
        for (int i = 0; i < wordCount; i++) {
            stringToRet+=words[i];
            stringToRet+=":";
            stringToRet+=wordCounts[i];
            if (i < wordCount - 1) {
                stringToRet+=", ";
            } 
        }
        return stringToRet;
    }
    
}

class TextClient {
    public static void main(String[] args) {
        Text text = new Text();
        text.addWord("hello");
        text.addWord("world");
        text.addWord("hello");
        text.addWord("java");
        text.addWord("programming");
        text.addWord("world");

        text.printText();

        text.addWord("test1");
        text.addWord("test2");
        text.addWord("test3");
        text.addWord("test4");
        text.addWord("test5");
        text.addWord("test6");
        text.addWord("test7");
        text.addWord("test8");
        text.addWord("test9");
        text.addWord("test10");
        text.addWord("test11");
        text.addWord("test12");
        text.addWord("test13");
        text.addWord("test14");
        text.addWord("test15");
        System.out.println(text.addWord("test16")); // Output: true
        System.out.println(text.addWord("test17")); // Output: false

    }
}
