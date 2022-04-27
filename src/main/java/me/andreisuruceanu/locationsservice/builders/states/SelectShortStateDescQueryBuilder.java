package me.andreisuruceanu.locationsservice.builders.states;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.andreisuruceanu.locationsservice.dao.CountriesTable;
import me.andreisuruceanu.locationsservice.dao.CountriesTable_;
import me.andreisuruceanu.locationsservice.dao.StatesTable;
import me.andreisuruceanu.locationsservice.dao.StatesTable_;
import me.andreisuruceanu.locationsservice.dto.state.ShortStateDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectStateQueryBuilder;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectShortStateDescQueryBuilder implements ISelectStateQueryBuilder {
    protected CriteriaBuilder builder;
    protected CriteriaQuery<? extends ShortStateDescription> query;
    protected Root<StatesTable> statesTable;
    protected Join<StatesTable, CountriesTable> countriesTable;

    public SelectShortStateDescQueryBuilder(Session session) {
        builder = session.getCriteriaBuilder();
        query = builder.createQuery(ShortStateDescription.class);
        statesTable = query.from(StatesTable.class);
        countriesTable = statesTable.join(StatesTable_.COUNTRIES_TABLE);
    }

    @Override
    public ISelectStateQueryBuilder multiselect() {
        query.multiselect(
                statesTable.get(StatesTable_.ID),
                statesTable.get(StatesTable_.NAME)
        );

        return this;
    }

    @Override
    public ISelectStateQueryBuilder setExpectedId(Short id) {
        this.query.where(builder.equal(statesTable.get(StatesTable_.ID), id));
        return this;
    }

    @Override
    public ISelectStateQueryBuilder setCountryId(Short id) {
        this.query.where(builder.equal(countriesTable.get(CountriesTable_.ID), id));
        return this;
    }

    @Override
    public CriteriaQuery<? extends ShortStateDescription> getQuery() {
        return this.query;
    }
}
