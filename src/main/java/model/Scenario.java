package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Scenario {
    private ArrayList<Quest> provQuests = new ArrayList<>();
    private String titre;

    /**
     * Ajoute les quêtes (dans le champ provQuests)
     * @param quest Quest
     */
    public void addQuest(Quest quest) {
        this.provQuests.add(quest);
    }

    /***
     * Récupère la liste des quêtes
     * @return ArrayList<Quest>
     */
    public ArrayList<Quest> getProvQuests(){
        return provQuests;
    }

    /***
     * Récupère la quête de la liste qui a le même id saisit en paramètre (parcours toute la liste pour la trouver)
     * @param questId int
     * @return Quest
     */
    public Quest getQuest(int questId){
        for (Quest quest :
                provQuests) {
            if (quest.getId() == questId){
                return quest;
            }
        }
        return null;
    }

    /**
     * Définit le titre du scénario
     * @param titre String
     */
    public void setTitle(String titre) {
        this.titre = titre;
    }

    /**
     * Récupère le titre du scénario
     * @return String
     */
    public String getTitle() {
        return titre;
    }

    /***
     * Permet d'obtenir une représentation textuelle de la taille de la liste des quêtes et la liste elle-même
     * @return la taille de la liste des quêtes ainsi que la liste elle-même
     */
    public String toString() {
        return this.provQuests.size() + " " + this.provQuests.toString();
    }
}
