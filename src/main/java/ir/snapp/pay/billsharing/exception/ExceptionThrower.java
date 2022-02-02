package ir.snapp.pay.billsharing.exception;

import ir.snapp.pay.billsharing.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Tue 01 Feb 2022
 */
@Component
public class ExceptionThrower {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public ExceptionThrower(PropertiesConfig propertiesConfig) {
        ExceptionThrower.propertiesConfig = propertiesConfig;
    }

    public static ServiceException throwServiceException(HttpStatus status, String messageKey, String... args) {
        return new ServiceException(messageKey, status, getFormattedMessage(messageKey, args));
    }

    private static String getFormattedMessage(String messageKey, String... args) {
        return Optional.ofNullable(propertiesConfig.getConfigValue(messageKey))
                .map(messageTemplate -> MessageFormat.format(messageTemplate, (Object[]) args))
                .orElseGet(() -> String.format(messageKey, (Object[]) args));
    }
}
