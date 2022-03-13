package me.andreisuruceanu.locationsservice.dto.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortCurrencyDescription {
    private Short id;
    private String currencySymbol;
}
