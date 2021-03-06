package me.andreisuruceanu.locationsservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class CountriesTable implements Serializable {
    @Id
    private Short id; //
    @Column
    private String name; //
    @Column
    private String iso3; ////
    @Column(name = "numeric_code")
    private String numericCode; //
    @Column
    private String iso2; //
    @Column
    private String phonecode; ////
    @Column
    private String capital;
    @Column
    private String tld;
    @Column(name = "native")
    private String nativeName; //
    @Column
    private String region;
    @Column
    private String subregion;
    @Column
    private String timezones;
    @Column
    private String translations;
    @Column
    private String latitude;
    @Column
    private String longitude;
    @Column
    private String emoji;
    @Column(name = "emoji_u")
    private String emojiU;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column
    private Short flag;
    @Column(name = "wiki_data_id")
    private String wikiDataId;
    @Column(name = "currency_id")
    private Short currencyId;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CurrenciesTable currenciesTable;

}
