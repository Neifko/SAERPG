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
        int questId = todoQuests.get(0).getId() == 0 ? todoQuests.get(1).getId(): todoQuests.get(0).getId();
        int distMin = 9999;
        Quest closestQuest = scenario.getQuest(questId);
        // On execute les quetes jusqu'a l'execution de la quete du boss
        int i = 10;
        while (!scenario.getQuest(questId).isBoss()) { //  && !(i<= 0)
            // On regarde parmis les quetes celles qui n'a : pas de precondition puis on cherche la plus proche
            for (Quest quest : todoQuests) {
                if (quest.noPrecond() || quest.hasCompletedPrecond(precondCompleted)) {
                    // On calcul la distance entre le joueur et la quete
                    int dist = calculDistance(quest.getCoordinates());
                    if (dist < distMin) {
                        distMin = dist;
                        closestQuest = quest;
                        questId = closestQuest.getId();
                    }
                }
            }
            // todo : move player to quest coordinates
            move(closestQuest.getCoordinates());
            doQuest(closestQuest);
            todoQuests.remove(closestQuest);
            distMin = 9999;

            System.out.println(todoQuests.size());
            System.out.println(distMin + " " + closestQuest + " " + questId);
            System.out.println("tour de boucle");

            i-= 1;
        }
    }

    private void doQuest(Quest quest){
        duration += quest.getDuration();
        xp += quest.getExperience();
        precondCompleted.add(quest.getId());
        states.add("+" + quest.getDuration() + " : quête " + quest.getId() + "(total xp : " + xp + ")");
    }

    private void move(int[] coord){
        // todo : ajouter la durée du movement a duration
        states.add("move");
    }

    /**
     * Solution exhaustive correspond au joueur qui realise toutes les quetes
     */
    public void exhaustive() {
        ArrayList<Quest> availableQuests = new ArrayList<>(scenario.getProvQuests());
        ArrayList<Quest> completedQuests = new ArrayList<>();

        while (!availableQuests.isEmpty()) {
            Quest closestQuest = availableQuests.get(0);
            int distMin = calculDistance(closestQuest.getCoordinates());

            for (Quest quest : availableQuests) {
                int dist = calculDistance(quest.getCoordinates());
                if (dist < distMin) {
                    distMin = dist;
                    closestQuest = quest;
                }
            }
            boolean canComplete = true;
            for (int precondition : closestQuest.getPreconditions()) {
                if (!completedQuests.contains(precondition)) {
                    canComplete = false;
                    break;
                }
            }
            if (!canComplete) {
                completedQuests.add(closestQuest);
                availableQuests.remove(closestQuest);
                continue;
            }

            playerCoord = closestQuest.getCoordinates();
            xp += closestQuest.getExperience();
            duration += closestQuest.getDuration();
            completedQuests.add(closestQuest);
            availableQuests.remove(closestQuest);
        }

        Iterator<Quest> iterator = completedQuests.iterator();
        while (iterator.hasNext()) {
            Quest postponedQuest = iterator.next();
            boolean canCompletePostponed = true;
            for (Quest precondition : postponedQuest.getPreconditions()) {
                if (!completedQuests.contains(precondition)) {
                    canCompletePostponed = false;
                    break;
                }
            }
            if (canCompletePostponed) {
                iterator.remove();
                availableQuests.add(postponedQuest);}

        System.out.println("Quêtes Complétées :");
        //*****************************************
        completedQuests.sort(Comparator.comparingInt(q -> Arrays.hashCode(q.getPreconditions())));
        //*****************************************
        for (Quest quest : completedQuests) {
            System.out.println(quest);
        }}
    }


    /**
     * calcule la distance entre les coordonnées du joueur et les coordonnées de la quete et la renvoie par la suite
     * @return float
     * @param questCoord int[]
     */
    private int calculDistance(int[] questCoord) {
        int[] playerCoord = this.playerCoord;

        int diffCoordX = questCoord[0] - playerCoord[0];
        int diffCoordY = questCoord[1] - playerCoord[1];

        return diffCoordX + diffCoordY;
    }

    public void showState() {
        for (String log : states){
            System.out.println(log);
        }
    }


    public String toString(){
        return "Player 0";
    }
}
