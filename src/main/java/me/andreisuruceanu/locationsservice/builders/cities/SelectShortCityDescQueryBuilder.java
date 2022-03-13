package me.andreisuruceanu.locationsservice.builders.cities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.andreisuruceanu.locationsservice.dao.CitiesTable;
import me.andreisuruceanu.locationsservice.dao.CitiesTable_;
import me.andreisuruceanu.locationsservice.dto.city.ShortCityDescription;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCityQueryBuilder;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectShortCityDescQueryBuilder implements ISelectCityQueryBuilder {
    protected CriteriaBuilder builder;
    protected CriteriaQuery<? extends ShortCityDescription> query;
    protected Root<CitiesTable> citiesTable;

    public SelectShortCityDescQueryBuilder(Session session) {
        this.builder = session.getCriteriaBuilder();
        this.query = builder.createQuery(ShortCityDescription.class);
        this.citiesTable = query.from(CitiesTable.class);
    }

    @Override
    public ISelectCityQueryBuilder multiselect() {
        this.query.multiselect(
                citiesTable.get(CitiesTable_.ID),
                citiesTable.get(CitiesTable_.NAME)
        );
        return this;
    }

    @Override
    public ISelectCityQueryBuilder setExpectedId(Integer id) {
        this.query.where(builder.equal(citiesTable.get(CitiesTable_.ID), id));
        return this;
    }

    @Override
    public CriteriaQuery<? extends ShortCityDescription> getQuery() {
        return this.query;
    }
}
