package me.andreisuruceanu.locationsservice.dto.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortCountryDescription implements Serializable {
    protected Short id;
    protected String name;
    protected String iso2;
    protected String nativeName;
}
