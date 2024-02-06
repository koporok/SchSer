package com.example.demo200000.enity;


public class Coaches {
    private int id;

    private String full_name;

    private String contact_information;


    private String login;
    private String information;

    public Coaches(int id, String full_name, String contact_information, String login, String information) {
        this.id = id;
        this.full_name = full_name;
        this.contact_information = contact_information;
        this.login = login;
        this.information = information;
    }

    public Coaches(String full_name, String contact_information, String login, String information) {
        this.full_name = full_name;
        this.contact_information = contact_information;
        this.login = login;
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getContact_information() {
        return contact_information;
    }

    public void setContact_information(String contact_information) {
        this.contact_information = contact_information;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
