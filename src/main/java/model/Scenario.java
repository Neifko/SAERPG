package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Scenario {
    private ArrayList<Quest> provQuests;

    public void addQuest(Quest quest) {
        this.provQuests.add(quest);
    }

    public String toString() {
        return this.provQuests.size() + " " + this.provQuests.toString();
    }
}
