package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Scenario {
    private ArrayList<Quest> provQuests = new ArrayList<>();

    public void addQuest(Quest quest) {
        this.provQuests.add(quest);
    }

    public ArrayList<Quest> getProvQuests(){
        return provQuests;
    }

    public Quest getQuest(int questId){
        for (Quest quest :
                provQuests) {
            if (quest.getId() == questId){
                return quest;
            }
        }
        return null;
    }

    public String toString() {
        return this.provQuests.size() + " " + this.provQuests.toString();
    }
}
