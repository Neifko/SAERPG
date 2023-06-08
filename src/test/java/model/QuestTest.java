package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {
    ArrayList<Integer> listquest;
    Quest quest1;
    Quest quest2;
    Quest quest3;
    Quest quest4;
    Quest bossQuest;

    @BeforeEach
    void setUp(){
        listquest = new ArrayList<>();
        quest1 = new Quest("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        quest2 = new Quest("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        quest3 = new Quest("3|(0, 4)|((2,),)|3|200|explorer palais de Ahehona");
        quest4 = new Quest("6|(26, 26)|((7, 13),)|7|200|explorer plaine de Dhirgolir");
        bossQuest = new Quest("0|(4, 3)|()|2|100|explorer pic de Bhanborim");
    }

    @Test
    void noPrecond() {
        assertTrue(quest1.noPrecond());

        assertFalse(quest2.noPrecond());
    }

    @Test
    void isBoss() {

        assertTrue(bossQuest.isBoss());
        assertFalse(quest1.isBoss());
    }

    @Test
    void hasCompletedPrecond() {

        assertTrue(quest1.hasCompletedPrecond(listquest));
        listquest.add(1);
        assertTrue(quest2.hasCompletedPrecond(listquest));
        listquest.add(2);
        assertTrue(quest3.hasCompletedPrecond(listquest));
        listquest.remove(1);
        assertFalse(quest3.hasCompletedPrecond(listquest));
        listquest.add(7);
        listquest.add(13);
        assertTrue(quest4.hasCompletedPrecond(listquest));
    }

    @Test
    void getFormattedCoordinates(){
        assertEquals(quest1.getFormattedCoordinates(), "(4, 3)");
    }

    @Test
    void extractStringToTab(){
        assertArrayEquals(quest1.extractStringToTab(quest1.getFormattedCoordinates(), new int[2]), new int[]{4, 3});
    }
}