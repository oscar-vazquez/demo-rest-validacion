package edesur.demo;

import static java.util.Collections.singletonList;
import org.hibernate.validator.HibernateValidator;
import javax.validation.ValidationProviderResolver;
import javax.validation.spi.ValidationProvider;
import java.util.ArrayList;
import java.util.List;


/**
 * OSGi-friendly implementation of {@code javax.validation.ValidationProviderResolver} returning
 * {@code org.hibernate.validator.HibernateValidator} instance.
 *
 */
public class HibernateValidationProviderResolver implements ValidationProviderResolver {
    @Override
    public List<ValidationProvider<?>> getValidationProviders() {
        List<ValidationProvider<?>> list = new ArrayList<>(1);
        list.add(new HibernateValidator());
        return list;
    }
}