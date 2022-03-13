package me.andreisuruceanu.locationsservice.interfaces;

import me.andreisuruceanu.locationsservice.dto.state.ShortStateDescription;

import javax.persistence.criteria.CriteriaQuery;

public interface ISelectStateQueryBuilder {

    ISelectStateQueryBuilder multiselect();

    ISelectStateQueryBuilder setExpectedId(Short id);

    CriteriaQuery<? extends ShortStateDescription> getQuery();
}
