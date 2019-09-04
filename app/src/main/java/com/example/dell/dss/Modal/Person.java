package com.example.dell.dss.Modal;

public class Person {

    private String regnum;
    private String email;
    private String level;
    private String prog;
    private String modeOfEntry;
    private String campus;
    private String nationality;
    private String age;
    private String password;
    private String hostel;
    private String name;
    private String surname;
    private String gender;
    private String disability;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



    public String getRegnum() {
        return regnum;
    }

    public void setRegnum(String regnum) {
        this.regnum = regnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if(email.contains("@")){
            this.email = email;
        }

    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    public String getModeOfEntry() {
        return modeOfEntry;
    }

    public void setModeOfEntry(String modeOfEntry) {
        this.modeOfEntry = modeOfEntry;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
