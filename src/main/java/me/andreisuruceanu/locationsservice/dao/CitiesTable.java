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
@Table(name = "cities")
public class CitiesTable implements Serializable {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column(name = "state_id")
    private Short stateId;
    @Column(name = "country_id")
    private Short countryId;
    @Column
    private String latitude;
    @Column
    private String longitude;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column
    private Short flag;
    @Column(name = "wiki_data_id")
    private String wikiDataId;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", updatable = false, insertable = false)
    private CountriesTable countriesTable;@ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", updatable = false, insertable = false)
    private StatesTable statesTable;
}
