import de.hhu.stups.btypes.BInteger;

public class TrafficLightExec {

    private TrafficLight TrafficLight = new TrafficLight();



    private BInteger counter;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        TrafficLight.initialize();
        counter = (BInteger) new BInteger("0");
        initialized = true;
    }

    public void simulate() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        while((counter.less(new BInteger("500000"))).booleanValue()) {
            this.TrafficLight.cars_ry();
            this.TrafficLight.cars_g();
            this.TrafficLight.cars_y();
            this.TrafficLight.cars_r();
            this.TrafficLight.peds_g();
            this.TrafficLight.peds_r();
            counter = (BInteger) counter.plus(new BInteger("1"));
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        TrafficLightExec exec = new TrafficLightExec();
        long end = System.nanoTime();
        System.out.println("Loading: " + (end - start));
        start = System.nanoTime();
        exec.initialize();
        end = System.nanoTime();
        System.out.println("Initialisation: " + (end - start));
        start = System.nanoTime();
        exec.simulate();
        end = System.nanoTime();
        System.out.println("Execution: " + (end - start));
    }

}
