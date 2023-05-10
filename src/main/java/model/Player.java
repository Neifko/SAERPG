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
        for (Quest quest: scenario.getProvQuests()){
//            if (quest.)
        }
    }

    private void showState(){

    }

    public String toString(){
        return "Player 0";
    }
}
