public static void main(String[] args) {
    Lift lift = new Lift();
    lift.initialize();
    lift.inc();
    lift.inc();
    lift.inc();
    System.out.println(lift.floor);
}