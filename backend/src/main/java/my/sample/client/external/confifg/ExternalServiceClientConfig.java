package my.sample.client.external.confifg;

public class ExternalServiceClientConfig {

    String username;

    String password;

    String bankUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getBankUrl() {
        return bankUrl;
    }

    public void setBankUrl( String bankUrl ) {
        this.bankUrl = bankUrl;
    }

    public void setServiceUrl( String bankUrl ) {
        this.bankUrl = bankUrl;
    }
}
