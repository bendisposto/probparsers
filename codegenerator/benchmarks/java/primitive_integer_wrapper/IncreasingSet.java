import de.hhu.stups.btypes.BSet;
import de.hhu.stups.btypes.BInteger;

public class IncreasingSet {




    private BInteger counter;
    private BSet set;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        counter = (BInteger) new BInteger(0);
        set = (BSet) new BSet(new BInteger(1), new BInteger(2), new BInteger(3));
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger(25000))).booleanValue()) {
            set = (BSet) set.union(new BSet(counter));
            counter = (BInteger) counter.plus(new BInteger(1));
        }
    }

    public static void main(String[] args) {
        IncreasingSet exec = new IncreasingSet();
        exec.initialize();
        long start = System.nanoTime();
        exec.simulate();
        long end = System.nanoTime();
        System.out.println(exec.getClass().toString() + " Execution: " + (end - start));
    }

}
