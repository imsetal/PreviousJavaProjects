package cn.yzw.device.vo;

import java.io.Serializable;
import java.util.Date;

public class FixInfo implements Serializable {
    private Integer id;
    private String number;
    private String name;
    private String fixFactory;
    private Date fixDate;
    private String fixPerson;
    private Double fixPrice;
    private String opinion;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFixFactory() {
        return fixFactory;
    }

    public void setFixFactory(String fixFactory) {
        this.fixFactory = fixFactory;
    }

    public Date getFixDate() {
        return fixDate;
    }

    public void setFixDate(Date fixDate) {
        this.fixDate = fixDate;
    }

    public String getFixPerson() {
        return fixPerson;
    }

    public void setFixPerson(String fixPerson) {
        this.fixPerson = fixPerson;
    }

    public Double getFixPrice() {
        return fixPrice;
    }

    public void setFixPrice(Double fixPrice) {
        this.fixPrice = fixPrice;
    }

    @Override
    public String toString() {
        return "FixInfo{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", fixFactory='" + fixFactory + '\'' +
                ", fixDate=" + fixDate +
                ", fixPerson='" + fixPerson + '\'' +
                ", opinion='" + opinion + '\'' +
                ", fixPrice=" + fixPrice +
                '}';
    }
}
