package me.andreisuruceanu.locationsservice.dto.city;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.andreisuruceanu.locationsservice.dto.country.ShortCountryDescription;
import me.andreisuruceanu.locationsservice.dto.state.ShortStateDescription;

@Getter
@Setter
@NoArgsConstructor
public class AdvancedCityDescription extends ShortCityDescription {
    protected ShortCountryDescription country;
    protected ShortStateDescription state;

    public AdvancedCityDescription(Integer id, String name,
                                   ShortCountryDescription country, ShortStateDescription state) {
        super(id, name);
        this.country = country;
        this.state = state;
    }

    public AdvancedCityDescription(Integer id, String name,
                                   Short countryId, String countryName, String countryIso2, String countryNativeName,
                                   Short stateId, String stateName) {
        super(id, name);
        this.country = new ShortCountryDescription(
                countryId,
                countryName,
                countryIso2,
                countryNativeName
        );
        this.state = new ShortStateDescription(stateId, stateName);
    }
}
