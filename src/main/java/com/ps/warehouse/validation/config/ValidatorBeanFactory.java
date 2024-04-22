package com.ps.warehouse.validation.config;

import com.ps.warehouse.validation.annotations.ValidDealDetail;
import com.ps.warehouse.validation.validators.DealDetailValidator;
import jakarta.validation.ConstraintValidator;
import java.lang.annotation.Annotation;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Component
public class ValidatorBeanFactory extends LocalValidatorFactoryBean {

    @Override
    protected void postProcessConfiguration(jakarta.validation.Configuration<?> configuration) {
        HibernateValidatorConfiguration hibernateConfiguration = (HibernateValidatorConfiguration) configuration;
        addMappings(hibernateConfiguration);
    }


    protected void addMappings(HibernateValidatorConfiguration hibernateConfiguration) {
        // add annotation with the targeted dto here
        addMappingToConfiguration(hibernateConfiguration, ValidDealDetail.class, DealDetailValidator.class);

    }

    protected <A extends Annotation> void addMappingToConfiguration(HibernateValidatorConfiguration hibernateConfiguration, Class<A> annotationClass,
            Class<? extends ConstraintValidator<A, ?>> validatorClass) {
        ConstraintMapping mapping = hibernateConfiguration.createConstraintMapping();
        mapping
                .constraintDefinition(annotationClass)
                .validatedBy(validatorClass)
                .includeExistingValidators(true);
        hibernateConfiguration.addMapping(mapping);

    }
}
