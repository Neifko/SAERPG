package client;

import model.Quest;

public class ClientQuest {
    public static void main(String[] args) {
        Quest maquete = new Quest("1|(2,3)|((2,),(,))|3|200|tarzan dans les mines de gruyere");
        System.out.println(maquete);
    }
}
