package model;

import java.util.ArrayList;

public class Solution {

    protected int xp;
    protected int duration;
    protected int distance;
    protected Scenario scenario;
    protected Player solPlayer;

    /**
     * Constructeur de la classe Solution. Initialise le joueur et récupère le scénario associé.
     *
     * @param player Le joueur utilisé dans la solution.
     */
    public Solution(Player player) {
        this.solPlayer = player;
        scenario = solPlayer.getScenario();
    }

    /**
     * Parcourt les quêtes et renvoie une liste vide (dans ce cas la) (les algorithmes sont dans les classes filles).
     *
     * @return Une liste vide d'objets Quest.
     */
    public ArrayList<Quest> parcours(){
        return new ArrayList<>();
    }

    /**
     * Convertit une liste de quêtes terminées en une liste d'identifiants de quêtes.
     *
     * @param completedQuests La liste des quêtes terminées.
     * @return Une liste d'entiers représentant les identifiants des quêtes terminées.
     */
    protected ArrayList<Integer> completedQuestsToIds(ArrayList<Quest> completedQuests) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Quest quest : completedQuests
        ) {
            ids.add(quest.getId());
        }
        return ids;
    }

    /**
     * Effectue une quête spécifique, met à jour la durée, l'expérience et execute la quete chez le joueur.
     *
     * @param quest La quête à effectuer.
     */
    protected void doQuest(Quest quest) {
        duration += quest.getDuration();
        if (!quest.isBoss()) {
            xp += quest.getExperience();
        }
        solPlayer.doPlayerQuest(quest, xp);
    }

    /**
     * Déplace le joueur vers les coordonnées spécifiées, met à jour la durée et la distance parcourue, et execute
     * le deplacement chez le joueur.
     *
     * @param coord Les nouvelles coordonnées du joueur.
     */
    protected void move(int[] coord) {
        int dist = calculDistance(solPlayer.getCoord(), coord);
        duration += dist;
        distance += dist;
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
