package me.andreisuruceanu.locationsservice.interfaces;

import me.andreisuruceanu.locationsservice.dto.city.ShortCityDescription;

import javax.persistence.criteria.CriteriaQuery;

public interface ISelectCityQueryBuilder {
    ISelectCityQueryBuilder multiselect();

    ISelectCityQueryBuilder setExpectedId(Integer id);

    CriteriaQuery<? extends ShortCityDescription> getQuery();
}
