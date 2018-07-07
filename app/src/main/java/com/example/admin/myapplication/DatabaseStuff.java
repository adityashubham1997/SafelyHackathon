package com.example.admin.myapplication;

/**
 * Created by ankan on 30/03/18.
 */
public class DatabaseStuff {
    private String sex;
    private String name;
    private String blood_group;
    private String email;
    private String Emergency_Contact_number;
    private String Contact_number_user;
    private String Diabetes_Condition;
    private String B_P_Condition;
    private String Chronic_Diseases;
    private String Bhamashah_ID;
    private String Institution_Name;

    public DatabaseStuff(String name, String email, String sex,String blood_group,String Emergency_Contact_number, String Contact_number_user,String Diabetes_Condition,String B_P_Condition,String Chronic_Diseases,String Bhahmashah_ID,String Institution_Name ) {
        this.email = email;
        this.name = name;
        this.sex = sex;
        this.blood_group=blood_group;
        this.Emergency_Contact_number = Emergency_Contact_number;
        this.Contact_number_user = Contact_number_user;
        this.Diabetes_Condition = Diabetes_Condition;
        this.B_P_Condition = B_P_Condition;
        this.Chronic_Diseases =Chronic_Diseases;
        this.Bhamashah_ID= Bhahmashah_ID;
        this.Institution_Name=Institution_Name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String name) {
        this.sex = name;
    }

    public String getBloodGroup()
    {
        return blood_group;
    }
    public void setBloodGroup(String blood_group)
    {
        this.blood_group = blood_group;
    }
    public String getEmergency_Contact_number() {
        return Emergency_Contact_number;
    }

    public void setEmergency_Contact_number(String Emergency_Contact_number) {
        this.Emergency_Contact_number = Emergency_Contact_number;
    }
    public String getContact_number_user() {
        return Contact_number_user;
    }
    public void setContact_number_user(String name) {
        this.Contact_number_user = name;
    }

    public String getDiabetes_Condition() {
        return Diabetes_Condition;
    }
    public void setDiabetes_Condition(String name) {
        this.Diabetes_Condition= name;
    }

    public String getB_P_Condition() {
        return B_P_Condition;
    }
    public void setB_P_Condition(String name) {
        this.B_P_Condition = name;
    }
    public String getChronic_Diseases() {
        return Chronic_Diseases;
    }
    public void setChronic_Diseases(String name) {
        this.Chronic_Diseases = name;
    }

    public String getBhamashah_ID() {
        return Bhamashah_ID;
    }
    public void setBhamashah_ID(String name) {
        this.Bhamashah_ID = name;
    }
    public String getInstituiton_Name() {
        return Institution_Name;
    }
    public void setInstitution_Name(String name) {
        this.Institution_Name= name;
    }
}



