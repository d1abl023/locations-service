package me.andreisuruceanu.locationsservice.interfaces;

import me.andreisuruceanu.locationsservice.dto.currency.ShortCurrencyDescription;

import javax.persistence.criteria.CriteriaQuery;

public interface ISelectCurrencyQueryBuilder {
    ISelectCurrencyQueryBuilder multiselect();

    ISelectCurrencyQueryBuilder setExpectedId(Short id);
    ISelectCurrencyQueryBuilder setExpectedCountryId(Short cuid);

    CriteriaQuery<? extends ShortCurrencyDescription> getQuery();
}
