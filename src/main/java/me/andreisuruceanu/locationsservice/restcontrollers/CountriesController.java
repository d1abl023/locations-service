package me.andreisuruceanu.locationsservice.restcontrollers;

import me.andreisuruceanu.locationsservice.builders.countries.SelectAdvCountryDescQueryBuilder;
import me.andreisuruceanu.locationsservice.builders.countries.SelectShortCountryDescQueryBuilder;
import me.andreisuruceanu.locationsservice.dto.country.ShortCountryDescription;
import me.andreisuruceanu.locationsservice.enums.DescriptionType;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCountryQueryBuilder;
import me.andreisuruceanu.locationsservice.service.HibernateService;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    @GetMapping("/all")
    public ResponseEntity<List<? extends ShortCountryDescription>> getAllCountries(@RequestParam DescriptionType type) {
        try (Session session = HibernateService.getSessionFactory().openSession()) {
            ISelectCountryQueryBuilder builder = getBuilder(type, session);
            builder.multiselect();

            return ResponseEntity.ok(session.createQuery(builder.getQuery()).getResultList());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/by-id")
    public ResponseEntity<ShortCountryDescription> getCountryById(@RequestParam DescriptionType type, @RequestParam Short id) {
        try (Session session = HibernateService.getSessionFactory().openSession()) {
            ISelectCountryQueryBuilder builder = getBuilder(type, session);
            builder.multiselect().setExpectedId(id);
            return ResponseEntity.ok(session.createQuery(builder.getQuery()).getSingleResult());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    private ISelectCountryQueryBuilder getBuilder(DescriptionType type, Session session) throws IllegalArgumentException {
        switch (type) {
            case SHORT: {
                return new SelectShortCountryDescQueryBuilder(session);
            }
            case ADVANCED: {
                return new SelectAdvCountryDescQueryBuilder(session);
            }
            case FULL:
            default: {
                throw new IllegalArgumentException("Unsupported entity type: " + type);
            }
        }
    }
}
