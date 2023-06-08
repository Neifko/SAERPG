package model;

import java.util.ArrayList;

public class Player {
    private Scenario scenario;
    private int[] playerCoord;
    private ArrayList<String> states;
    private int duration;

    /***
     * Constructeur de la classe Player.
     * Initialise les attributs du joueur en fonction du scénario fourni.
     * @param parScenario Le scénario du jeu.
     */
    public Player(Scenario parScenario) {
        scenario = parScenario;
        playerCoord = new int[]{0, 0};
        states = new ArrayList<>();
    }

    public Scenario getScenario(){
        return scenario;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public int getDuration() {
        return duration;
    }

    public void doPlayerQuest(Quest quest, int xpTot) {
        duration += quest.getDuration();
        states.add("+" + quest.getDuration() + " : quête " + quest.getId() + "(total xp : " + xpTot + ")");
    }

    public void movePlayer(int[] coord, int distance) {
        duration += distance;
        playerCoord = coord;
        states.add("+" + distance + " : déplacement de (" + playerCoord[0] + "," + playerCoord[1] + ") à (" + coord[0] + "," + coord[1] + ")");
    }

    /***
     * Méthode pour afficher l'état actuel du joueur
     */
    public void showState() {
        for (String log : states) {
            System.out.println(log);
        }
        System.out.println("Durée : " + duration + " unités de temps");
    }

    /***
     * Permet d'obtenir une représentation textuelle de l'objet joueur
     * @return Une chaîne de caractères représentant le joueur ("Player 0")
     */
    public String toString() {
        return "Player 0";
    }

    public int[] getCoord() {
        return playerCoord;
    }
}
