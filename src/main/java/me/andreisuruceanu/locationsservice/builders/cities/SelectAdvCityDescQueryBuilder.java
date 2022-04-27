package me.andreisuruceanu.locationsservice.builders.cities;

import me.andreisuruceanu.locationsservice.dao.*;
import me.andreisuruceanu.locationsservice.dto.city.AdvancedCityDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCityQueryBuilder;
import org.hibernate.Session;

import javax.persistence.criteria.Join;

public class SelectAdvCityDescQueryBuilder extends SelectShortCityDescQueryBuilder {


    public SelectAdvCityDescQueryBuilder(Session session){
        super();
        this.builder = session.getCriteriaBuilder();
        this.query = builder.createQuery(AdvancedCityDescription.class);
        this.citiesTable = query.from(CitiesTable.class);
        this.countriesTable = this.citiesTable.join(CitiesTable_.COUNTRIES_TABLE);
        this.statesTable = this.citiesTable.join(CitiesTable_.STATES_TABLE);

    }

    @Override
    public ISelectCityQueryBuilder multiselect() {
        this.query.multiselect(
                citiesTable.get(CitiesTable_.ID),
                citiesTable.get(CitiesTable_.NAME),
                countriesTable.get(CountriesTable_.ID),
                countriesTable.get(CountriesTable_.NAME),
                countriesTable.get(CountriesTable_.ISO2),
                countriesTable.get(CountriesTable_.NATIVE_NAME),
                statesTable.get(StatesTable_.ID),
                statesTable.get(StatesTable_.NAME)
        );
        return this;
    }

}
