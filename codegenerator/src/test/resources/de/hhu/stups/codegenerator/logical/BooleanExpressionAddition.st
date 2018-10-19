public static void main(String[] args) {
    BooleanExpression logical = new BooleanExpression();
    logical.initialize();
    logical.simulate();
    System.out.println(logical.expr);
}