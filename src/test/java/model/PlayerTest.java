package model;

import iofile.ReadTextFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Solution solution;
    private Player player;
    private Scenario scenario;

    @BeforeEach
    void setUp() {
        scenario = ReadTextFile.read(new File("scenarios" + File.separator + "scenario_0.txt"));
        player = new Player(scenario);
        solution = new Solution(player);
    }

    @Test
    void getScenario() {
        assertEquals(scenario, player.getScenario());
    }

    @Test
    void doPlayerQuest() {
        Quest quest = scenario.getQuests().get(0);
        int xpTot = 100;

        player.doPlayerQuest(quest, xpTot);

        assertEquals(quest.getDuration(), player.getDuration());
        assertTrue(player.getStates().contains("+" + quest.getDuration() + " : quête " + quest.getId() + "(total xp : " + xpTot + ")"));
    }

    @Test
    void movePlayer() {
        int[] newCoord = {2, 3};
        int distance = 5;

        int initialDuration = player.getDuration();
        ArrayList<String> initialStates = new ArrayList<>(player.getStates());

        player.movePlayer(newCoord, distance);

        assertArrayEquals(newCoord, player.getCoord());
        assertEquals(initialDuration + distance, player.getDuration());
        assertTrue(player.getStates().containsAll(initialStates));
        assertFalse(player.getStates().contains("+" + distance + " : déplacement de (0,0) à (2,3)"));
    }


    @Test
    void showState() {
        // Test de l'affichage, il est difficile de faire une assertion précise ici
        player.movePlayer(new int[]{1, 2}, 3);
        player.doPlayerQuest(scenario.getQuests().get(0), 100);
        player.showState();
    }

    @Test
    void testToString() {
        assertEquals("Player 0", player.toString());
    }

    @Test
    void getCoord() {
        int[] coord = player.getCoord();

        assertNotNull(coord);
        assertEquals(2, coord.length);
    }

    @Test
    void testDurationWithNoActions() {
        assertEquals(0, player.getDuration());
    }

    @Test
    void testDoMultiplePlayerQuests() {
        Quest quest1 = scenario.getQuests().get(0);
        Quest quest2 = scenario.getQuests().get(1);
        int xpTot = 200;

        player.doPlayerQuest(quest1, xpTot);
        player.doPlayerQuest(quest2, xpTot);

        assertEquals(quest1.getDuration() + quest2.getDuration(), player.getDuration());
        assertTrue(player.getStates().contains("+" + quest1.getDuration() + " : quête " + quest1.getId() + "(total xp : " + xpTot + ")"));
        assertTrue(player.getStates().contains("+" + quest2.getDuration() + " : quête " + quest2.getId() + "(total xp : " + xpTot + ")"));
    }

    @Test
    void testMovePlayerMultipleTimes() {
        int[] newCoord1 = {1, 2};
        int[] newCoord2 = {3, 4};
        int distance1 = 5;
        int distance2 = 10;

        int initialDuration = player.getDuration();

        player.movePlayer(newCoord1, distance1);

        int durationAfterFirstMove = player.getDuration();

        player.movePlayer(newCoord2, distance2);

        assertArrayEquals(newCoord2, player.getCoord());
        assertEquals(initialDuration + distance1 + distance2, player.getDuration());
        assertFalse(player.getStates().contains("+" + distance1 + " : déplacement de (0,0) à (1,2)"));
        assertTrue(player.getStates().contains("+" + distance2 + " : déplacement de (1,2) à (3,4)"));
        assertEquals(durationAfterFirstMove + distance2, player.getDuration());
    }

    @Test
    void testShowStateWithNoActions() {
        player.showState();
    }

    @Test
    void testGetCoordWithDefaultPlayerCoord() {
        int[] defaultCoord = {0, 0};
        assertArrayEquals(defaultCoord, player.getCoord());
    }
}
