package me.andreisuruceanu.locationsservice.builders.countries;

import me.andreisuruceanu.locationsservice.dao.CountriesTable;
import me.andreisuruceanu.locationsservice.dao.CountriesTable_;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable_;
import me.andreisuruceanu.locationsservice.dto.country.AdvancedCountryDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCountryQueryBuilder;
import org.hibernate.Session;

import javax.persistence.criteria.Join;

public class SelectAdvCountryDescQueryBuilder extends SelectShortCountryDescQueryBuilder {
    protected Join<CountriesTable, CurrenciesTable> currenciesTable;

    public SelectAdvCountryDescQueryBuilder(Session session) {
        super();
        this.builder = session.getCriteriaBuilder();
        this.query = this.builder.createQuery(AdvancedCountryDescription.class);
        this.countriesTable = this.query.from(CountriesTable.class);
        this.currenciesTable = this.countriesTable.join(CountriesTable_.CURRENCIES_TABLE);
    }

    @Override
    public ISelectCountryQueryBuilder multiselect() {
        this.query.multiselect(
                countriesTable.get(CountriesTable_.ID),
                countriesTable.get(CountriesTable_.NAME),
                countriesTable.get(CountriesTable_.ISO2),
                countriesTable.get(CountriesTable_.NATIVE_NAME),
                countriesTable.get(CountriesTable_.ISO3),
                countriesTable.get(CountriesTable_.PHONECODE),
                countriesTable.get(CountriesTable_.CAPITAL),
                currenciesTable.get(CurrenciesTable_.ID),
                currenciesTable.get(CurrenciesTable_.CURRENCY_SYMBOL)
        );
        return this;
    }
}
