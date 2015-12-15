import java.util.Set;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileReader;
public class PerfectWordCalculator{

     public static void main(String []args){
        PerfectWordCalculator instance = new PerfectWordCalculator();
        Set<String> dictionary = instance.readDictionary("enable1.txt");
        System.out.println("Read dictionary with size: "+dictionary.size());
        /*System.out.println("Test char score: "+instance.letterScore('a')+","+instance.letterScore('j'));
        System.out.println("Test char score: "+instance.letterScore('A')+","+instance.letterScore('J'));
        System.out.println("Test word score: "+instance.wordScore("attitude"));*/
        System.out.println("Perfect word list begins now:");
        
        instance.printPerfectWords(dictionary, 1, 100);
     }
     
     public void printPerfectWords(Set<String> dictionary, int begin, int end) {
         int count = 0;
         for (String word: dictionary) {
            if (wordScore(word) == 100) {
                if (count >=begin && count <= end) {
                    System.out.println(word+": "+explain(word));
                }
                ++count;
            }
            if (count == end)
                break;
         }
         System.out.println("Total number of perfect words checked: "+count);
     }
     
     public String explain(String word) {
         StringBuilder builder = new StringBuilder();
         for (int i = 0; i < word.length(); ++i) {
             builder.append(letterScore(word.charAt(i)));
             if (i < word.length() -1) {
                 builder.append("+");
             }
         }
         return builder.toString();
     }
     
     public int wordScore(String word) {
        int ans = 0;
        for (int i = 0; i < word.length(); ++ i) {
            ans += letterScore(word.charAt(i));
        }
        return ans;
     }
     
     public int letterScore(char letter) {
         return (int)(Character.toLowerCase(letter)) - 96;
     }
     
     private Set<String> readDictionary(String filename) {
         Set<String> dictionary = new TreeSet<String>();
         try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
             String line;
             while ((line = br.readLine()) != null) {
                  dictionary.add(line);
             }
         } catch (Exception e)
            {
                System.err.println(e.getMessage()); // handle exception
            }
         return dictionary;
     }
     
}
