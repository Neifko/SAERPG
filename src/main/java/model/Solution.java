package model;

import java.util.ArrayList;

public class Solution {
    private ArrayList<Quest> todoQuests;
    private ArrayList<Quest> completedQuests;

    private static int xp;
    private int duration;
    private Player solPlayer;

    public Solution(Player player){
        completedQuests = new ArrayList<>();
        this.solPlayer = player;
    }

    /**
     * Solution efficace correspond au joueur qui realise les quetes les plus proches
     */
    public void efficace(Scenario scenario) {
        // recupere la premiere quete du scenario en faisant en sorte que ce soit pas la quete du boss
        int questId = todoQuests.get(0).getId() == 0 ? todoQuests.get(1).getId() : todoQuests.get(0).getId();
        int distMin = 9999;
        Quest closestQuest = scenario.getQuest(questId);
        // On execute les quetes jusqu'a l'execution de la quete du boss
        int i = 10;
        while (!scenario.getQuest(questId).isBoss()) { //  && !(i<= 0)
            Quest bossQuest = scenario.getQuest(0);
            if (!(xp >= bossQuest.getExperience() && bossQuest.hasCompletedPrecond(completedQuestsToIds(completedQuests)))) {
                // On regarde parmis les quetes celles qui n'a : pas de precondition puis on cherche la plus proche
                for (Quest quest : todoQuests) {
                    if (quest.noPrecond() || quest.hasCompletedPrecond(completedQuestsToIds(completedQuests))) {
                        // On calcul la distance entre le joueur et la quete
                        int dist = calculDistance(solPlayer.getCoord(), quest.getCoordinates());
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

        resetCompletedQuests();
    }

    private ArrayList<Integer> completedQuestsToIds(ArrayList<Quest> completedQuests) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Quest quest: completedQuests
             ) {
            ids.add(quest.getId());
        }
        return ids;
    }

    private void doQuest(Quest quest) {
        duration += quest.getDuration();
        if (!quest.isBoss()) {
            xp += quest.getExperience();
        }
        completedQuests.add(quest);
        solPlayer.doPlayerQuest(quest);
    }

    private void move(int[] coord) {
        duration += calculDistance(solPlayer.getCoord(), coord);
        solPlayer.movePlayer(coord);
    }

    /**
     * calcule la distance entre les coordonnées du joueur et les coordonnées de la quete et la renvoie par la suite
     *
     * @param questCoord int[]
     * @return float
     */
    private int calculDistance(int[] playerCoord, int[] questCoord) {

        // Calcul des différences de coordonnées en X et en Y
        int diffCoordX = questCoord[0] - playerCoord[0];
        int diffCoordY = questCoord[1] - playerCoord[1];
        // Calcul de la distance en utilisant la distance de Manhattan (somme des différences absolues)
        return Math.abs(diffCoordX) + Math.abs(diffCoordY);
    }

    private void resetCompletedQuests(){
        completedQuests = new ArrayList<>();
    }
}