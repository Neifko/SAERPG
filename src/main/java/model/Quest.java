package model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Quest {
    private int id;
    private int[] position;
    private int[] preconditions;
    private int duration;
    private int experience;
    private String title;

    public boolean noPrecond() {
        for (int precond : this.preconditions) {
            if (precond != 0) {
                return false;
            }
        }
        return true;
    }

    public Quest(String line) {
        Scanner scanner = new Scanner(line).useDelimiter("\\|");
        while (scanner.hasNext()) {
            this.id = scanner.nextInt();
            String pos = scanner.next();
            this.position = extractStringToTab(pos, new int[2]);
            String precond = scanner.next();
            this.preconditions = extractStringToTab(precond, new int[4]);
            this.duration = scanner.nextInt();
            this.experience = scanner.nextInt();
            this.title = scanner.next();
        }
    }

    private int[] extractStringToTab(String string, int[] tab) {
        string = string.replace("(", "");
        string = string.replace(")", "");
        string = string.replace(" ", "");
        Scanner scanPrecondition = new Scanner(string).useDelimiter(",");
        int i = 0;
        while (scanPrecondition.hasNext()) {
            String extract = scanPrecondition.next();
            if (!extract.equals("")) {
                tab[i] = Integer.parseInt(extract);
            }
            i++;
        }

        return tab;
    }

    public String toString() {
        return this.id + " " + Arrays.toString(this.position) + " " + Arrays.toString(this.preconditions) + " " + this.duration + " " + this.experience + " " + this.title;
    }
}
