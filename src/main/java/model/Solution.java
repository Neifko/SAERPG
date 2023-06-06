package model;

import java.util.ArrayList;

public class Solution {

    protected static int xp;
    protected int duration;
    protected Scenario scenario;
    protected Player solPlayer;

    public Solution(Player player) {
        this.solPlayer = player;
        scenario = solPlayer.getScenario();
    }

    public ArrayList<Quest> parcours(){
        return new ArrayList<>();
    }

    protected ArrayList<Integer> completedQuestsToIds(ArrayList<Quest> completedQuests) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Quest quest : completedQuests
        ) {
            ids.add(quest.getId());
        }
        return ids;
    }

    protected void doQuest(Quest quest) {
        duration += quest.getDuration();
        if (!quest.isBoss()) {
            xp += quest.getExperience();
        }
        solPlayer.doPlayerQuest(quest);
    }

    protected void move(int[] coord) {
        duration += calculDistance(solPlayer.getCoord(), coord);
        solPlayer.movePlayer(coord, calculDistance(solPlayer.getCoord(), coord));
    }

    /**
     * calcule la distance entre les coordonnées du joueur et les coordonnées de la quete et la renvoie par la suite
     *
     * @param questCoord int[]
     * @return float
     */
    protected int calculDistance(int[] playerCoord, int[] questCoord) {

        // Calcul des différences de coordonnées en X et en Y
        int diffCoordX = questCoord[0] - playerCoord[0];
        int diffCoordY = questCoord[1] - playerCoord[1];
        // Calcul de la distance en utilisant la distance de Manhattan (somme des différences absolues)
        return Math.abs(diffCoordX) + Math.abs(diffCoordY);
    }
}
