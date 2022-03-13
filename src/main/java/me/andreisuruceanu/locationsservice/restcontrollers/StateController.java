package me.andreisuruceanu.locationsservice.restcontrollers;

import me.andreisuruceanu.locationsservice.builders.states.SelectShortStateDescQueryBuilder;
import me.andreisuruceanu.locationsservice.builders.states.SelectAdvStateDescQueryBuilder;
import me.andreisuruceanu.locationsservice.dto.state.ShortStateDescription;
import me.andreisuruceanu.locationsservice.enums.DescriptionType;
import me.andreisuruceanu.locationsservice.interfaces.ISelectStateQueryBuilder;
import me.andreisuruceanu.locationsservice.service.HibernateService;

import org.hibernate.Session;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {
    @GetMapping("/all")
    public ResponseEntity<List<? extends ShortStateDescription>> getAllStates(@RequestParam DescriptionType type) {
        try (Session session = HibernateService.getSessionFactory().openSession()) {
            ISelectStateQueryBuilder builder = getBuilder(type, session);
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
    public ResponseEntity<? extends ShortStateDescription> getStateById(@RequestParam DescriptionType type, @RequestParam Short id) {
        try (Session session = HibernateService.getSessionFactory().openSession()) {
            ISelectStateQueryBuilder builder = getBuilder(type, session);
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

    private ISelectStateQueryBuilder getBuilder(DescriptionType type, Session session) throws IllegalArgumentException {
        switch (type) {
            case SHORT: {
                return new SelectShortStateDescQueryBuilder(session);
            }
            case ADVANCED: {
                return new SelectAdvStateDescQueryBuilder(session);
            }
            case FULL:
            default: {
                throw new IllegalArgumentException("Unsupported entity type: " + type);
            }
        }
    }
}
