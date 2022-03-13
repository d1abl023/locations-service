package me.andreisuruceanu.locationsservice.dto.country;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.andreisuruceanu.locationsservice.dto.currency.ShortCurrencyDescription;

@Getter
@Setter
@NoArgsConstructor
public class AdvancedCountryDescription extends ShortCountryDescription {
    protected String iso3;
    protected String phonecode;
    protected String capital;
    protected ShortCurrencyDescription currency;

    public AdvancedCountryDescription(Short id, String name, String iso2, String nativeName, String iso3,
                                      String phonecode, String capital, ShortCurrencyDescription currency) {
        super(id, name, iso2, nativeName);
        this.iso3 = iso3;
        this.phonecode = phonecode;
        this.capital = capital;
        this.currency = currency;
    }

    public AdvancedCountryDescription(Short id, String name, String iso2, String nativeName, String iso3,
                                      String phonecode, String capital, Short currencyId, String currencySymbol) {
        super(id, name, iso2, nativeName);
        this.iso3 = iso3;
        this.phonecode = phonecode;
        this.capital = capital;
        this.currency = new ShortCurrencyDescription(currencyId, currencySymbol);
    }
}
