public class TestStrategyPattern {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "Soumik Saha"));
        context.executePayment(1000);

        context.setPaymentStrategy(new PayPalPayment("soumik.saha@example.com"));
        context.executePayment(2000);
    }
}
