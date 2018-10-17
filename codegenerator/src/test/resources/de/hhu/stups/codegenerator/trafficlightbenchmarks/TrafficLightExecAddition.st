public static void main(String[] args) {
    TrafficLightExec traffic = new TrafficLightExec();
    traffic.initialize();
    traffic.simulate();;
    System.out.println(traffic.counter);
}