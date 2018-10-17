public static void main(String[] args) {
    ACounter counter = new ACounter();
    counter.initialize();
    counter.inc();
    counter.inc();
    System.out.println(counter.res());
}