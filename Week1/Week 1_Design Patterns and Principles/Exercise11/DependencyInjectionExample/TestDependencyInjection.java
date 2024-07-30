public class TestDependencyInjection {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        String customerName = service.getCustomerName("291");
        System.out.println(customerName);
    }
}
