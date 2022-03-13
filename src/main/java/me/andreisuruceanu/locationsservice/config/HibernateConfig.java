package me.andreisuruceanu.locationsservice.config;

import me.andreisuruceanu.locationsservice.dao.CitiesTable;
import me.andreisuruceanu.locationsservice.dao.CountriesTable;
import me.andreisuruceanu.locationsservice.dao.CurrenciesTable;
import me.andreisuruceanu.locationsservice.dao.StatesTable;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateConfig {
    private static Properties properties;

    private static Properties getProperties(){
        if(properties == null){
            properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");

            properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/locations");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "postgres");

            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
            properties.put(Environment.SHOW_SQL, "true");
        }
        return properties;
    }

    @SuppressWarnings("WeakerAccess")
    public static Configuration getConfiguration(){
        return new Configuration()
                .setProperties(getProperties())
                .addAnnotatedClass(CitiesTable.class)
                .addAnnotatedClass(StatesTable.class)
                .addAnnotatedClass(CountriesTable.class)
                .addAnnotatedClass(CurrenciesTable.class);

    }
}
