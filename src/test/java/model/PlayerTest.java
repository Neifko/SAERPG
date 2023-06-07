package model;

import iofile.ReadTextFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

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
    }

    @Test
    void doPlayerQuest() {
    }

    @Test
    void movePlayer() {
        int[] newCoord = {2, 3};
        int distance = 5;

        player.movePlayer(newCoord, distance);

        assertArrayEquals(newCoord, player.getCoord());
    }

    @Test
    void showState() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getCoord() {
    }
}