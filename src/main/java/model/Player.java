package model;

import java.util.ArrayList;

public class Player {
    private Scenario scenario;
    private int[] playerCoord;

    public Player(Scenario parScenario){
        scenario = parScenario;
        playerCoord = new int[] {0, 0};
    }

    private void efficace(){
        int questId = scenario.getProvQuests().get(0).getId();
        for (Quest quest: scenario.getProvQuests()){
            if (quest.noPrecond()){
                // todo if questid == id faire l'action necessaire pour verif la distance la plus courte
            }
        }
    }

    private void showState(){

    }

    public String toString(){
        return "Player 0";
    }
}
