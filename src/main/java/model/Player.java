package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class Player {
    private Scenario scenario;
    private ArrayList<Quest> todoQuests;
    private int[] playerCoord;
    private int xp;
    private int duration;
    private ArrayList<Integer> precondCompleted;

    private ArrayList<String> states;

    /***
     * Constructeur de la classe Player.
     * Initialise les attributs du joueur en fonction du scénario fourni.
     * @param parScenario Le scénario du jeu.
     */
    public Player(Scenario parScenario) {
        scenario = parScenario;
        todoQuests = new ArrayList<>();
        todoQuests.addAll(scenario.getProvQuests());
        playerCoord = new int[]{0, 0};
        precondCompleted = new ArrayList<>();
        states = new ArrayList<>();
    }

    /**
     * Solution efficace correspond au joueur qui realise les quetes les plus proches
     */
    public void efficace() {
        // recupere la premiere quete du scenario en faisant en sorte que ce soit pas la quete du boss
        int questId = todoQuests.get(0).getId() == 0 ? todoQuests.get(1).getId() : todoQuests.get(0).getId();
        int distMin = 9999;
        Quest closestQuest = scenario.getQuest(questId);
        // On execute les quetes jusqu'a l'execution de la quete du boss
        int i = 10;
        while (!scenario.getQuest(questId).isBoss()) { //  && !(i<= 0)
            Quest bossQuest = scenario.getQuest(0);
            if (!(xp >= bossQuest.getExperience() && bossQuest.hasCompletedPrecond(precondCompleted))) {
                System.out.println("pas bon" + xp);
                // On regarde parmis les quetes celles qui n'a : pas de precondition puis on cherche la plus proche
                for (Quest quest : todoQuests) {
                    if (quest.noPrecond() || quest.hasCompletedPrecond(precondCompleted)) {
                        // On calcul la distance entre le joueur et la quete
                        int dist = calculDistance(quest.getCoordinates());
                        if (dist < distMin) {
                            if ((quest.isBoss() && xp >= quest.getExperience()) || !quest.isBoss()) {
                                distMin = dist;
                                closestQuest = quest;
                                questId = closestQuest.getId();
                            }
                        }
                    }
                }
            } else {
                System.out.println("bon" + xp);
                closestQuest = bossQuest;
                questId = closestQuest.getId();
            }

            move(closestQuest.getCoordinates());
            doQuest(closestQuest);
            todoQuests.remove(closestQuest);
            distMin = 9999;

            System.out.println(closestQuest + " " + questId);

            i -= 1;
        }
    }

    private void doQuest(Quest quest) {
        duration += quest.getDuration();
        if (!quest.isBoss()) {
            xp += quest.getExperience();
        }
        precondCompleted.add(quest.getId());
        states.add("+" + quest.getDuration() + " : quête " + quest.getId() + "(total xp : " + xp + ")");
    }

    private void move(int[] coord) {
        duration += calculDistance(coord);
        states.add("+" + calculDistance(coord) + " : déplacement de (" + playerCoord[0] + "," + playerCoord[1] + ") à (" + coord[0] + "," + coord[1] + ")");
        playerCoord = coord;
    }

    /**
     * Solution exhaustive correspond au joueur qui realise toutes les quetes
     */
    public void exhaustive() {
        // Liste des quêtes disponibles
        ArrayList<Quest> availableQuests = new ArrayList<>(scenario.getProvQuests());
        // Liste des quêtes complétées
        ArrayList<Quest> completedQuests = new ArrayList<>();
        // Recherche de la quête 0
        Quest questZero = null;
        for (Quest quest : availableQuests) {
            if (quest.getId() == 0) {
                questZero = quest;
                break;
            }
        }
        // Suppression de la quête 0 des quêtes disponibles si elle existe
        if (questZero != null) {
            availableQuests.remove(questZero);
        }
        // Traitement des quêtes disponibles
        while (!availableQuests.isEmpty()) {
            // Recherche de la quête la plus proche et réalisable
            Quest closestQuest = null;
            int distMin = Integer.MAX_VALUE;
            for (Quest quest : availableQuests) {
                // Vérification des préconditions de la quête
                if (quest.noPrecond() || quest.hasCompletedPrecond(precondCompleted)) {
                    int dist = calculDistance(quest.getCoordinates());
                    // Mise à jour de la quête la plus proche si la distance est inférieure à la distance minimale
                    if (dist < distMin) {
                        distMin = dist;
                        closestQuest = quest;
                    }
                    // Si la distance est égale à la distance minimale, on choisit la quête avec un ID inférieur
                    else if (dist == distMin && quest.getId() < closestQuest.getId()) {
                        closestQuest = quest;
                    }
                }
            }
            // Réalisation de la quête la plus proche
            if (closestQuest != null) {
                move(closestQuest.getCoordinates());
                doQuest(closestQuest);
                completedQuests.add(closestQuest);
                availableQuests.remove(closestQuest);
            }
        }
        // Ajout de la quête 0 en dernière position si elle existe
        if (questZero != null) {
            completedQuests.add(questZero);
        }
        // Affichage des quêtes complétées
        System.out.println("Quêtes Complétées :");
        for (Quest quest : completedQuests) {
            System.out.println(quest);
        }
    }

    /**
     * calcule la distance entre les coordonnées du joueur et les coordonnées de la quete et la renvoie par la suite
     *
     * @param questCoord int[]
     * @return float
     */
    private int calculDistance(int[] questCoord) {
        // Utilisez les coordonnées du joueur this.playerCoord au lieu de déclarer une nouvelle variable locale int[] playerCoord
        int[] playerCoord = this.playerCoord;
        // Calcul des différences de coordonnées en X et en Y
        int diffCoordX = questCoord[0] - playerCoord[0];
        int diffCoordY = questCoord[1] - playerCoord[1];
        // Calcul de la distance en utilisant la distance de Manhattan (somme des différences absolues)
        return Math.abs(diffCoordX) + Math.abs(diffCoordY);
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
}
