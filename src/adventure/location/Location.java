package adventure.location;

import java.util.Locale;

public class Location {

    private String name;                    //declaring variables
    private Location leftLocation;
    private Location rightLocation;
    private Location upLocation;
    private Location downLocation;

    public Location(String name) {      //constructor initialize variables
        this.name = name;
        leftLocation = null;
        rightLocation = null;
        upLocation = null;
        downLocation = null;
    }

    public String getName() {       //returning the name, necessary to get access from outside
        return name;
    }

    public Location getNeighboringLocation(String direction) {
        switch (direction) {                         //getting a direction, and returning the
            case "left":
                return leftLocation;       //location in that direction
            case "right":
                return rightLocation;
            case "up":
                return upLocation;
            case "down":
                return downLocation;
        }
        return null;
    }

    @Override                   //Overriding toString method in Object class
    public String toString() {
        String s = name + ". You can go: ";
        if (leftLocation != null) {
            s += "left ";
        }
        if (rightLocation != null) {
            s += "right ";
        }
        if (upLocation != null) {
            s += "up ";
        }
        if (downLocation != null) {
            s += "down ";
        }
        return s;

    }

    @Override                   //Overriding equals method in Object class
    public boolean equals(Object obj) {

        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (!obj.getClass().equals(this.getClass()))
            return false;

        Location location = (Location) obj;
        return (this.name.equals(location.getName()));

    }

    public void createPath(String direction, Location location) {
        Location thisLocation = new Location(this.name);
        Location connectedLocation = new Location(location.name);
        switch (direction) {
            case "up":
                this.upLocation = connectedLocation;
                location.downLocation = thisLocation;
                break;
            case "down":
                this.downLocation = connectedLocation;
                location.upLocation = thisLocation;
                break;

            case "left":
                this.leftLocation = connectedLocation;
                location.rightLocation = thisLocation;
                break;

            case "right":
                this.rightLocation = connectedLocation;
                location.leftLocation = thisLocation;
                break;

        }


    }
}