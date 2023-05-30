package client;

import iofile.ReadTextFile;
import model.Player;
import model.Quest;
import model.Scenario;

import java.io.File;

public class ClientQuest {
    public static void main(String[] args) {
//        Quest maquete = new Quest("1|(2,3)|((2,),(,))|3|200|tarzan dans les mines de gruyere");
//        System.out.println(maquete);

        Scenario scenario = ReadTextFile.read(new File("scenarios" + File.separator + "scenario_2.txt"));
//        System.out.println(scenario);

        Player monJoueur = new Player(scenario);
//        monJoueur.exhaustive();
//        int dist = monJoueur.calculDistance(new int[] {2,3});
//        System.out.println(dist);

        monJoueur.exhaustive();
    }
}
