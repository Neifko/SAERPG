package model;

import java.util.ArrayList;

public class Player {
    private Scenario scenario;
    private int[] playerCoord;
    private int xp;
    private int duration;

    private ArrayList<String> states;

    public Player(Scenario parScenario) {
        scenario = parScenario;
        playerCoord = new int[]{0, 0};
    }

    /**
     * Solution efficace correspond au joueur qui realise toutes les quetes
     */
    private void efficace() {
        int questId = scenario.getProvQuests().get(0).getId() == 0 ? scenario.getProvQuests().get(1).getId(): scenario.getProvQuests().get(0).getId();
        float distMin = 9999f;
        Quest closestQuest = null;
        while (!scenario.getQuest(questId).isBoss()) {
            for (Quest quest : scenario.getProvQuests()) {
                if (quest.noPrecond()) {
                    // todo : if questid == id faire l'action necessaire pour verif la distance la plus courte
                    float dist = calculDistance(quest.getCoordinates());
                    if (dist < distMin) {
                        distMin = dist;
                        closestQuest = quest;
                    }
                }
            }
        }

        // todo : move player to quest coordinates
    }

    /**
     * Solution exhaustive correspond au joueur qui realise toutes les quetes
     */
    public void exhaustive() {
        ArrayList <Quest> availableQuests = new ArrayList <> (scenario.getProvQuests());
        ArrayList <Quest> completedQuests = new ArrayList <> ();

        if (!availableQuests.isEmpty()) {
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
        System.out.println("Quetes Completées : ");
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
