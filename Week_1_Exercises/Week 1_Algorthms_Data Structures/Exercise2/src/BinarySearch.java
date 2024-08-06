import java.util.Arrays;

public class BinarySearch {
    public static Product binarySearch(Product[] products, int productId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].getProductId() == productId) {
                return products[mid];
            } else if (products[mid].getProductId() < productId) {
                left = mid + 1;
            } else {
                right = mid - 1;
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

        // Sort products by productId before binary search
        Arrays.sort(products, (p1, p2) -> Integer.compare(p1.getProductId(), p2.getProductId()));

        Product result = binarySearch(products, 3);
        if (result != null) {
            System.out.println("Product found: " + result.getProductName());
        } else {
            System.out.println("Product not found.");
        }
    }
}
