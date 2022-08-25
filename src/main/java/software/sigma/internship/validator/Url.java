package software.sigma.internship.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UrlValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {
    String message() default "Invalid URL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
