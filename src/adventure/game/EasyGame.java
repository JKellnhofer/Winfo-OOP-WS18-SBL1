/*
 * OOP - WS1819 - SBL 1
 * Vorname Nachname (Matrikelnr.)
 * Vorname Nachname (Matrikelnr.)
 */


package adventure.game;

import adventure.location.Location;

public class EasyGame {
    public static void main(String[] argv) {
        // Create Locations
        Location rollerCoaster = new Location("Roller Coaster");
        Location restroom = new Location("Restroom");
        Location bumperCar = new Location("Bumper Car");
        Location kiosk = new Location("Kiosk");
        Location wildWaterChannel = new Location("Wild-Water Channel");
        Location restaurant = new Location("Restaurant");
        Location carousel = new Location("Carousel");
        Location freefallTower = new Location("Freefall Tower");
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

        //initializing player on start location
        Player player = new Player(rollerCoaster);

        //Intro text
        System.out.println("You’re in a theme park, it’s getting dark." +
                "&nYou want to go to your car." +
                "&nOn the way there you want to have as much fun as possible" +
                "&nBut you have only limited energy and money left.");


        //GameLoop as long as Game is not over (enough energy and not at parking lot
        while (!isGameOver(player) && !playerAtEndLocation(player,parkingLot)) {
            System.out.println(player.toString());
        }
        if (isGameOver(player)) {

        }


    }

    private static boolean isGameOver(Player player) {
        return player.getEnergy() >= 0;
    }

    //returns true if player location equals end end location
    private static boolean  playerAtEndLocation(Player player, Location endLocation){
        return player.getCurrentLocation().equals(endLocation);
    }
}
