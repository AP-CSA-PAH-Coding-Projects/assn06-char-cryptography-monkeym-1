package apcsa.githubtrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<CString> wordsList = new ArrayList<>();
        
        // Define the file
        String fileName = "secretMessage.txt";
        File inputFile = new File(fileName);

        Scanner scanner = new Scanner(inputFile);

        // Read line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Split by whitespace to get individual words
            String[] tokens = line.split("\\s+");
             
            for (String token : tokens) {
                if (!token.isEmpty()) {
                    wordsList.add(new CString(token));
                }
            }
        }
        scanner.close();

        // Convert List to Array
        CString[] words = wordsList.toArray(new CString[0]);

        //Decrypt each word
        for (int i = 0; i < words.length; i++) {
            words[i] = CStringUtil.decrypt(words[i]);
        }

        // Calculate rotation offset
        // Find the character with the largest ASCII value using cast to int
        int maxAscii = 0;
        for (CString word : words) {
            for (char c : word.getData()) {
                if ((int) c > maxAscii) {
                    maxAscii = (int) c;
                }
            }
        }
        int d = maxAscii - 60;

        //Rotate the array
        rotate(words, d);

        // Print
       StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i].toString());
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

 // rotate method
    public static void rotate(CString[] arr, int d) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int n = arr.length;
        d = d %n;
        if (d < 0) {
            d += n;
        }
        // Create a temporary array 
        CString[] temp = new CString[n];
        
        for (int i = 0; i < n; i++) {
            temp[i] = arr[(i + d) % n];
        }
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }
}
