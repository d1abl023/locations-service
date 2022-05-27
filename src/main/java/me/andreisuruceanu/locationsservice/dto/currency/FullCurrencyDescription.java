package me.andreisuruceanu.locationsservice.dto.currency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FullCurrencyDescription extends AdvancedCurrencyDescription {
    private String currencyName;

    public FullCurrencyDescription(Short id, String currencySymbol,
                                   String currency, String currencyName) {
        super(id, currencySymbol, currency);
        this.currencyName = currencyName;
    }


}
