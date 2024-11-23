public class Vehicle {

    private int altitude;
    private int previousAltitude;

    public Vehicle(int InitialAltitude) {

        this.altitude = InitialAltitude;
        this.previousAltitude = InitialAltitude;
        // initialize the altitude AND previous altitude to initialAltitude
    }

    int Gravity = 100;
    /* The rate in which the spaceship descents in free fall (in ten seconds) */

    // Various end-of-game messages and status result codes.
    String dead = "\nCRASH!!\n\tThere were no survivors.\n\n";
    public static final int DEAD = -3;
    String crashed = "\nThe Starship crashed. Good luck getting back home. Elon is pissed.\n\n";
    public static final int CRASHED = -2;
    String emptyfuel = "\nThere is no fuel left. You're floating around like Major Tom.\n\n";
    public static final int EMPTYFUEL = -1;
    String success = "\nYou made it! Good job!\n\n";
    public static final int SUCCESS = 0;
    public static final int FLYING = 1;

    // this is initial vehicle setup
    int Altitude= 8000;
    int PrevAltitude= 8000;

    int Velocity= 1000;
    int Fuel = 12000;
    int Burn = 0;
    int Flying = FLYING;

    public Vehicle() {}

    public String checkFinalStatus() {
        String s = "";
        if (this.Altitude <= 0) {
            if (this.Velocity > 10) {
                s = dead;
                Flying = DEAD;
            }
            if (this.Velocity < 10 && this.Velocity > 3) {
                s = crashed;
                Flying = CRASHED;
            }
            if (this.Velocity < 3) {
                s = success;
                Flying = SUCCESS;
            }
        } else {
            if (this.Altitude > 0) {
                s = emptyfuel;
                Flying = EMPTYFUEL;
            } }
        return s;
    }

    public int computeDeltaV() {
        return Velocity + Gravity - Burn;
       // return 0;
    }

    public void adjustForBurn(int burnAmount) {

        // set burn to burnamount requested
            this.Burn = burnAmount;
        // save previousAltitude with current Altitude
        this.PrevAltitude = this.Altitude;
        // set new velocity to result of computeDeltaV function.
        this.Velocity = computeDeltaV();
        // subtract speed from Altitude
        this.Altitude -= this.Velocity;

    // subtract burn amount fuel used from tank
            this.Fuel -= burnAmount;
        }

    public boolean stillFlying() {

        return this.Altitude > 0;
    }
    public boolean outOfFuel() {

        return this.Fuel <= 0;
    }

    public DescentEvent getStatus(int tick) {

        return new DescentEvent(1,2,100,300,1);
    }

}
