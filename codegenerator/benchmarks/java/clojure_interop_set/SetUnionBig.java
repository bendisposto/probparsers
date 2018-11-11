import de.hhu.stups.btypes.BSet;
import de.hhu.stups.btypes.BInteger;
import de.hhu.stups.btypes.BBoolean;

public class SetUnionBig {




    private BInteger counter;
    private BSet set;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        counter = (BInteger) new BInteger("25001");
        set = (BSet) BSet.range(new BInteger("1"),new BInteger("25000")).complement(new BSet(new BInteger("24999")));
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger("35000"))).booleanValue()) {
            set = (BSet) set.union(new BSet(counter));
            counter = (BInteger) counter.plus(new BInteger("1"));
        }
    }

    public static void main(String[] args) {
        SetUnionBig exec = new SetUnionBig();
        exec.initialize();
        long start = System.nanoTime();
        exec.simulate();
        long end = System.nanoTime();
        System.out.println(exec.getClass().toString() + " Execution: " + (end - start));
    }

}
