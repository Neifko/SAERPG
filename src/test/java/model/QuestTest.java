package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {

    @Test
    void noPrecond() {
        Quest quest1 = new Quest("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        assertTrue(quest1.noPrecond());

        Quest quest2 = new Quest("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        assertFalse(quest2.noPrecond());
    }

    @Test
    void isBoss() {
        Quest bossQuest = new Quest("0|(4, 3)|()|2|100|explorer pic de Bhanborim");
        assertTrue(bossQuest.isBoss());

        Quest nonBossQuest = new Quest("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        assertFalse(nonBossQuest.isBoss());
    }

    @Test
    void hasCompletedPrecond() {
        ArrayList<Integer> listquest = new ArrayList<>();
        Quest quest1 = new Quest("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Quest quest2 = new Quest("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        Quest quest3 = new Quest("3|(0, 4)|((2,),)|3|200|explorer palais de Ahehona");
        Quest quest4 = new Quest("6|(26, 26)|((7, 13),)|7|200|explorer plaine de Dhirgolir");
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
}