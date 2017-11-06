package org.garage48.travellinn;

import java.util.ArrayList;

/**
 * Created by jaan on 04/11/2017.
 */

public class Locations {
    public static ArrayList<Location> List;

    static {
        Location jaaniKirik = new Location();
        jaaniKirik.name = "Zavood (2 photos, 1 note)";
        jaaniKirik.latitude = 58.3835809;
        jaaniKirik.longitude = 26.7170644;

        Location vanemuine = new Location();
        vanemuine.name = "Keller (1 photo, 1 note)";
        vanemuine.latitude = 58.3815583;
        vanemuine.longitude = 26.7197021;

        Location townHall = new Location();
        if (MapsFragment.bmp == null) {
            townHall.name = "Physicum (2 photos)";
        } else {
            townHall.name = "Physicum (3 photos)";
        }
        townHall.latitude = 58.3664035;
        townHall.longitude = 26.6885706;

        List = new ArrayList<Location>();
        List.add(townHall);
        List.add(vanemuine);
        List.add(jaaniKirik);
    }
}