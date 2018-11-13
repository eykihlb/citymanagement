package main.java.com.mydao.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "token")
public class AccessConfig {

    private String AK;
    private String SK;

    public String getAK() {
        return AK;
    }

    public void setAK(String AK) {
        this.AK = AK;
    }

    public String getSK() {
        return SK;
    }

    public void setSK(String SK) {
        this.SK = SK;
    }
}
