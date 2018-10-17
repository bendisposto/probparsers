public static void main(String[] args) {
    ManyLocalDeclarations locals = new ManyLocalDeclarations();
    locals.initialize();
    System.out.println(locals.Inc(new BInteger(10)));
}