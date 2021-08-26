package my.sample.config.security;

public class SampleToken {

    private String version;

    private String profile;

    private String username;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin( boolean admin ) {
        this.admin = admin;
    }

    private boolean admin;

    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile( String profile ) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }
}
