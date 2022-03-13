package me.andreisuruceanu.locationsservice.interfaces;

import me.andreisuruceanu.locationsservice.dto.country.ShortCountryDescription;

import javax.persistence.criteria.CriteriaQuery;

public interface ISelectCountryQueryBuilder {
    ISelectCountryQueryBuilder multiselect();

    ISelectCountryQueryBuilder setExpectedId(Short id);

    CriteriaQuery<? extends ShortCountryDescription> getQuery();
}
