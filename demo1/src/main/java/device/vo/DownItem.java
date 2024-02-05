package cn.yzw.device.vo;

import java.io.Serializable;
import java.util.Date;

public class DownItem implements Serializable {

    private Integer id;
    private String name;
    private String number;
    private String category;
    private String specs;
    private Double price;
    private String factory;
    private Date buyDate;
    private String QGP;
    private String opinion;
    private Date downDate;
    private String doPerson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getQGP() {
        return QGP;
    }

    public void setQGP(String QGP) {
        this.QGP = QGP;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getDownDate() {
        return downDate;
    }

    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    public String getDoPerson() {
        return doPerson;
    }

    public void setDoPerson(String doPerson) {
        this.doPerson = doPerson;
    }

    @Override
    public String toString() {
        return "DownItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", category='" + category + '\'' +
                ", specs='" + specs + '\'' +
                ", price=" + price +
                ", factory='" + factory + '\'' +
                ", buyDate=" + buyDate +
                ", QGP='" + QGP + '\'' +
                ", opinion='" + opinion + '\'' +
                ", downDate=" + downDate +
                ", doPerson='" + doPerson + '\'' +
                '}';
    }
}
