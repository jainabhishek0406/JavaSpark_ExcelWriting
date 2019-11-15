package main.java.model;

import java.io.Serializable;

/**
 * @author Abhishek Jain
 */

public class ExcelUtilModel implements Serializable {
    private static final long serialVersionUID = 8591562805688184821L;

    private String name;
    private Integer custid_pk;
    private String state;

    private Integer orderid;
    private String type;
    private Integer unit;
    private Float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustid_pk() {
        return custid_pk;
    }

    public void setCustid_pk(Integer custid_pk) {
        this.custid_pk = custid_pk;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ExcelUtilModel{" +
                "name='" + name + '\'' +
                ", custid_pk=" + custid_pk +
                ", state='" + state + '\'' +
                ", orderid=" + orderid +
                ", type='" + type + '\'' +
                ", unit=" + unit +
                ", price=" + price +
                '}';
    }
}
