package adventure.game;

import java.util.Random;

import adventure.location.*;


public class Player {

    public Random randomGenerator = new Random(10);
    //Instance variables
    private Location currentLocation;
    private double money;
    private int energy;
    private int funPoints;

    //Constructor sets startLocation and initializes instance variables
    public Player(Location startLocation) {
        currentLocation = startLocation;
        money = (randomGenerator.nextInt(5001)) / 100; //sets a random value for money between 00.00(incl.) and 50.00(incl.) 5001 -> upper Bound is exclusive
        energy = (randomGenerator.nextInt(141)) + 60;  ////sets a random value for energy between 60(inc) and 200(incl.) 141 -> upper Bound is exclusive
        funPoints = 0;
    }

    //Getter for money, energy and funpoints
    public double getMoney() {
        return money;
    }

    public int getEnergy() {
        return energy;
    }

    public int getFunPoints() {
        return funPoints;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    // returns String with status
    public String toString() {
        return String.format("%2$s%nYou have %1$d energy and &3$f \\0x20ac. You already earned %4$d fun points", getEnergy(), currentLocation.toString(), getMoney(), getFunPoints());
    }

    // player walks to new location with direction
    // sets new location with direction + player loses 10 energy points
    public void walk(String direction) {
        currentLocation = currentLocation.getNeighboringLoccation(direction);
        energy -= 10;

    }

    //Player stays at location and uses FunRide/Facility


    public void stay() {
        if (currentLocation.getClass() == FunRide.class) { //checks if location is FunRide/Facility

            double cost = ((FunRide) currentLocation).getCost();
            if (money - cost >= 0) { // checks if player has enough money
                money -= cost;
                funPoints += ((FunRide) currentLocation).getFunPoints();
                energy -= 5; //every funRide removes 5 energy Points
            }

        } else if (currentLocation.getClass() == Facility.class) {
            double cost = ((Facility) currentLocation).getCost();
            if (money - cost >= 0) { // checks if player has enough money
                money -= cost;
                energy += ((Facility) currentLocation).getEnergyPoints();
            }
        }


    }

    //sets user to given Location and resets funPoints
    public void gameOver(Location location) {
        currentLocation = location;
        funPoints = 0;
    }


}
