package ir.snapp.pay.billsharing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Tue 01 Feb 2022
 */
@Component
@PropertySource("classpath:message.properties")
public class PropertiesConfig {

    private final Environment env;

    @Autowired
    public PropertiesConfig(Environment env) {
        this.env = env;
    }

    public String getConfigValue(String key) {
        return env.getProperty(key);
    }
}
