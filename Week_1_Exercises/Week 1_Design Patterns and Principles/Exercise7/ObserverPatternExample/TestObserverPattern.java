public class TestObserverPattern {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp1 = new MobileApp("App1");
        Observer webApp1 = new WebApp("Web1");

        stockMarket.registerObserver(mobileApp1);
        stockMarket.registerObserver(webApp1);

        stockMarket.setStockPrice(1000.00);
        stockMarket.setStockPrice(1050.50);

        stockMarket.deregisterObserver(mobileApp1);

        stockMarket.setStockPrice(1100.00);
    }
}
