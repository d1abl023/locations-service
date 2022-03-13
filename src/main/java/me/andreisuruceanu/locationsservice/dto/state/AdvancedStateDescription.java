package me.andreisuruceanu.locationsservice.dto.state;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.andreisuruceanu.locationsservice.dto.country.ShortCountryDescription;
import me.andreisuruceanu.locationsservice.dto.currency.ShortCurrencyDescription;

@Getter
@Setter
@NoArgsConstructor
public class AdvancedStateDescription extends ShortStateDescription {
    protected ShortCountryDescription country;
    protected ShortCurrencyDescription currency;

    public AdvancedStateDescription(Short id, String name, Short countryId, String countryName, String countryIso2,
                                    String countryNativeName, Short currencyId, String currencySymbol) {
        super(id, name);
        this.country = new ShortCountryDescription(
                countryId,
                countryName,
                countryIso2,
                countryNativeName
        );
        this.currency = new ShortCurrencyDescription(currencyId, currencySymbol);
    }
}
