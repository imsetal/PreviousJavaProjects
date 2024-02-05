package cn.yzw.device.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备实体类
 */
public class Device implements Serializable {

    private Integer id;
    private String number;
    private String name;
    private String category;
    private Integer cid;
    private String specs;
    private Double price;
    private String factory;
    private String QGP;
    private Integer state;
    private Date buyTime;
    private String doPerson;

    public String getDoPerson() {
        return doPerson;
    }

    public void setDoPerson(String doPerson) {
        this.doPerson = doPerson;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public String getQGP() {
        return QGP;
    }

    public void setQGP(String QGP) {
        this.QGP = QGP;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", cid=" + cid +
                ", specs='" + specs + '\'' +
                ", price=" + price +
                ", factory='" + factory + '\'' +
                ", doPerson='" + doPerson + '\'' +
                ", QGP='" + QGP + '\'' +
                ", state=" + state +
                ", buyTime=" + buyTime +
                '}';
    }
}
