package model;

import iofile.ReadTextFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionEfficaceTest {
    Scenario scenario;
    Player monJoueur;
    SolutionEfficace solution;

    @BeforeEach
    void setUp() {
        scenario = ReadTextFile.read(new File("scenarios" + File.separator + "scenario_0.txt"));
        monJoueur = new Player(scenario);
        solution = new SolutionEfficace(monJoueur);
    }

    @Test
    void parcours() {
        ArrayList<Quest> result = solution.parcours();
        List<Integer> expectedIds = List.of(1, 2, 4, 0);
        List<Integer> actualIds = solution.completedQuestsToIds(result);
        assertEquals(expectedIds, actualIds);
    }

    @Test
    void speedrun() {
    }
}