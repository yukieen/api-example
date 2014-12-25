package web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "customer.service", ignoreUnknownFields = false)
public class CustomerServiceProperties {

    private String url;
    
    public String getUrl() {
	return url;
    }
    
    public void setUrl(String url) {
	this.url = url;
    }

}
