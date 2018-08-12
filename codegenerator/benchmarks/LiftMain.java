import de.hhu.stups.btypes.BInteger;

public class LiftMain {

	public static void main(String args[]) {

		long start, end, diff;
		Lift machine = null;

		diff = 0;
		start = System.nanoTime();
		machine = new Lift();
		end = System.nanoTime();
		diff += end - start;
		System.out.println("Loading machine: " + diff);

		diff = 0;
		start = System.nanoTime();
		machine.initialize();
		end = System.nanoTime();
		diff += end - start;
		System.out.println("Initialize machine: " + diff);

		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 99; j++) {
				start = System.nanoTime();
				machine.inc();
				end = System.nanoTime();
				System.out.println("inc," + (end-start));
			}
			for(int j = 0; j < 99; j++) {
				start = System.nanoTime();
				machine.dec();
				end = System.nanoTime();
				System.out.println("dec," + (end-start));
			}
		}


	}


}
