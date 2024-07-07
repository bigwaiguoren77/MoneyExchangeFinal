import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ExchangeRateResponse {
    @SerializedName("base_currency")  // Custom name for baseCode
    private String baseCode;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public String getBaseCode() {
        //System.out.println("en el method "+baseCode); Debugging check
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }


}