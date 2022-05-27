package me.andreisuruceanu.locationsservice.dto.currency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdvancedCurrencyDescription extends ShortCurrencyDescription {
    private String currency;
    public AdvancedCurrencyDescription(Short id, String currencySymbol, String currency) {
        super(id, currencySymbol);
        this.currency = currency;
    }
}
