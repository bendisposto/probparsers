import de.hhu.stups.btypes.BSet;
import de.hhu.stups.btypes.BInteger;
import de.hhu.stups.btypes.BBoolean;

public class SetIntersectionBig2 {




    private BInteger counter;
    private BSet set1;
    private BSet set2;
    private BSet set3;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        counter = (BInteger) new BInteger(0);
        set1 = (BSet) BSet.range(new BInteger(1),new BInteger(35000)).complement(new BSet(new BInteger(24999)));
        set2 = (BSet) BSet.range(new BInteger(1),new BInteger(2000)).union(new BSet(new BInteger(1).negative()));
        set3 = (BSet) BSet.range(new BInteger(1),new BInteger(45000));
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger(10000))).booleanValue()) {
            set3 = (BSet) set1.intersect(set2);
            set3 = (BSet) set3.intersect(set1);
            counter = (BInteger) counter.plus(new BInteger(1));
        }
    }

    public static void main(String[] args) {
        SetIntersectionBig2 exec = new SetIntersectionBig2();
        exec.initialize();
        long start = System.nanoTime();
        exec.simulate();
        long end = System.nanoTime();
        System.out.println(exec.getClass().toString() + " Execution: " + (end - start));
    }


}
