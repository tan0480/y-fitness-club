public abstract class User {
    private String userID;
    private String userName;
    private String phone;
    private String email;
    private String password;
    private String role;
    private Gender gender;

    // Tracks the currently logged-in user across the system
    private static User currentUser = null;

    // No-arg constructor
    public User() {
        this.userID = "";
        this.userName = "";
        this.phone = "";
        this.email = "";
        this.password = "";
        this.role = "";
        this.gender = null;
    }

    // Parameterized constructor
    public User(String userID, String userName, String phone, String email,
                String password, String role, Gender gender) {
        this.userID = userID;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.gender = gender;
    }

    public boolean login() {
        if (userID != null && password != null) {
            currentUser = this;
            System.out.println("Welcome back, " + userName + "! You are now logged in.");
            return true;
        }
        System.out.println("Login failed. Invalid credentials.");
        return false;
    }

    public void logout() {
        System.out.println("Thank you, " + userName + "! You have been logged out.");
        System.out.println("Goodbye! See you again at YFitness.");
        currentUser = null;
        System.exit(0);
    }

    // Returns the currently logged-in user
    public static User getCurrentUser() {
        return currentUser;
    }

    // Getters
    public String getUserID()   { return userID; }
    public String getUserName() { return userName; }
    public String getPhone()    { return phone; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public String getRole()     { return role; }
    public Gender getGender()   { return gender; }

    // Setters
    public void setUserID(String userID)       { this.userID = userID; }
    public void setUserName(String userName)   { this.userName = userName; }
    public void setPhone(String phone)         { this.phone = phone; }
    public void setEmail(String email)         { this.email = email; }
    public void setPassword(String password)   { this.password = password; }
    public void setRole(String role)           { this.role = role; }
    public void setGender(Gender gender)       { this.gender = gender; }

    @Override
    public String toString() {
        return "User ID: " + userID +
               "\nName: " + userName +
               "\nPhone: " + phone +
               "\nEmail: " + email +
               "\nRole: " + role +
               "\nGender: " + gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return this.userID.equals(other.userID);
    }
}
