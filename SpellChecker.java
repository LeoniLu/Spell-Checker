import java.util.*;
import java.io.*;
import java.nio.file.*;

public class SpellChecker implements SpellCheckerInterface{
    public List<String> dictionary;
    private Path path;
    private String line;
    public HashSet<String> myhash = new HashSet<String>();

    public SpellChecker(String filename){
        try{
            path = Paths.get(filename);
            dictionary = Files.readAllLines(path);
        }catch(Exception e){}
        for(int i = 0; i < dictionary.size(); i++){
            line = dictionary.get(i).toLowerCase();
            dictionary.set(i,line);
            myhash.add(line);
        }
    }
    
    public List<String> getIncorrectWords(String filename){
        List<String> readin = new ArrayList<String>();
        Path filepath;
        List<String> incorrect = new ArrayList<String>();
        String[] splitline;
        
        try{
            filepath = Paths.get(filename);
            readin = Files.readAllLines(filepath);
        }catch(Exception e){}
        for(int i = 0; i < readin.size(); i++){
            splitline = readin.get(i).split(" ");
            for(int j = 0; j < splitline.length; j++){
                String currentword = splitline[j].replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
                if(!myhash.contains(currentword) & !currentword.equals("")){
                    incorrect.add(currentword);
                }
            }
        }
        return incorrect;
    }
    
    public Set<String> getSuggestions(String word){
        HashSet<String> method = new HashSet<String>();
        String newword = null;
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(int i = 0; i < 26; i++){
            for(int j = 0; j <= word.length(); j++){ 
                StringBuffer addw = new StringBuffer(word);
                newword = addw.insert(j,alphabet[i]).toString();
                if(myhash.contains(newword)){
                    method.add(newword);
                }
            }
        }
        for(int m = 0; m < word.length(); m++){
            StringBuffer delw = new StringBuffer(word);
            newword = delw.deleteCharAt(m).toString();
            if(myhash.contains(newword)){
                method.add(newword);
            }
        }
        if(word.length()>1){
            for(int k = 0; k < word.length()-1; k++){
                String swap = String.valueOf(word.charAt(k+1))+String.valueOf(word.charAt(k));
                StringBuffer swapw = new StringBuffer(word);
                newword = swapw.replace(k,k+2,swap).toString();
                if(myhash.contains(newword)){
                    method.add(newword);
                }
                
            }
        }
        return method;
    }
}
