public static void main(String[] args) {
    A machine = new A();
    machine.initialize();
    System.out.println(machine.op1(new BInteger(10), new BInteger(10)));
}