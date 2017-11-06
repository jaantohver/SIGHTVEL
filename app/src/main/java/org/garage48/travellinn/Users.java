package org.garage48.travellinn;

import java.util.ArrayList;

/**
 * Created by jaan on 04/11/2017.
 */

public class Users {
    public static ArrayList<User> List;

    static {
        User andres = new User();
        andres.name = "Andres";
        andres.image = R.drawable.andres;

        User jaan = new User();
        jaan.name = "Jaan";
        jaan.image = R.drawable.jaan;

        User papuna = new User();
        papuna.name = "Papuna";
        papuna.image = R.drawable.papuna;

        User triin = new User();
        triin.name = "Triin";
        triin.image = R.drawable.triin;

        List = new ArrayList<User>();
        List.add(andres);
        List.add(jaan);
        List.add(papuna);
        List.add(triin);
    }
}