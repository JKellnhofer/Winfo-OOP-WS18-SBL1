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

    @Override
    // returns String with status
    public String toString() {

        return String.format("%1$s%nYou have %2$d energy and %3$2.2f \u20ac. You already earned %4$d fun points", currentLocation.toString(), getEnergy(), getMoney(), getFunPoints());
    }

    // player walks to new location with direction
    // sets new location with direction + player loses 10 energy points
    public void walk(String direction) {
        currentLocation = currentLocation.getNeighboringLocation(direction);
        energy -= 10;

    }

    //Player stays at location and uses FunRide/Facility


    public void stay() {
        if (isFunRide(currentLocation)) { //checks if location is FunRide/Facility

            double cost = ((FunRide) currentLocation).getCost();
            if (money - cost >= 0) { // checks if player has enough money
                money -= cost;
                funPoints += ((FunRide) currentLocation).getFunPoints();
                energy -= 5; //every funRide removes 5 energy Points
            }

        } else if (isFacility(currentLocation)) {
            double cost = ((Facility) currentLocation).getCost();
            if (money - cost >= 0) { // checks if player has enough money
                money -= cost;
                energy += ((Facility) currentLocation).getEnergyPoints();
            }
        }


    }

    //returns true if location's class is FunRide
    private boolean isFunRide(Location location) {
        return location.getClass() == FunRide.class;
    }

    //returns true if location's class is Facility
    private boolean isFacility(Location location) {
        return location.getClass() != FunRide.class;
    }

    //returns true if location's class is neither Facility nor FunRide
    private boolean isOnlyLocation(Location location) {
        return !isFunRide(location) && !isFacility(location);


    }

    //sets user to given Location and resets funPoints
    public void gameOver(Location location) {
        currentLocation = location;
        funPoints = 0;
    }


}

