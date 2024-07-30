public class BubbleSort {
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for(int i=0; i<n-1; i++) {
            for(int j=0; j<n-1-i; j++) {
                if (orders[j].getTotalPrice()>orders[j+1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Order[] orders = {
                new Order(1, "Soumik Saha", 2500.50),
                new Order(2, "Sougata Maity", 1500.25),
                new Order(3, "Tathagata Pal", 5000)
        };

        bubbleSort(orders);

        for(Order order:orders) {
            System.out.println(order.getCustomerName() + ": Rs. " + order.getTotalPrice());
        }
    }
}
