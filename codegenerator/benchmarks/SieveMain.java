import de.hhu.stups.btypes.BInteger;

public class SieveMain {

	public static void main(String args[]) {

		long start, end, diff;
		Sieve machine = null;

		diff = 0;
		start = System.nanoTime();
		machine = new Sieve();
		end = System.nanoTime();
		diff += end - start;

		System.out.println("Loading machine: " + diff);

		diff = 0;
		start = System.nanoTime();
		machine.initialize();
		end = System.nanoTime();
		diff += end - start;

		System.out.println("Initialize machine: " + diff);

		for(int i = 0; i < 10000; i++) {
			start = System.nanoTime();
			BInteger result = machine.ComputeNumberOfPrimes();
			end = System.nanoTime();
			System.out.println("ComputeNumberOfPrimes," + (end-start));
		}

	}


}
