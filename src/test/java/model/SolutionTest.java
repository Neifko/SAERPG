package model;

import iofile.ReadTextFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void completedQuestsToIds() {
        Scenario scenario = ReadTextFile.read(new File("scenarios" + File.separator + "scenario_0.txt"));
        Player monJoueur = new Player(scenario);
        SolutionEfficace solution = new SolutionEfficace(monJoueur);

        int[] resultatAttendu = new int[] {1,2,4,0};
        for (int i=0; i<resultatAttendu.length; i++) {
            assertEquals(resultatAttendu[i], solution.completedQuestsToIds(solution.parcours()).get(i));
        }

    }

    @Test
    void calculDistance() {
        Scenario scenario = ReadTextFile.read(new File("scenarios" + File.separator + "scenario_0.txt"));
        Player monJoueur = new Player(scenario);
        SolutionEfficace solution = new SolutionEfficace(monJoueur);

        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{9,3}), 12);
        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{3,9}), 12);
        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{-9,3}), 12);
        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{9,-3}), 12);
        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{4,5}), 9);
        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{89,106}), 89+106);

        monJoueur.movePlayer(new int[]{9,3}, 12);
        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{14,5}), 7);
        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{1,2}), 9);
        assertEquals(solution.calculDistance(monJoueur.getCoord(), new int[]{100,0}), 94);

    }
    @Test
    void doQuest() {
        Scenario scenario = ReadTextFile.read(new File("scenarios" + File.separator + "scenario_0.txt"));
        Player monJoueur = new Player(scenario);
        Solution solution = new Solution(monJoueur);

        Quest quest = new Quest("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        solution.doQuest(quest);

        assertEquals(10, solution.getXp());
        assertEquals(5, solution.getDuration());
        assertTrue(monJoueur.getStates().contains("+5 : quête 1(total xp : 10)"));
    }

    @Test
    void move() {
        Scenario scenario = ReadTextFile.read(new File("scenarios" + File.separator + "scenario_0.txt"));
        Player monJoueur = new Player(scenario);
        Solution solution = new Solution(monJoueur);

        int[] newCoord = {2, 3};
        solution.move(newCoord);

        assertArrayEquals(newCoord, monJoueur.getCoord());
        assertEquals(5, solution.getDuration());
        assertEquals(5, solution.getDistance());
        assertTrue(monJoueur.getStates().contains("+5 : déplacement de (0,0) à (2,3)"));
    }
}