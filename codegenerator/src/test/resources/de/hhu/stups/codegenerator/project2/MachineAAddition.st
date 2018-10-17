public static void main(String[] args) {
    MachineA machine = new MachineA();
    machine.initialize();
    machine.IncX1();
    machine.IncX2();
    System.out.println(machine.MachineB.x);
}