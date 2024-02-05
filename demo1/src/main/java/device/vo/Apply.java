package cn.yzw.device.vo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class Apply implements Serializable {

    private Integer id;
    private String name;
    private String reason;
    private Integer count;
    private Double amount;
    private Date applyDate;
    private String applyPerson;
    private String category;
    private Integer cid;
    private Integer state;
    private  String specs;
    private Double price;
    private String cateNum;
    private Integer isShow;
    private String opinion;
    private Date checkDate;
    private String checkPerson;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getCateNum() {
        return cateNum;
    }

    public void setCateNum(String cateNum) {
        this.cateNum = cateNum;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reason='" + reason + '\'' +
                ", count=" + count +
                ", amount=" + amount +
                ", applyDate=" + applyDate +
                ", applyPerson='" + applyPerson + '\'' +
                ", category='" + category + '\'' +
                ", cid=" + cid +
                ", state=" + state +
                ", specs='" + specs + '\'' +
                ", price=" + price +
                ", cateNum='" + cateNum + '\'' +
                ", isShow=" + isShow +
                ", opinion=" + opinion + '\'' +
                ", checkDate=" + checkDate +
                ", checkPerson='" + checkPerson + '\'' +
                '}';
    }
}
