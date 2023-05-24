package model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Quest implements Comparable<Quest>{
    private int id;
    private int[] position;
    private int[] preconditions;
    private int duration;
    private int experience;
    private String title;

    /**
     * Vérifie si la quête n'a aucune précondition.
     * @return boolean
     */
    public boolean noPrecond() {
        for (int precond : this.preconditions) {
            if (precond != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Constructeur de la classe Quest.
     * @param line la chaîne de caractères contenant les informations de la quête
     */
    public Quest(String line) {
        // Pinaise
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

    /**
     * Extrait les coordonnées ou les préconditions de la quête à partir d'une chaîne de caractères.
     * @param string la chaîne de caractères contenant les informations
     * @param tab le tableau dans lequel stocker les informations extraites
     * @return int[] le tableau contenant les informations extraites
     */
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

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de la quête.
     * @return String
     */
    public String toString() {
        return this.id + " " + Arrays.toString(this.position) + " " + Arrays.toString(this.preconditions) + " " + this.duration + " " + this.experience + " " + this.title;
    }

    /**
     * Renvoie l'identifiant de la quête.
     * @return int
     */
    public int getId() {return this.id;}

    /**
     * Renvoie les coordonnées de la quête.
     * @return int[]
     */
    public int[] getCoordinates() {return this.position;}

    /**
     * Renvoie l'expérience de la quête.
     * @return int
     */
    public int getExperience() {return this.experience;}

    /**
     * Renvoie la durée de la quête.
     * @return int
     */
    public int getDuration() {return this.duration;}

    /**
     * Renvoie le titre de la quête.
     * @return String
     */
    public String getTitle() {return this.title;}

    /**
     * Renvoie le le tableau des préconditions.
     * @return int[]
     */
    public int[] getPreconditions() {
        return this.preconditions;
    }

    /**
     * Vérifie si la quête est un boss.
     * @return boolean
     */
    public boolean isBoss() {
        return this.id == 0;
    }

    /**
     * Compare 2 quêtes (dont une reçue en paramètre).
     * @return int
     * @param quest Quest
     */
    @Override
    public int compareTo(Quest quest) {
//        if (quest.getId() > this.id) return -1;
//        if (quest.getId() < this.id) return 1;
        return Integer.compare(quest.getId(), this.id);
    }

    public boolean hasCompletedPrecond(ArrayList<Integer> precondCompleted) {
        for (int i : preconditions){
            if (!precondCompleted.contains(i) && i != 0){
                return false;
            }
        }
        return true;
    }
}
