package me.andreisuruceanu.locationsservice.builders.currencies;

import lombok.NoArgsConstructor;
import me.andreisuruceanu.locationsservice.dao.CountriesTable;
import me.andreisuruceanu.locationsservice.dao.CountriesTable_;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable_;
import me.andreisuruceanu.locationsservice.dto.currency.ShortCurrencyDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCurrencyQueryBuilder;
import org.hibernate.Session;

import javax.persistence.criteria.*;

@NoArgsConstructor
public class SelectShortCurrDescQueryBuilder implements ISelectCurrencyQueryBuilder {
    protected CriteriaBuilder builder;
    protected CriteriaQuery<? extends ShortCurrencyDescription> query;
    protected Root<CurrenciesTable> currenciesTable;

    public SelectShortCurrDescQueryBuilder(Session session) {
        this.builder = session.getCriteriaBuilder();
        this.query = builder.createQuery(ShortCurrencyDescription.class);
        this.currenciesTable = this.query.from(CurrenciesTable.class);
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
    public ISelectCurrencyQueryBuilder setExpectedCountryId(Short cuid) {
        Subquery<Short> subquery = this.query.subquery(Short.class);
        Root<CountriesTable> countriesTable = subquery.from(CountriesTable.class);
        subquery.select(countriesTable.get(CountriesTable_.CURRENCY_ID))
                .where(this.builder.equal(countriesTable.get(CountriesTable_.ID), cuid));

        this.query.where(builder.equal(currenciesTable.get(CurrenciesTable_.ID), subquery));
        return this;
    }

    @Override
    public CriteriaQuery<? extends ShortCurrencyDescription> getQuery() {
        return this.query;
    }
}
