import de.hhu.stups.btypes.BSet;
import de.hhu.stups.btypes.BInteger;
import de.hhu.stups.btypes.BBoolean;

public class SetElementOfBig {




    private BInteger counter;
    private BSet set;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        counter = (BInteger) new BInteger("0");
        set = (BSet) BSet.range(new BInteger("1"),new BInteger("25000")).complement(new BSet(new BInteger("24999")));
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger("10000")).and(set.elementOf(new BInteger("25000")))).booleanValue()) {
            counter = (BInteger) counter.plus(new BInteger("1"));
        }
    }

    public static void main(String[] args) {
        SetElementOfBig exec = new SetElementOfBig();
        exec.initialize();
        long start = System.nanoTime();
        exec.simulate();
        long end = System.nanoTime();
        System.out.println("Execution: " + (end - start));
    }

}
