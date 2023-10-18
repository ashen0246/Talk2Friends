package com.example.talk2friends;

public class User {
    String email;
    String name;
    String bday;
    boolean isStudent;
    boolean isProficient;

    public User(String email, String name, String bday, boolean isStudent, boolean isProficient) {
        this.email = email;
        this.name = name;
        this.bday = bday;
        this.isStudent = isStudent;
        this.isProficient = isProficient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public boolean isProficient() {
        return isProficient;
    }

    public void setProficient(boolean proficient) {
        isProficient = proficient;
    }
}
