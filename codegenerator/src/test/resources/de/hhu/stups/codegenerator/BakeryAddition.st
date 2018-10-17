public static void main(String[] args) {
    Bakery bakery = new Bakery();
    bakery.initialize();
    bakery.enter1();
    bakery.enter2();
    bakery.leave1();
    bakery.leave2();
    bakery.try1();
    bakery.try2();
    System.out.println(bakery.aa);
}