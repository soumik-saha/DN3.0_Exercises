public class LinearSearch {
    public static Product linearSearch(Product[] products, int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null; // Product not found
    }

    public static void main(String[] args) {
        Product[] products = {
                new Product(1, "Laptop", "Electronics"),
                new Product(2, "Mouse", "Accessories"),
                new Product(3, "Keyboard", "Accessories")
        };

        Product result = linearSearch(products, 2);
        if (result != null) {
            System.out.println("Product found: " + result.getProductName());
        } else {
            System.out.println("Product not found.");
        }
    }
}
