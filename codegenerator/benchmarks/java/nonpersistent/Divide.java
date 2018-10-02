import de.hhu.stups.btypes.BInteger;

public class Divide {




    private BInteger counter;
    private BInteger value;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        counter = (BInteger) new BInteger("0");
        value = (BInteger) new BInteger("0");
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger("5000000"))).booleanValue()) {
            counter = (BInteger) counter.plus(new BInteger("1"));
            value = (BInteger) value.divide(new BInteger("1"));
        }
    }

    public static void main(String[] args) {
        Divide exec = new Divide();
        exec.initialize();
        long start = System.nanoTime();
        exec.simulate();
        long end = System.nanoTime();
        System.out.println("Execution: " + (end - start));
    }

}
