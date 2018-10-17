public static void main(String[] args) {
    LiftExec lift = new LiftExec();
    lift.initialize();
    lift.simulate();
    System.out.println(lift.counter);
}