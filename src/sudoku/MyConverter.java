package sudoku;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Μετατρέπει αριθμούς σε συμβολοσειρές καθώς και το αντίθετο έτσι ώστε να μπορούμε να έχουμε το WordDoku
 * ο μετατροπέας δέχεται αριθμούς απο το 0 εώς το 9
 * @author Manos Kirtas
 * @version 1.0.0
 */
public class MyConverter {
        private HashMap<String,Integer> charToInt;
        private HashMap<Integer,String> intToChar;
        private static MyConverter instance = null;
        private MyConverter(){
                intToChar = new HashMap<>();
                intToChar.put(0,""); intToChar.put(1,"A"); intToChar.put(2,"B");intToChar.put(3,"C");
                intToChar.put(4,"D"); intToChar.put(5,"E");intToChar.put(6,"F"); intToChar.put(7,"G");
                intToChar.put(8,"H"); intToChar.put(9,"I");

                charToInt = new HashMap<>();
                charToInt.put("",0); charToInt.put("A",1); charToInt.put("B",2);charToInt.put("C",3);
                charToInt.put("D",4);charToInt.put("E",5);charToInt.put("F",6); charToInt.put("G",7);
                charToInt.put("H",8); charToInt.put("I",9); 
        }

    /**
     * Δημιουργεί ενα αντικείμενο της κλάσης ή επιστρέφει το ήδη υπάρχον
     * @return Αντικείμενο της συγκεκριμένης κλάσης
     */
    public static synchronized MyConverter getInstance() {
            if (instance == null) {
                instance = new MyConverter();
            }
            return instance;
    }

    /**
     * Μετατρεπει εναν ακέραιο σε {@link String} αναλογά με της συμβάσεις που εχουν γίνει στο WordDoku
     * @param a Tον αριθμό που είναι για μετατροπή
     * @return Το αντίστοιχο γράμμα του αριθμού επιστρέφει {@code null} αν ο αρίθμος δεν περίεχεται
     */
    public String intToChar(int a){
            if(intToChar.containsKey(a)){
                return intToChar.get(a);
            }
            return null;
        }

    /**
     * Μετατρέπει έναν ακέραιο σε {@link String} ανάλογά με της συμβάσεις που έχουν γίνει στο WordDoku
     * @param a Τον {@link String} που είναι για μετατροπή
     * @return Τον αντίστοιχο ακέραιο της συμβολοσειράς επιστρέφει -1 αν ο η συμβολοσείρα δεν περίεχεται
     */ 
    public int charToInt(String a){
            if(a == null){
                return -1;
            }
            if(charToInt.containsKey(a.toUpperCase())){
                return charToInt.get(a.toUpperCase());
            }
            return -1;
        }

    /**
     * Μετατρέπει ένα σύνολο από ακεραίους αριθμούς σε ενα σύνολο απο συμβολοσειρές ανάλογά με της συμβάσεις που έχουν γίνει στο WordDoku
     * @param help Το σύνολο ακέραιών που θέλουμε να μερέψουμε
     * @return Το νεο σύνολό με τις συμβολοσειράς επιστρέφει {@code null} αν ένας απο τους αρίθμος δεν περίεχεται
     */
    public HashSet<String> intToCharSet(HashSet<Integer> help){
            HashSet<String> nHelp = new HashSet<>();
            for(Integer num : help){
                if(intToChar.containsKey(num)){
                    nHelp.add(intToChar(num));
                }else{
                    return null;
                }
            }
            return nHelp;
        }
        
}
