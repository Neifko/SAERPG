package client;

import iofile.ReadTextFile;
import model.*;

import java.io.File;

public class ClientQuest {
    public static void main(String[] args) {
//        Quest maquete = new Quest("1|(2,3)|((2,),(,))|3|200|tarzan dans les mines de gruyere");
//        System.out.println(maquete);

        Scenario scenario = ReadTextFile.read(new File("scenarios" + File.separator + "scenario_0.txt"));
//        System.out.println(scenario);

        Player monJoueur = new Player(scenario);
        SolutionEfficace solution = new SolutionEfficace(monJoueur);
//        monJoueur.exhaustive();
//        int dist = monJoueur.calculDistance(new int[] {2,3});
//        System.out.println(dist);

        solution.parcours();
        monJoueur.showState();

        solution.speedrun();
    }
}
