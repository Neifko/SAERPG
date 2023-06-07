package model;

import java.util.ArrayList;

public class SolutionEfficace extends Solution {
    /**
     * Constructeur de la classe SolutionEfficace
     * @param player le joueur
     */
    public SolutionEfficace(Player player) {
        super(player);
    }

    /**
     * Algorithme de parcours efficace du scénario.
     *
     * @return le chemin efficace
     */
    public ArrayList<Quest> parcours() {
        // Liste des quêtes disponibles
        ArrayList<Quest> availableQuests = new ArrayList<>(scenario.getProvQuests());
//        System.out.println(availableQuests);
        // Liste des quêtes complétées
        ArrayList<Quest> localCompletedQuests = new ArrayList<>();
        // récupère la première quête du scénario en faisant en sorte que ce ne soit pas la quête du boss
        int questId = availableQuests.get(0).getId() == 0 ? availableQuests.get(1).getId() : availableQuests.get(0).getId();
        int distMin = 9999;
        Quest closestQuest = scenario.getQuest(questId);
        // On exécute les quêtes jusqu'à l'exécution de la quête du boss
        int i = 10;
        while (!scenario.getQuest(questId).isBoss() && i > 0) { // modifié && i > 0 ajouté
            Quest bossQuest = scenario.getQuest(0);
            if (!(xp >= bossQuest.getExperience() && bossQuest.hasCompletedPrecond(completedQuestsToIds(localCompletedQuests)))) {
                // On regarde parmi les quêtes celles qui n'ont pas de précondition, puis on cherche la plus proche
                for (Quest quest : availableQuests) {
                    if (quest.noPrecond() || quest.hasCompletedPrecond(completedQuestsToIds(localCompletedQuests))) {
                        // On calcule la distance entre le joueur et la quête
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

//            System.out.println(closestQuest + " " + questId);

            i -= 1;
        }

        return localCompletedQuests;
    }

    public void speedrun() {
        System.out.println("DEBUT SPEEDRUN");
        // On recupere le trajet efficace
        ArrayList<Quest> optimalQuests = parcours();
        DurationWrapper optimalDuration = new DurationWrapper(Integer.MAX_VALUE);

        ArrayList<Quest> currentQuests = new ArrayList<>();
        int currentDuration = 0;

        generateCombinations(new ArrayList<>(scenario.getProvQuests()), currentQuests, 0, currentDuration, optimalQuests, optimalDuration);

        System.out.println("Solution optimale en termes de durée :");
        for (Quest quest : optimalQuests) {
            System.out.println(quest);
        }
        System.out.println("Durée totale : " + optimalDuration.getDuration() + " unités de temps");
    }

    private void generateCombinations(ArrayList<Quest> availableQuests, ArrayList<Quest> currentQuests, int index, int currentDuration, ArrayList<Quest> optimalQuests, DurationWrapper optimalDuration) {
        System.out.println("Generate combination : " + currentDuration + " | current quests : " + currentQuests);

        if (currentDuration >= optimalDuration.getDuration()) {
            System.out.println(" current duration plus grand que optimal duration");
            return;
        }

        if (index == availableQuests.size()) {
            System.out.println(" On a parcouru toutes les quetes");
            if (currentDuration < optimalDuration.getDuration()) {
                System.out.println("  current duration plus petit que optimal duration");
                optimalDuration.setDuration(currentDuration);
                optimalQuests.clear();
                optimalQuests.addAll(currentQuests);
            }
            return;
        }

        Quest currentQuest = availableQuests.get(index);

        generateCombinations(availableQuests, currentQuests, index + 1, currentDuration, optimalQuests, optimalDuration);
        currentQuests.add(currentQuest);
        currentDuration += currentQuest.getDuration();

        generateCombinations(availableQuests, currentQuests, index + 1, currentDuration, optimalQuests, optimalDuration);
        currentQuests.remove(currentQuest);
        currentDuration -= currentQuest.getDuration();

        // Mettre à jour la durée optimale lorsqu'une solution optimale est trouvée
        if (currentDuration < optimalDuration.getDuration()) {
            optimalDuration.setDuration(currentDuration);
        }
    }
}
