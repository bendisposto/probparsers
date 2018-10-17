public static void main(String[] args) {
    Counter counter = new Counter();
    counter.initialize();
    counter.Reset();
    counter.Inc();
    counter.Even();
    counter.Odd();
    System.out.println(counter.count);
}