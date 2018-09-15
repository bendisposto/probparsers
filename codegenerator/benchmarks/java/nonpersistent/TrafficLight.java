import de.hhu.stups.btypes.BSet;
import de.hhu.stups.btypes.BObject;
import de.hhu.stups.btypes.BBoolean;

public class TrafficLight {


    public enum colors implements BObject {
        red, 
        redyellow, 
        yellow, 
        green;

        public BBoolean equal(colors o) {
            return new BBoolean(this == o);
        }

        public BBoolean unequal(colors o) {
            return new BBoolean(this != o);
        }
    }

    private BSet _colors = new BSet(colors.red, colors.redyellow, colors.yellow, colors.green);

    private colors tl_cars;
    private colors tl_peds;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        tl_cars = (colors) colors.red;
        tl_peds = (colors) colors.red;
        initialized = true;
    }

    public void cars_ry() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        if((tl_cars.equal(colors.red).and(tl_peds.equal(colors.red))).booleanValue()) {
            tl_cars = (colors) colors.redyellow;
        } else {
            throw new RuntimeException("Invocation of the operation is not possible");
        }
    }

    public void cars_y() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        if((tl_cars.equal(colors.green).and(tl_peds.equal(colors.red))).booleanValue()) {
            tl_cars = (colors) colors.yellow;
        } else {
            throw new RuntimeException("Invocation of the operation is not possible");
        }
    }

    public void cars_g() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        if((tl_cars.equal(colors.redyellow).and(tl_peds.equal(colors.red))).booleanValue()) {
            tl_cars = (colors) colors.green;
        } else {
            throw new RuntimeException("Invocation of the operation is not possible");
        }
    }

    public void cars_r() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        if((tl_cars.equal(colors.yellow).and(tl_peds.equal(colors.red))).booleanValue()) {
            tl_cars = (colors) colors.red;
        } else {
            throw new RuntimeException("Invocation of the operation is not possible");
        }
    }

    public void peds_r() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        if((tl_peds.equal(colors.green).and(tl_cars.equal(colors.red))).booleanValue()) {
            tl_peds = (colors) colors.red;
        } else {
            throw new RuntimeException("Invocation of the operation is not possible");
        }
    }

    public void peds_g() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        if((tl_peds.equal(colors.red).and(tl_cars.equal(colors.red))).booleanValue()) {
            tl_peds = (colors) colors.green;
        } else {
            throw new RuntimeException("Invocation of the operation is not possible");
        }
    }

}
