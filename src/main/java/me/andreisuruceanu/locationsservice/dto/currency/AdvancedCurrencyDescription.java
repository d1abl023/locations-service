package me.andreisuruceanu.locationsservice.dto.currency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdvancedCurrencyDescription extends ShortCurrencyDescription {
    private String currency;
    private String currencyName;

    public AdvancedCurrencyDescription(Short id, String currencySymbol, String currency, String currencyName) {
        super(id, currencySymbol);
        this.currency = currency;
        this.currencyName = currencyName;
    }
}
