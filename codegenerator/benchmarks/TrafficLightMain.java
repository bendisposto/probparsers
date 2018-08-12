import de.hhu.stups.btypes.BInteger;

public class TrafficLightMain {

	public static void main(String args[]) {

		long start, end, diff;
		TrafficLight machine = null;

		diff = 0;
		start = System.nanoTime();
		machine = new TrafficLight();
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
			machine.cars_ry();
			end = System.nanoTime();
			System.out.println("cars_ry," + (end-start));

			start = System.nanoTime();
			machine.cars_g();
			end = System.nanoTime();
			System.out.println("cars_g," + (end-start));

			start = System.nanoTime();
			machine.cars_y();
			end = System.nanoTime();
			System.out.println("cars_y," + (end-start));

			start = System.nanoTime();
			machine.cars_r();
			end = System.nanoTime();
			System.out.println("cars_r," + (end-start));

			start = System.nanoTime();
			machine.peds_g();
			end = System.nanoTime();
			System.out.println("peds_g," + (end-start));

			start = System.nanoTime();
			machine.peds_r();
			end = System.nanoTime();
			System.out.println("peds_r," + (end-start));
		}

	}


}