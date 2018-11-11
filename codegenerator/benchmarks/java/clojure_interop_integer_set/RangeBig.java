import de.hhu.stups.btypes.BSet;
import de.hhu.stups.btypes.BInteger;
import de.hhu.stups.btypes.BBoolean;

public class RangeBig {




    private BInteger counter;
    private BSet set;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        counter = (BInteger) new BInteger("0");
        set = (BSet) new BSet();
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger("1000"))).booleanValue()) {
            set = (BSet) BSet.range(new BInteger("1"),new BInteger("25000"));
            counter = (BInteger) counter.plus(new BInteger("1"));
        }
    }

    public static void main(String[] args) {
        RangeBig exec = new RangeBig();
        exec.initialize();
        long start = System.nanoTime();
        exec.simulate();
        long end = System.nanoTime();
        System.out.println(exec.getClass().toString() + " Execution: " + (end - start));
    }

}
