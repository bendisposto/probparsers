import de.hhu.stups.btypes.BInteger;

public class LiftExec {

    private Lift Lift = new Lift();



    private BInteger counter;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        Lift.initialize();
        counter = (BInteger) new BInteger(0);
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger(3000))).booleanValue()) {
            BInteger i = null;
            i = (BInteger) new BInteger(0);
            while((i.less(new BInteger(100))).booleanValue()) {
                this.Lift.inc();
                i = (BInteger) i.plus(new BInteger(1));
            }
            BInteger _i = null;
            _i = (BInteger) new BInteger(0);
            while((_i.less(new BInteger(100))).booleanValue()) {
                this.Lift.dec();
                _i = (BInteger) _i.plus(new BInteger(1));
            }
            counter = (BInteger) counter.plus(new BInteger(1));
        }
    }

    public static void main(String[] args) {
        LiftExec exec = new LiftExec();
        exec.initialize();
        long start = System.nanoTime();
        exec.simulate();
        long end = System.nanoTime();
        System.out.println("Execution: " + (end - start));
    }

}
