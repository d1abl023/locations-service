package me.andreisuruceanu.locationsservice.builders.currencies;

import lombok.NoArgsConstructor;
import me.andreisuruceanu.locationsservice.dao.CountriesTable;
import me.andreisuruceanu.locationsservice.dao.CountriesTable_;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable_;
import me.andreisuruceanu.locationsservice.dto.currency.AdvancedCurrencyDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCurrencyQueryBuilder;
import org.hibernate.Session;

@NoArgsConstructor
public class SelectAdvCurrDescQueryBuilder extends SelectShortCurrDescQueryBuilder {
    public SelectAdvCurrDescQueryBuilder(Session session) {
        super();
        this.builder = session.getCriteriaBuilder();
        this.query = builder.createQuery(AdvancedCurrencyDescription.class);
        this.countriesTable = this.query.from(CountriesTable.class);
        this.currenciesTable = this.countriesTable.join(CountriesTable_.CURRENCIES_TABLE);
    }

    @Override
    public ISelectCurrencyQueryBuilder multiselect() {
        this.query.multiselect(
                this.currenciesTable.get(CurrenciesTable_.ID),
                this.currenciesTable.get(CurrenciesTable_.CURRENCY_SYMBOL),
                this.currenciesTable.get(CurrenciesTable_.CURRENCY),
                this.currenciesTable.get(CurrenciesTable_.CURRENCY_NAME)
        );
        return this;
    }
}
