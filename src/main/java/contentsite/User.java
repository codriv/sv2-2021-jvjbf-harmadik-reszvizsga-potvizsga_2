package contentsite;

public class User {

    private String userName;
    private int password;
    private boolean isPremiumMember;
    private boolean isLogIn;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = (userName + password).hashCode();
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isPremiumMember() {
        return isPremiumMember;
    }

    public boolean isLogIn() {
        return isLogIn;
    }

    public void setLogIn(boolean value) {
        isLogIn = value;
    }

    public void upgradeForPremium() {
        isPremiumMember = true;
    }
}
