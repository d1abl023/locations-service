package me.andreisuruceanu.locationsservice.restcontrollers;

import me.andreisuruceanu.locationsservice.builders.cities.SelectAdvCityDescQueryBuilder;
import me.andreisuruceanu.locationsservice.builders.cities.SelectShortCityDescQueryBuilder;
import me.andreisuruceanu.locationsservice.builders.currencies.SelectAdvCurrDescQueryBuilder;
import me.andreisuruceanu.locationsservice.builders.currencies.SelectFullCurrDescQueryBuilder;
import me.andreisuruceanu.locationsservice.builders.currencies.SelectShortCurrDescQueryBuilder;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable_;
import me.andreisuruceanu.locationsservice.dto.currency.ShortCurrencyDescription;
import me.andreisuruceanu.locationsservice.enums.DescriptionType;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCityQueryBuilder;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCurrencyQueryBuilder;
import me.andreisuruceanu.locationsservice.service.HibernateService;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequestMapping("/locations/currencies")
public class CurrenciesController {

    @GetMapping("/all")
    public ResponseEntity<List<? extends ShortCurrencyDescription>> getAllCurrencies(@RequestParam DescriptionType type) {
        try (Session session = HibernateService.getSessionFactory().openSession()) {
            ISelectCurrencyQueryBuilder builder = getBuilder(type, session);
            builder.multiselect();
            return ResponseEntity.ok(session.createQuery(builder.getQuery()).getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/by-country-id")
    public ResponseEntity<? extends ShortCurrencyDescription> getCurrencyByCountryId(@RequestParam DescriptionType type,
                                                                                     @RequestParam Short cuid) {
        try (Session session = HibernateService.getSessionFactory().openSession()) {
            ISelectCurrencyQueryBuilder builder = getBuilder(type, session);
            builder.multiselect();
            builder.setExpectedCountryId(cuid);
            return ResponseEntity.ok(session.createQuery(builder.getQuery()).getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/by-id")
    public ResponseEntity<CurrenciesTable> getCurrencyById(@RequestParam Short id) {
        try (Session session = HibernateService.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CurrenciesTable> query = builder.createQuery(CurrenciesTable.class);
            Root<CurrenciesTable> table = query.from(CurrenciesTable.class);
            query.select(table).where(builder.equal(table.get(CurrenciesTable_.ID), id));
            return ResponseEntity.ok(session.createQuery(query).getSingleResult());
        } catch (NoResultException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    private ISelectCurrencyQueryBuilder getBuilder(DescriptionType type, Session session) {
        switch (type) {
            case SHORT: {
                return new SelectShortCurrDescQueryBuilder(session);
            }
            case ADVANCED: {
                return new SelectAdvCurrDescQueryBuilder(session);
            }
            case FULL: {
                return new SelectFullCurrDescQueryBuilder(session);
            }
            default: {
                throw new IllegalArgumentException("Unsupported entity type: " + type);
            }
        }
    }
}
