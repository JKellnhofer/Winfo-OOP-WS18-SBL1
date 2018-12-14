/*
 * OOP - WS1819 - SBL 1
 * Vorname Nachname (Matrikelnr.)
 * Vorname Nachname (Matrikelnr.)
 */
package adventure.game;

import adventure.location.Facility;
import adventure.location.FunRide;
import adventure.location.Location;

public class Game {
    public static void main(String[] argv){
        // Create Locations
        FunRide rollerCoaster = new FunRide("Roller Coaster", 2.5, 5);
        Facility restroom = new Facility("Restroom", 0.5, 1);
        FunRide bumperCar = new FunRide("Bumper Car", 1, 2);
        Facility kiosk = new Facility("Kiosk", 2, 5);
        FunRide wildWaterChannel = new FunRide("Wild-Water Channel", 2.5, 5);
        Facility restaurant = new Facility("Restaurant", 10, 20);
        FunRide carousel = new FunRide("Carousel", 1, 2);
        FunRide freefallTower = new FunRide("Freefall Tower", 1, 2);
        Location entrance = new Location("Entrance");
        Location parkingLot = new Location("Parking Lot");

        // Create Paths
        rollerCoaster.createPath("up", restroom);
        restroom.createPath("left", bumperCar);
        bumperCar.createPath("down", kiosk);
        bumperCar.createPath("left", wildWaterChannel);
        wildWaterChannel.createPath("down", restaurant);
        restaurant.createPath("down", carousel);
        kiosk.createPath("left", carousel);
        restaurant.createPath("left", freefallTower);
        freefallTower.createPath("down", entrance);
        carousel.createPath("left", entrance);
        entrance.createPath("down", parkingLot);

        /*
         * TODO
         */


    }

}
