package adventure.location;

public class Facility extends Location{

    private final double COST;
    private final int ENERGY_POINTS;

    public Facility (String name, double cost, int energyPoints){
        super(name);
        this.COST = cost;
        this.ENERGY_POINTS = energyPoints;
    }

    public double getCost() {
        return COST;
    }

    public int getEnergyPoints() {
        return ENERGY_POINTS;
    }

    @Override
    public String toString(){
        return String.format (" or rest%nA rest cost %2.2f \u20ac and you will receive" +
                " %d energy points", getCost(), getEnergyPoints());
    }
}
