package model;

import java.util.ArrayList;

public class Player {
    private Scenario scenario;
    private int[] playerCoord;

    public Player(Scenario parScenario){
        scenario = parScenario;
        playerCoord = new int[] {0, 0};
    }

    /**
     * Solution efficace correspond a un joueur qui fait les quetes les plus proches
     */
    private void efficace(){
        int questId = scenario.getProvQuests().get(0).getId();
        for (Quest quest: scenario.getProvQuests()){
            if (quest.noPrecond()){
                // todo if questid == id faire l'action necessaire pour verif la distance la plus courte

            }
        }
    }

    /**
     * Solution exhaustive correspond au joueur qui realise toutes les quetes
     */
    public void exhaustive(){

    }

    /**
     * calcul Distance renvoie la distance entre les coordonnées du joueur et la quete quest
     * @return float
     */
    private float calculDistance(Quest quest){
        return 0f;
    }

    private void showState(){

    }

    public String toString(){
        return "Player 0";
    }
}
