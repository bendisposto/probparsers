import de.hhu.stups.btypes.BSet;
import de.hhu.stups.btypes.BInteger;

public class Sieve {




    private BSet numbers;
    private BInteger cur;
    private BInteger limit;

    private boolean initialized = false;

    public void initialize() {
        if(initialized) {
            throw new RuntimeException("Machine is already initialized");
        }
        numbers = (BSet) BSet.range(new BInteger("2"),new BInteger("10000"));
        cur = (BInteger) new BInteger("2");
        limit = (BInteger) new BInteger("10000");
        initialized = true;
    	//System.out.println(numbers.card());
    }

    public BInteger ComputeNumberOfPrimes() {
        if(!initialized) {
            throw new RuntimeException("Machine was not initialized");
        }
        BInteger res = null;
        while((cur.greater(new BInteger("1")).and(cur.multiply(cur).lessEqual(limit))).booleanValue()) {
            if((numbers.elementOf(cur)).booleanValue()) {
                BInteger n = null;
                BSet set = null;
                n = (BInteger) cur;
                set = (BSet) new BSet();
                while((n.lessEqual(limit.divide(cur))).booleanValue()) {
                    set = (BSet) set.union(new BSet(cur.multiply(n)));
                    n = (BInteger) n.plus(new BInteger("1"));
                }
                numbers = (BSet) numbers.complement(set);
            } 
            cur = (BInteger) cur.plus(new BInteger("1"));
        }
        res = (BInteger) numbers.card();
        return res;
    }

    public static void main(String[] args) {
        Sieve sieve = new Sieve();
        sieve.initialize();
        long start = System.nanoTime();
        System.out.println(sieve.ComputeNumberOfPrimes());
        long end = System.nanoTime();
        System.out.println("Execution: " + (end - start));
    }
}
