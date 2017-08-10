/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datingsystem2017;

/**
 *
 * @author Tatha
 */
public class UserProfile {
    private String id;
    private String name;
    private int age;
    private String sex;
    private String city;
    private String i1;
    private String i2;
    private String i3;
    private String last_loged;
    private int views;
    
    public UserProfile(String id,String name,int age,String sex,String city,String i1,String i2,String i3,String last_loged,int views)
    {
        this.id=id;
        this.name=name;
        this.age=age;
        this.city=city;
        this.sex=sex;
        this.i1=i1;
        this.i2=i2;
        this.i3=i3;
        this.last_loged=last_loged;
        this.views=views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getI1() {
        return i1;
    }

    public void setI1(String i1) {
        this.i1 = i1;
    }

    public String getI2() {
        return i2;
    }

    public void setI2(String i2) {
        this.i2 = i2;
    }

    public String getI3() {
        return i3;
    }

    public void setI3(String i3) {
        this.i3 = i3;
    }

    public String getLast_loged() {
        return last_loged;
    }

    public void setLast_loged(String last_loged) {
        this.last_loged = last_loged;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
