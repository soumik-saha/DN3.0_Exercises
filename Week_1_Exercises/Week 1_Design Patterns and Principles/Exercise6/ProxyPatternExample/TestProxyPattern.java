public class TestProxyPattern {
    public static void main(String[] args) {
        Image image = new ProxyImage("flights.jpg");

        image.display();
        image.display();
    }
}
