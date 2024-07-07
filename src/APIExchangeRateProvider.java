import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

public class APIExchangeRateProvider {

    private final String apiKey;

    public APIExchangeRateProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    public double getExchangeRate(Double amount, String fromCurrency, String toCurrency) throws IOException, InterruptedException {
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, fromCurrency);
        //System.out.println(url); // Debugging check

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (!response.headers().firstValue("Content-Type").isPresent() ||
                !response.headers().firstValue("Content-Type").get().contains("application/json")) {
            throw new IOException("Formato inesperado: Se esperaba un json.");
        }

        String responseBody = response.body();

        // Parse JSON response using Gson
        Gson gson = new Gson();
        ExchangeRateResponse responseObject = gson.fromJson(responseBody, ExchangeRateResponse.class);

        // Get source currency from base_code field (assuming response has base_code)
        String sourceCurrency = responseObject.getBaseCode();
        //System.out.println(sourceCurrency);
        // Find exchange rate for the target currency using Map operations
        Optional<Double> exchangeRate = responseObject.getConversionRates().entrySet().stream()
                .filter(entry -> entry.getKey().equals(toCurrency))
                .map(Map.Entry::getValue)
                .findFirst();

        if (!exchangeRate.isPresent()) {
            throw new RuntimeException("Error: No se puede encontrar la Tasa de " + toCurrency);
        }

        // Access the exchange rate from the Optional object
        double targetRate = exchangeRate.get();
        return targetRate;
    }
}