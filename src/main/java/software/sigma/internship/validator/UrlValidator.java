package software.sigma.internship.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator implements ConstraintValidator<Url, String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String URL_PATTERN = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]" +
            "{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

    @Override
    public void initialize(Url constraintAnnotation) {
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        pattern = Pattern.compile(URL_PATTERN);
        matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
