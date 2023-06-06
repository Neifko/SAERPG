package model;

import java.util.ArrayList;

public class SolutionEfficace extends Solution{
    public SolutionEfficace(Player player) {
        super(player);
    }

    /**
     * Solution efficace correspond au joueur qui realise les quetes les plus proches
     */
    public ArrayList<Quest> parcours() {
        // Liste des quêtes disponibles
        ArrayList<Quest> availableQuests = new ArrayList<>(scenario.getProvQuests());
        // Liste des quêtes complétées
        ArrayList<Quest> localCompletedQuests = new ArrayList<>();
        // recupere la premiere quete du scenario en faisant en sorte que ce soit pas la quete du boss
        int questId = availableQuests.get(0).getId() == 0 ? availableQuests.get(1).getId() : availableQuests.get(0).getId();
        int distMin = 9999;
        Quest closestQuest = scenario.getQuest(questId);
        // On execute les quetes jusqu'a l'execution de la quete du boss
        int i = 10;
        while (!scenario.getQuest(questId).isBoss()) { //  && !(i<= 0)
            Quest bossQuest = scenario.getQuest(0);
            if (!(xp >= bossQuest.getExperience() && bossQuest.hasCompletedPrecond(completedQuestsToIds(localCompletedQuests)))) {
                // On regarde parmis les quetes celles qui n'a : pas de precondition puis on cherche la plus proche
                for (Quest quest : availableQuests) {
                    if (quest.noPrecond() || quest.hasCompletedPrecond(completedQuestsToIds(localCompletedQuests))) {
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
            localCompletedQuests.add(closestQuest);
            availableQuests.remove(closestQuest);
            distMin = 9999;

            System.out.println(closestQuest + " " + questId);

            i -= 1;
        }

        return localCompletedQuests;
    }

    public void speedrun(){
        ArrayList<Quest> quetesCheminOptimal;
        int dureeCheminOptimal;


    }
}
