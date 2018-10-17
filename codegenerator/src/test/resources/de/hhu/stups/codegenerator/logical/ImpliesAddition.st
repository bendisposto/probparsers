public static void main(String[] args) {
    Implies logical = new Implies();
    logical.initialize();
    logical.simulate();
    System.out.println(logical.counter);
}