package my.sample.client.external.deps;

public class ExternalServiceClientDeps {

    String username;

    String password;

    String branchUrl;

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

    public String getBranchUrl() {
        return branchUrl;
    }

    public void setBranchUrl( String branchUrl ) {
        this.branchUrl = branchUrl;
    }

    public String getBankUrl() {
        return bankUrl;
    }

    public void setServiceUrl( String bankUrl ) {
        this.bankUrl = bankUrl;
    }
}
