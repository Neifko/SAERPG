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
}