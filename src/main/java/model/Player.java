package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Player {
    private Scenario scenario;
    private ArrayList<Quest> todoQuests;
    private int[] playerCoord;
    private int xp;
    private int duration;

    private ArrayList<String> states;

    public Player(Scenario parScenario) {
        scenario = parScenario;
        todoQuests = new ArrayList<>();
        for (Quest quest:scenario.getProvQuests()){
            todoQuests.add(quest);
        }
        playerCoord = new int[]{0, 0};
    }

    /**
     * Solution efficace correspond au joueur qui realise les quetes les plus proches
     */
    private void efficace() {
        int questId = todoQuests.get(0).getId() == 0 ? todoQuests.get(1).getId(): todoQuests.get(0).getId();
        float distMin = 9999f;
        Quest closestQuest = todoQuests.get(0);
        while (!scenario.getQuest(questId).isBoss()) {
            for (Quest quest : todoQuests) {
                if (quest.noPrecond()) {
                    // todo : if questid == id faire l'action necessaire pour verif la distance la plus courte
                    float dist = calculDistance(quest.getCoordinates());
                    if (dist < distMin) {
                        distMin = dist;
                        closestQuest = quest;
                    }
                }
            }
            // todo : move player to quest coordinates
            move(closestQuest.getCoordinates());
            doQuest(closestQuest);
            todoQuests.remove(closestQuest);

        }
    }

    private void doQuest(Quest quest){

    }

    private void move(int[] coord){
        System.out.println("move");
    }

    /**
     * Solution exhaustive correspond au joueur qui realise toutes les quetes
     */
    public void exhaustive() {
        ArrayList<Quest> availableQuests = new ArrayList<>(scenario.getProvQuests());
        ArrayList<Quest> completedQuests = new ArrayList<>();

        while (!availableQuests.isEmpty()) {
            Quest closestQuest = availableQuests.get(0);
            float distMin = calculDistance(closestQuest.getCoordinates());

            for (Quest quest : availableQuests) {
                float dist = calculDistance(quest.getCoordinates());
                if (dist < distMin) {
                    distMin = dist;
                    closestQuest = quest;
                }
            }

            playerCoord = closestQuest.getCoordinates();
            xp += closestQuest.getExperience();
            duration += closestQuest.getDuration();
            completedQuests.add(closestQuest);
            availableQuests.remove(closestQuest);
        }

        System.out.println("Quêtes Complétées (ordre des préconditions) :");
        //*****************************************
        completedQuests.sort(Comparator.comparingInt(q -> Arrays.hashCode(q.getPreconditions()))); ///****************************************************
        //*****************************************
        for (Quest quest : completedQuests) {
            System.out.println(quest);
        }
    }


    /**
     * calcule la distance entre les coordonnées du joueur et les coordonnées de la quete et la renvoie par la suite
     *
     * @param
     * @return float
     */
    private int calculDistance(int[] questCoord) {
        int[] playerCoord = this.playerCoord;

        int diffCoordX = questCoord[0] - playerCoord[0];
        int diffCoordY = questCoord[1] - playerCoord[1];

        return diffCoordX + diffCoordY;
    }

    private void showState() {

    }


    public String toString(){
        return "Player 0";
    }
}
