package ir.snapp.pay.billsharing.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Wed 02 Feb 2022
 */
public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {

    private static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();

    @Override
    public void initialize(Phone constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            return false;
        }
        ConstraintValidatorContextImpl contextImpl = (ConstraintValidatorContextImpl) context;
        Phone phoneAnnotation = (Phone) contextImpl.getConstraintDescriptor().getAnnotation();
        String locale = phoneAnnotation.locale();
        try {
            Phonenumber.PhoneNumber phone = PHONE_NUMBER_UTIL.parse(value, locale);
            return PHONE_NUMBER_UTIL.isValidNumber(phone);
        } catch (NumberParseException e) {
            return false;
        }
    }
}
