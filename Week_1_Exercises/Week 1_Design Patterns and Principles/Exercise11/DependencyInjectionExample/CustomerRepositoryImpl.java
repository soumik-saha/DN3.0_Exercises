public class CustomerRepositoryImpl implements CustomerRepository{
    @Override
    public String findCustomerById(String id) {
        return "Customer Name for ID " + id;
    }
}
