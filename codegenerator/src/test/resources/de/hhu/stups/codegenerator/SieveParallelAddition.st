public static void main(String[] args) {
    SieveParallel sieve = new SieveParallel();
    sieve.initialize();
    System.out.println(sieve.ComputeNumberOfPrimes());
}