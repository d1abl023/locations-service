package me.andreisuruceanu.locationsservice.restcontrollers;

import me.andreisuruceanu.locationsservice.builders.cities.SelectAdvCityDescQueryBuilder;
import me.andreisuruceanu.locationsservice.builders.cities.SelectShortCityDescQueryBuilder;
import me.andreisuruceanu.locationsservice.dto.city.ShortCityDescription;
import me.andreisuruceanu.locationsservice.enums.DescriptionType;
import me.andreisuruceanu.locationsservice.interfaces.ISelectCityQueryBuilder;
import me.andreisuruceanu.locationsservice.service.HibernateService;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @GetMapping("/all")
    public ResponseEntity<List<? extends ShortCityDescription>> getAllCities(@RequestParam DescriptionType type) {
        try(Session session = HibernateService.getSessionFactory().openSession()){
            ISelectCityQueryBuilder builder = getBuilder(type, session);
            builder.multiselect();
            return ResponseEntity.ok(session.createQuery(builder.getQuery()).getResultList());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/by-id")
    public ResponseEntity<? extends ShortCityDescription> getCityById(@RequestParam DescriptionType type,
                                                                      @RequestParam Integer id) {
        try(Session session = HibernateService.getSessionFactory().openSession()){
            ISelectCityQueryBuilder builder = getBuilder(type, session);
            builder.multiselect().setExpectedId(id);

            return ResponseEntity.ok(session.createQuery(builder.getQuery()).getSingleResult());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    private ISelectCityQueryBuilder getBuilder(DescriptionType type, Session session) {
        switch (type) {
            case SHORT: {
                return new SelectShortCityDescQueryBuilder(session);
            }
            case ADVANCED: {
                return new SelectAdvCityDescQueryBuilder(session);
            }
            case FULL:
            default: {
                throw new IllegalArgumentException("Unsupported entity type: " + type);
            }
        }
    }
}
