import java.io.*;
import java.util.*;

/**
 * Created by Daniel Yun on 5/24/2018.
 */
public class Histogram {

    // Location of the input.txt file
    private static final String FILENAME = "E:\\CodingChallenge\\src\\input.txt";

    public static void main(String[] args) throws IOException{

        // Writer to create the output.txt file
        FileWriter writer = new FileWriter("output.txt");

        // Hash table to store the frequency of the words
        Map<String, Integer> counts = new HashMap<>();

        try {
            FileReader freader = new FileReader(FILENAME);
            BufferedReader breader = new BufferedReader(freader);

            String currentLine;

            // Check that there is a next line
            while((currentLine = breader.readLine()) != null) {

                // Split the words by white spaces
                for(String word : currentLine.split(" ")) {

                    // Check if the word ends with a comma or a period.
                    // If it does, truncate the comma or period
                    // Turn all the words to lowercase to keep different case words as the same
                    if (word.endsWith(",") || word.endsWith(".")) {
                        word = word.toLowerCase().substring(0, word.length()-1);

                        // Check of the counts hash table contains the word
                        // If it does, replace the current entry with an incremented value
                        // It it does not, put it in the table with an intial value of 1
                        if(counts.containsKey(word)){
                            counts.replace(word, counts.get(word), counts.get(word)+1);
                        } else{
                            counts.put(word, 1);
                        }

                    // The word does not end with comma or period so perform the same hashmap operation w/o trucating
                    } else{
                        word = word.toLowerCase();
                        if(counts.containsKey(word)){
                            counts.replace(word, counts.get(word), counts.get(word)+1);
                        } else{
                            counts.put(word, 1);
                        }
                    }
                }
            }

            // Iterate through the hashmap and get the keys and their corresponding values
            for(Map.Entry<String, Integer> entry : counts.entrySet()){

                // Add some padding to keep the histogram bar beginning uniform
                int paddingLength = 15 - entry.getKey().length();
                String spaces = String.format("%"+paddingLength+"s", "");
                String length = "";

                // Iterate to get the number of "=" to represent the size of the bar
                for(int i = 0; i < entry.getValue(); i++){
                    length += "=";
                }

                // Write to the output.txt file
                writer.write(entry.getKey() + spaces + "|" + length + " (" + entry.getValue() + ")" + "\n");

            }
            writer.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
