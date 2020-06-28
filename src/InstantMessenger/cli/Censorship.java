package InstantMessenger.cli;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Censorship {
    private static HashMap<String, String> dictionary;
    private static Censorship censorship;

    private Censorship(){
        if (dictionary == null){
            dictionary = new HashMap<>();
        }
        try(Scanner sc = new Scanner(new File(Const.CENSORSHIP_FILENAME))){
            while (sc.hasNextLine()){
                String[] keyValue = sc.nextLine().split(" ");
                dictionary.put(keyValue[0], keyValue[1]);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Censorship initCensorship(){
        if (censorship == null){
            censorship = new Censorship();
        }
        return censorship;
    }

    public String moderate(String msg) {
        StringBuilder sb = new StringBuilder();
        for (String s: msg.split(" ")) {
            sb.append(moderateWord(s)).append(" ");
        }
        return sb.toString().trim();
    }

    public String moderateWord(String word) {
        return dictionary.getOrDefault(word,word);
    }
}
