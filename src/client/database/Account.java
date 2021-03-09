package client.database;

public class Account {
    private String username;
    private int password;

    public Account(String username, int password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object != null && object instanceof Account)
        {
            return this.username.equals (((Account) object).username) && this.password == ((Account) object).password;
        }

        return false;
    }
}
