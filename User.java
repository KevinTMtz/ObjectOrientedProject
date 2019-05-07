import java.io.Serializable;

public abstract class User implements Serializable {
    private String name;
    private String password;
    private String userType;

    public String toString(){
		return name;
	}

    public void setUsername(String name) throws EmptyFieldException {
        if (name == null || name.length() == 0)
			throw new EmptyFieldException("The username cannot be null");
        this.name = name;
    }

    public void setPassword(String password) throws EmptyFieldException {
        if (password == null || password.length() == 0)
			throw new EmptyFieldException("The password cannot be null");
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return(name);
    }

    public String getPassword() {
        return(password);    
    }

    public String getUserType() {
        return(userType);    
    }
}