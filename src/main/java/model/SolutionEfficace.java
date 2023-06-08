package model;

import java.util.ArrayList;
import java.util.Arrays;

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

        ArrayList<Quest> availableQuests = new ArrayList<>(scenario.getProvQuests());
        int numQuests = availableQuests.size();

        // Tableau pour stocker les durées optimales pour chaque combinaison de quêtes
        int[][] optimalDurations = new int[numQuests + 1][numQuests + 1];

        // Initialisation des durées optimales à une valeur maximale
        for (int i = 0; i <= numQuests; i++) {
            Arrays.fill(optimalDurations[i], Integer.MAX_VALUE);
        }

        // Cas de base : aucune quête
        optimalDurations[0][0] = 0;

        // Calcul des durées optimales pour toutes les combinaisons de quêtes
        for (int i = 1; i <= numQuests; i++) {
            Quest quest = availableQuests.get(i - 1);
            int duration = quest.getDuration();

            for (int j = 0; j <= i; j++) {
                // Exclusion de la quête actuelle
                optimalDurations[i][j] = Math.min(optimalDurations[i][j], optimalDurations[i - 1][j]);

                if (j > 0) {
                    // Inclusion de la quête actuelle
                    optimalDurations[i][j] = Math.min(optimalDurations[i][j], optimalDurations[i - 1][j - 1] + duration);
                }
            }
        }

        // Reconstruction de la solution optimale en termes de durée
        ArrayList<Quest> optimalQuests = new ArrayList<>();
        int j = numQuests;
        for (int i = numQuests; i >= 1; i--) {
            if (optimalDurations[i][j] < optimalDurations[i - 1][j]) {
                Quest quest = availableQuests.get(i - 1);
                optimalQuests.add(0, quest);
                j--;
            }
        }

        // Affichage de la solution optimale
        System.out.println("Solution optimale en termes de durée :");
        for (Quest quest : optimalQuests) {
            System.out.println(quest);
        }
        System.out.println("Durée totale : " + optimalDurations[numQuests][numQuests] + " unités de temps");
    }



}
