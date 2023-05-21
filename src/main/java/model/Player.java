package model;

import java.util.ArrayList;

public class Player {
    private Scenario scenario;
    private int[] playerCoord;
    private int xp;
    private int duration;

    private ArrayList<String> states;

    public Player(Scenario parScenario){
        scenario = parScenario;
        playerCoord = new int[] {0, 0};
    }

    private void efficace(){
        int questId = scenario.getProvQuests().get(0).getId();
        float distMin = 9999f;
        Quest closestQuest = null;
        for (Quest quest: scenario.getProvQuests()){
            if (quest.noPrecond()){
                // todo if questid == id faire l'action necessaire pour verif la distance la plus courte
                float dist = calculDistance(quest);
                if (dist < distMin){
                    distMin = dist;
                    closestQuest = quest;
                }
            }
        }

        // todo : move player to quest coordinates
    }

    /**
     * Solution exhaustive correspond au joueur qui realise toutes les quetes
     */
    public void exhaustive(){

    }

    /**
     * calcule la distance entre les coordonnées du joueur et les coordonnées de la quete et la renvoie par la suite
     * @return float
     * @param quest Quest
     */
    private float calculDistance(Quest quest){
        int[] questCoord = quest.getCoordinates();
        int[] playerCoord = this.playerCoord;

        int diffCoordX = questCoord[0] - playerCoord[0];
        int diffCoordY = questCoord[1] - playerCoord[1];

        return (float) Math.sqrt(diffCoordX * diffCoordX + diffCoordY  * diffCoordY );
    }

    private void showState(){

    }


    public String toString(){
        return "Player 0";
    }
}
