package me.andreisuruceanu.locationsservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.andreisuruceanu.locationsservice.dto.country.ShortCountryDescription;
import me.andreisuruceanu.locationsservice.service.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.ResponseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currencies")
public class CurrenciesTable implements Serializable {
    @Id
    @Column
    private Short id;
    @Column
    private String currency;
    @Column(name = "currency_name")
    private String currencyName;
    @Column(name = "currency_symbol")
    private String currencySymbol;
}
