package iofile;

import model.Quest;
import model.Scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadTextFile {
    public static Scenario read(File file){
        Scenario scenario = new Scenario();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                scenario.addQuest(new Quest(scanner.nextLine()));
            }
        }
        catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }
        return scenario;
    }
}
