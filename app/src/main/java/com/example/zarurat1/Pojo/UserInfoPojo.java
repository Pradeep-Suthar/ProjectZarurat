package com.example.zarurat1.Pojo;

public class UserInfoPojo {
    private String Name;
    private String Email;
    private String Moble_no;
    private String Pwd;
    private String id;

    public String getConPwd() {
        return ConPwd;
    }

    public void setConPwd(String conPwd) {
        ConPwd = conPwd;
    }

    private String ConPwd;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMoble_no() {
        return Moble_no;
    }

    public void setMoble_no(String moble_no) {
        Moble_no = moble_no;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    private String img;
    private String dob;
}
