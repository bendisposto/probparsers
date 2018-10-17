public static void main(String[] args) {
    TrafficLight traffic = new TrafficLight();
    traffic.initialize();
    traffic.cars_ry();
    traffic.cars_g();
    traffic.cars_y();
    traffic.cars_r();
    traffic.peds_g();
    System.out.println(traffic.tl_cars);
    System.out.println(traffic.tl_peds);
    traffic.peds_r();
}