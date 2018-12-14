/*
 * OOP - WS1819 - SBL 1
 * Vorname Nachname (Matrikelnr.)
 * Vorname Nachname (Matrikelnr.)
 */


package adventure.game;

import adventure.location.Location;

import java.util.Scanner;

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

        // important locations
        Location startLocation = rollerCoaster;
        Location endLocation = parkingLot;


        //initializing player on start location
        Player player = new Player(startLocation);

        //initializing new Scanner for User Input
        Scanner input = new Scanner(System.in);
        String enteredOrder;

        //Intro text
        System.out.printf("You’re in a theme park, it’s getting dark." +
                "%nYou want to go to your car." +
                "%nOn the way there you want to have as much fun as possible" +
                "%nBut you have only limited energy and money left.%n%n");


        //GameLoop as long as Game is not over (enough energy and not at end location)
        while (!outOfEnergy(player) && !playerAtEndLocation(player, endLocation)) {
            System.out.println(player.toString());
            System.out.print("> ");
            enteredOrder = input.nextLine();
            while ((player.getCurrentLocation().getNeighboringLocation(enteredOrder) == null)) {

                System.out.print("> ");
                enteredOrder = input.nextLine();
            }
            player.walk(enteredOrder);

        }


        if (playerAtEndLocation(player, endLocation)) {
            System.out.print("You are here now: " + endLocation.getName() +
                    "&nCongratulations, you made it. You have collected " +
                    player.getFunPoints() + " fun points and have " +
                    player.getMoney() + " \0x20ac.");
        } else {
            System.out.println("Game over. You collapse exhausted and the park inspector must carry you out of the park. " +
                    "%nYou lose all your fun points! You have " + player.getMoney() + " \\0x20ac.");
        }


    }

    private static boolean outOfEnergy(Player player) {
        return player.getEnergy() < 0;
    }

    //returns true if player location equals end end location
    private static boolean playerAtEndLocation(Player player, Location endLocation) {
        return player.getCurrentLocation().equals(endLocation);
    }
}
