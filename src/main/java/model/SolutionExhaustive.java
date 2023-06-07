package model;

import java.util.ArrayList;

public class SolutionExhaustive extends Solution{
    /**
     * Constructeur de la classe SolutionExhaustive.
     *
     * @param player le joueur
     */
    public SolutionExhaustive(Player player) {
        super(player);
    }

    /**
     * Algorithme de parcours exhaustif du scénario.
     *
     * @return le chemin exhaustif
     */
    public ArrayList<Quest> parcours() {
        // Liste des quêtes disponibles
        ArrayList<Quest> availableQuests = new ArrayList<>(scenario.getProvQuests());
        // Liste des quêtes complétées
        ArrayList<Quest> localCompletedQuests = new ArrayList<>();
        // Recherche de la quête 0
        Quest questZero = null;
        for (Quest quest : availableQuests) {
            if (quest.getId() == 0) {
                questZero = quest;
                break;
            }
        }
        // Suppression de la quête 0 des quêtes disponibles si elle existe
        if (questZero != null) {
            availableQuests.remove(questZero);
        }
        // Traitement des quêtes disponibles
        while (!availableQuests.isEmpty()) {
            // Recherche de la quête la plus proche et réalisable
            Quest closestQuest = null;
            int distMin = Integer.MAX_VALUE;
            for (Quest quest : availableQuests) {
                // Vérification des préconditions de la quête
                if (quest.noPrecond() || quest.hasCompletedPrecond(completedQuestsToIds(localCompletedQuests))) {
                    int dist = calculDistance(solPlayer.getCoord(), quest.getCoordinates());
                    // Mise à jour de la quête la plus proche si la distance est inférieure à la distance minimale
                    if (dist < distMin) {
                        distMin = dist;
                        closestQuest = quest;
                    }
                    // Si la distance est égale à la distance minimale, on choisit la quête avec un ID inférieur
                    else if (dist == distMin && quest.getId() < closestQuest.getId()) {
                        closestQuest = quest;
                    }
                }
            }
            // Réalisation de la quête la plus proche
            if (closestQuest != null) {
                move(closestQuest.getCoordinates());
                doQuest(closestQuest);
                localCompletedQuests.add(closestQuest);
                availableQuests.remove(closestQuest);
            }
        }
        // Ajout de la quête 0 en dernière position si elle existe
        if (questZero != null) {
            localCompletedQuests.add(questZero);
            // On joue la quete du boss
            move(questZero.getCoordinates());
            doQuest(questZero);
        }
        // Affichage des quêtes complétées
//        System.out.println("Quêtes Complétées :");
//        for (Quest quest : localCompletedQuests) {
//            System.out.println(quest);
//        }

        return localCompletedQuests;
    }
}
