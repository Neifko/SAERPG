package model;

import java.util.ArrayList;

public class Player {
    private Scenario scenario;
    private int[] playerCoord;
    private ArrayList<String> states;

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

    public void doPlayerQuest(Quest quest) {
        states.add("+" + quest.getDuration() + " : quête " + quest.getId()); // todo : afficher total xp -  + "(total xp : " + xp + ")"
    }

    public void movePlayer(int[] coord, int distance) {
        states.add("+" + distance + " : déplacement de (" + playerCoord[0] + "," + playerCoord[1] + ") à (" + coord[0] + "," + coord[1] + ")");
    }

    /***
     * Méthode pour afficher l'état actuel du joueur
     */
    public void showState() {
        for (String log : states) {
            System.out.println(log);
        }
        // todo : afficher la durée total
//        System.out.println("Durée : " + duration + " unités de temps");
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
