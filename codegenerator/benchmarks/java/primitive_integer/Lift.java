import de.hhu.stups.btypes.BInteger;

public class Lift {




    private BInteger floor;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        floor = (BInteger) new BInteger(0);
        initialized = true;
    }

    public void inc() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        floor = (BInteger) floor.plus(new BInteger(1));
    }

    public void dec() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        floor = (BInteger) floor.minus(new BInteger(1));
    }

}
