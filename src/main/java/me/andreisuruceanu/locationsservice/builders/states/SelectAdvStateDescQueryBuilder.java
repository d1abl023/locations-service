package me.andreisuruceanu.locationsservice.builders.states;

import me.andreisuruceanu.locationsservice.dao.*;
import me.andreisuruceanu.locationsservice.dto.state.AdvancedStateDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectStateQueryBuilder;
import org.hibernate.Session;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class SelectAdvStateDescQueryBuilder extends SelectShortStateDescQueryBuilder {
    protected Join<CountriesTable, CurrenciesTable> currenciesTable;

    public SelectAdvStateDescQueryBuilder(Session session){
        super();
        this.builder = session.getCriteriaBuilder();
        this.query = builder.createQuery(AdvancedStateDescription.class);
        this.statesTable = query.from(StatesTable.class);
        this.countriesTable = statesTable.join(StatesTable_.COUNTRIES_TABLE);
        this.currenciesTable = this.countriesTable.join(CountriesTable_.CURRENCIES_TABLE, JoinType.INNER);
    }

    @Override
    public ISelectStateQueryBuilder multiselect(){
        query.multiselect(
                statesTable.get(StatesTable_.ID),
                statesTable.get(StatesTable_.NAME),
                countriesTable.get(CountriesTable_.ID),
                countriesTable.get(CountriesTable_.NAME),
                countriesTable.get(CountriesTable_.ISO2),
                countriesTable.get(CountriesTable_.NATIVE_NAME),
                currenciesTable.get(CurrenciesTable_.ID),
                currenciesTable.get(CurrenciesTable_.CURRENCY_SYMBOL)
        );
        return this;
    }
}
