package me.andreisuruceanu.locationsservice.restcontrollers;

import me.andreisuruceanu.locationsservice.dao.CurrenciesTable;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable_;
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
    public ResponseEntity<List<CurrenciesTable>> getAllCurrencies() {
        try (Session session = HibernateService.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CurrenciesTable> query = builder.createQuery(CurrenciesTable.class);
            Root<CurrenciesTable> table = query.from(CurrenciesTable.class);
            query.select(table);
            return ResponseEntity.ok(session.createQuery(query).getResultList());
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
}
