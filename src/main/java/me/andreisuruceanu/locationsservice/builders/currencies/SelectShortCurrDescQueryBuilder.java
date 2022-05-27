package me.andreisuruceanu.locationsservice.builders.currencies;

import lombok.NoArgsConstructor;
import me.andreisuruceanu.locationsservice.dao.CountriesTable;
import me.andreisuruceanu.locationsservice.dao.CountriesTable_;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable_;
import me.andreisuruceanu.locationsservice.dto.currency.ShortCurrencyDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCurrencyQueryBuilder;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@NoArgsConstructor
public class SelectShortCurrDescQueryBuilder implements ISelectCurrencyQueryBuilder {
    protected CriteriaBuilder builder;
    protected CriteriaQuery<? extends ShortCurrencyDescription> query;
    protected Root<CountriesTable> countriesTable;
    protected Join<CountriesTable, CurrenciesTable> currenciesTable;

    public SelectShortCurrDescQueryBuilder(Session session) {
        this.builder = session.getCriteriaBuilder();
        this.query = builder.createQuery(ShortCurrencyDescription.class);
        this.countriesTable = this.query.from(CountriesTable.class);
        this.currenciesTable = this.countriesTable.join(CountriesTable_.CURRENCIES_TABLE);
    }

    @Override
    public ISelectCurrencyQueryBuilder multiselect() {
        this.query.multiselect(
                currenciesTable.get(CurrenciesTable_.ID),
                currenciesTable.get(CurrenciesTable_.CURRENCY_SYMBOL)
        );
        return this;
    }

    @Override
    public ISelectCurrencyQueryBuilder setExpectedId(Short id) {
        this.query.where(builder.equal(currenciesTable.get(CurrenciesTable_.ID), id));
        return this;
    }

    @Override
    public CriteriaQuery<? extends ShortCurrencyDescription> getQuery() {
        return this.query;
    }
}
