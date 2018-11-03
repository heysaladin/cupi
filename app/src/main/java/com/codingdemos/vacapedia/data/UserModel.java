package com.codingdemos.vacapedia.data;

public class UserModel {

    private String _id;
    private String name;
    private String full_name;
    private String photo_profile;
    private String sex_type;
    private String birth_date;
    private String email_address;
    private String phone_number;
    private String country;
    private String sayings;

    public UserModel() {}

    @Override
    public String toString() {
        return "UserModel{" +
                "_id='" + get_id() + "\'" +
                ", name='" + getName() + "\'" +
                ", full_name='" + getFull_name() + "\'" +
                ", photo_profile='" + getPhoto_profile() + "\'" +
                ", sex_type='" + getSex_type() + "\'" +
                ", birth_date='" + getBirth_date() + "\'" +
                ", email_address='" + getEmail_address() + "\'" +
                ", phone_number='" + getPhone_number() + "\'" +
                ", country='" + getCountry() + "\'" +
                ", sayings='" + getSayings() + "\'" +
                "}";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }

    public String getSex_type() {
        return sex_type;
    }

    public void setSex_type(String sex_type) {
        this.sex_type = sex_type;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSayings() {
        return sayings;
    }

    public void setSayings(String sayings) {
        this.sayings = sayings;
    }
}
