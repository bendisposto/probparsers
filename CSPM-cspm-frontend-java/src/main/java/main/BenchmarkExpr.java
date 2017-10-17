package frege.main;

import frege.main.Main;
import frege.main.ExecCommand;
import java.util.LinkedList;
import java.util.stream.*;

public class BenchmarkExpr {

    /**
     * Measures the runtime of a --prologOut call.
     * @param args[0] number of repetitions
     * @param args[1] the file to be translated
     */
    public static void main(String[] args) {
        int repetitions = Integer.parseInt(args[0]);
        String filename = args[1];
        String[] cmdArgs = new String[3];
        LinkedList<Long> runtimes = new LinkedList<>();
        
        long loopStart = System.currentTimeMillis();
        for(int i = 0; i < repetitions; i++) {
            cmdArgs[0] = "translate";
            cmdArgs[1] = "--expressionToPrologTerm=N";
            cmdArgs[2] = filename;
            
            long start = System.currentTimeMillis();
            Main.main(cmdArgs);
            long end  = System.currentTimeMillis();
            
            Long runtime = end - start;
            System.out.println("run " + i + ": " + runtime);
            runtimes.add(runtime);
        }
        long loopEnd = System.currentTimeMillis();
        runtimes.forEach(s -> System.out.print(s + ", "));
        System.out.println();
        
        long runtimesSum = runtimes.stream().mapToLong(Long::longValue).sum();
        double runtimesAvg = (double)runtimesSum / repetitions;
        double runtimesAvgHalf = runtimes.subList(runtimes.size()/2, runtimes.size()).stream().mapToLong(Long::longValue).average().getAsDouble();
        
        System.out.println("total time: " + runtimesSum);
        System.out.println("average time: " + runtimesAvg);
        System.out.println("average time for second half: " + runtimesAvgHalf);
        System.out.println("benchmark wallclock time: " + (loopEnd - loopStart));
    }
    
}
