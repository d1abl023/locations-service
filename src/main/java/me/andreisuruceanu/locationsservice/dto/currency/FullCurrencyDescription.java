package me.andreisuruceanu.locationsservice.dto.currency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FullCurrencyDescription extends AdvancedCurrencyDescription {
    private Short countryId;

    public FullCurrencyDescription(Short id, String currencySymbol,
                                   String currency, String currencyName, Short countryId) {
        super(id, currencySymbol, currency, currencyName);
        this.countryId = countryId;
    }


}
