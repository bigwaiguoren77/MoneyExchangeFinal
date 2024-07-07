import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String apiKey = "640b525439fad16d27d14b5d";
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Scanner inputOption = new Scanner(System.in);
        //inputOption.useDelimiter(System.lineSeparator());
        APIExchangeRateProvider provider = new APIExchangeRateProvider(apiKey);

        while (true) {
            System.out.println("\nCurrency Converter Menu:");
            System.out.println("1. Cambiar Moneda");
            System.out.println("2. Exit");
            System.out.print("Selecciona la opción: ");

            int choice = inputOption.nextInt();
            inputOption.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    String amount = getUserInputAmount(scanner); // Get amount from user.
                    Double amountValue = Double.parseDouble(amount);
                    String fromCurrency = getUserInputFromCurrency(scanner); // Get source currency from user.
                    String targetCurrency = getUserInputTargetCurrency(scanner); // Get target currency from user
                    System.out.println("Cotizando " + amountValue+" "+fromCurrency+" en "+targetCurrency);
                    System.out.println(".........");
                    convertCurrency(amountValue, provider, fromCurrency, targetCurrency);
                    break;
                case 2:
                    System.out.println("Saliendo del Programa...");
                    System.exit(0);
                default:
                    System.out.println("Opción Incorrecta. Intenta de Nuevo.");
            }
        }
    }
    private static String getUserInputAmount(Scanner scanner) {
        System.out.print("Cuanto Deseas Cambiar el día de hoy : ");
        return scanner.nextLine();
    }
    private static String getUserInputFromCurrency(Scanner scanner) {
        System.out.print("Ingresar Moneda Origen (e.g., USD, EUR): ");
        return scanner.nextLine().toUpperCase();
    }

    private static String getUserInputTargetCurrency(Scanner scanner) {
        System.out.print("Ingresar Moneda Destino (e.g., USD, EUR): ");
        return scanner.nextLine().toUpperCase();
    }
    private static void convertCurrency(Double amountValue, APIExchangeRateProvider provider, String fromCurrency, String targetCurrency)  throws IOException, InterruptedException {
        double convertedAmount = provider.getExchangeRate(amountValue, fromCurrency,targetCurrency); // Pass only target currency
        System.out.println("*****************************");
        System.out.println("Por esa cantidad recibirías : " + convertedAmount*amountValue + " " + targetCurrency);
        System.out.println("*****************************");
    }
}