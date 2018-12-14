/*
 * OOP - WS1819 - SBL 1
 * Vorname Nachname (Matrikelnr.)
 * Vorname Nachname (Matrikelnr.)
 */
package adventure.game;

import adventure.location.Facility;
import adventure.location.FunRide;
import adventure.location.Location;

import java.util.Scanner;

public class Game {
    public static void main(String[] argv) {
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

            while (!isInputCorrect(player, enteredOrder)) { //checks if input is possible to fulfill and let new input if not


                System.out.print("> ");
                enteredOrder = input.nextLine();
            }
            if (isStayOrder(player)) {
                player.stay();
            }
            else{
                player.walk(enteredOrder);
            }

        }


        if (playerAtEndLocation(player, endLocation)) {
            System.out.printf("You are here now: %1$s &nCongratulations, you made it. You have collected %2$d fun points and have %3$2.2f \u20ac.",endLocation.getName(), player.getMoney(), player.getMoney());
        } else {
            System.out.printf("Game over. You collapse exhausted and the park inspector must carry you out of the park.%nYou lose all your fun points! You have %2.2f \u20ac.", player.getMoney());
        }


    }
    
    private static boolean isStayOrder(Player player) {

        return player.getCurrentLocation().getClass() == FunRide.class || player.getCurrentLocation().getClass() == Facility.class;

    }


    private static boolean isInputCorrect(Player player, String input) {
        if (player.getCurrentLocation().getNeighboringLocation(input) == null) {
            if (player.getCurrentLocation().getClass() == FunRide.class && input.equals("ride")) {
                return true;
            } else if (player.getCurrentLocation().getClass() == Facility.class && input.equals("rest")) {
                return true;
            } else return false;
        }
        return false;
    }

    private static boolean outOfEnergy(Player player) {
        return player.getEnergy() < 0;
    }

    //returns true if player location equals end end location
    private static boolean playerAtEndLocation(Player player, Location endLocation) {
        return player.getCurrentLocation().equals(endLocation);
    }


}
