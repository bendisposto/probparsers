import de.hhu.stups.btypes.BInteger;
import de.hhu.stups.btypes.BBoolean;

public class Or {




    private BInteger counter;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        counter = (BInteger) new BInteger("0");
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger("5000000")).or(new BInteger("1").equal(new BInteger("2")))).booleanValue()) {
            counter = (BInteger) counter.plus(new BInteger("1"));
        }
    }

    public static void main(String[] args) {
        Or exec = new Or();
        exec.initialize();
        long start = System.nanoTime();
        exec.simulate();
        long end = System.nanoTime();
        System.out.println("Execution: " + (end - start));
    }

}
