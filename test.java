import java.util.*;

public class test4 {
    public static void main(String args[]){
         SpellChecker mine = new SpellChecker("words.txt");   
         List<String> mineset = mine.getIncorrectWords("test.txt");
         for(String x:mineset){
             System.out.println(mine.getSuggestions(x));
         }
    }
}
