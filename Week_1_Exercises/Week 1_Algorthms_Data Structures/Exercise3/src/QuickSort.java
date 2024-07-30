public class QuickSort {
    public static void quickSort(Order[] orders, int low, int high) {
        if(low<high) {
            int pi = partition(orders, low, high);

            quickSort(orders, low, pi-1);
            quickSort(orders, pi, high);
        }
    }

    public static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low-1;

        for(int j=low; j<high; j++) {
            if(orders[j].getTotalPrice() < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i+1];
        orders[i+1] = orders[high];
        orders[high] = temp;

        return i+1;
    }

    public static void main(String[] args) {
        Order[] orders = {
                new Order(1, "Soumik Saha", 2500.50),
                new Order(2, "Sougata Maity", 1500.25),
                new Order(3, "Tathagata Pal", 5000)
        };

        quickSort(orders, 0, orders.length-1);

        for(Order order:orders) {
            System.out.println(order.getCustomerName() + ": Rs. " + order.getTotalPrice());
        }
    }
}
