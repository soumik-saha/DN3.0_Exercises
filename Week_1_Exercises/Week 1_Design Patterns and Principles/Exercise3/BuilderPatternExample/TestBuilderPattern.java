public class TestBuilderPattern {
    public static void main(String[] args) {
        Computer comp1 = new Computer.Builder("AMD Ryzen 5", "12GB").setStorage("512GB SSD").build();
        Computer comp2 = new Computer.Builder("Intel i3", "4GB").setStorage("1TB HDD").build();

        System.out.println(comp1);
        System.out.println(comp2);
    }
}
