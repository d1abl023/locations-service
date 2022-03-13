package me.andreisuruceanu.locationsservice.builders.countries;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.andreisuruceanu.locationsservice.dao.CountriesTable;
import me.andreisuruceanu.locationsservice.dao.CountriesTable_;
import me.andreisuruceanu.locationsservice.dto.country.ShortCountryDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCountryQueryBuilder;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectShortCountryDescQueryBuilder implements ISelectCountryQueryBuilder {
    protected CriteriaBuilder builder;
    protected CriteriaQuery<? extends ShortCountryDescription> query;
    protected Root<CountriesTable> countriesTable;

    public SelectShortCountryDescQueryBuilder(Session session){
        this.builder = session.getCriteriaBuilder();
        this.query = this.builder.createQuery(ShortCountryDescription.class);
        this.countriesTable = this.query.from(CountriesTable.class);
    }

    @Override
    public ISelectCountryQueryBuilder multiselect() {
        this.query.multiselect(
                countriesTable.get(CountriesTable_.ID),
                countriesTable.get(CountriesTable_.NAME),
                countriesTable.get(CountriesTable_.ISO2),
                countriesTable.get(CountriesTable_.NATIVE_NAME)
        );
        return this;
    }

    @Override
    public ISelectCountryQueryBuilder setExpectedId(Short id) {
        this.query.where(builder.equal(countriesTable.get(CountriesTable_.ID), id));
        return this;
    }

    @Override
    public CriteriaQuery<? extends ShortCountryDescription> getQuery() {
        return this.query;
    }
}
