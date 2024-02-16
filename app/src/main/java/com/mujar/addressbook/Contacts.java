package com.mujar.addressbook;

public class Contacts {
    private String Name;
    private String Phone;
    private String Company;
    private String Email;

    public Contacts(String name, String phone, String company, String email){
        Name = name;
        Phone = phone;
        Company = company;
        Email = email;
    }

    public String getName(){
        return Name;
    }

    public void setName(String fName){
        Name = fName;
    }

    public String getPhone(){
        return Phone;
    }

    public void setPhone(String phoneNum){
        Phone = phoneNum;
    }

    public String getCompany(){
        return Company;
    }

    public void setCompany(String uCompany){
        Company = uCompany;
    }

    public String getEmail(){
        return Email;
    }

    public void setEmail(String uEmail){
        Email = uEmail;
    }
}
