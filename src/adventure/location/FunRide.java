package adventure.location;

public class FunRide extends Location{

    private final double COST;
    private final int FUN_POINTS;

    public FunRide (String name, double cost, int funPoints){
        super(name);
        this.COST = cost;
        this.FUN_POINTS = funPoints;

    }

    public double getCost() {
        return COST;
    }

    public int getFunPoints() {
        return FUN_POINTS;
    }

    @Override
    public String toString(){
        return String.format (super.toString() + "or ride%nA ride cost %2.2f \u20ac and you will receive" +
                " %d fun points", getCost(), getFunPoints());

    }
}
