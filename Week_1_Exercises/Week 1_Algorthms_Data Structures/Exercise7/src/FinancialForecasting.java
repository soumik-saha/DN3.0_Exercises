public class FinancialForecasting {
    public static double predictFutureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }
        return (1 + growthRate) * predictFutureValue(currentValue, growthRate, years - 1);
    }

    public static void main(String[] args) {
        double currentValue = 1000.0; // Initial value
        double growthRate = 0.05; // 5% growth rate
        int years = 10; // Forecast for 10 years

        double futureValue = predictFutureValue(currentValue, growthRate, years);
        System.out.println("The future value after " + years + " years is: Rs. " + futureValue);
    }
}
