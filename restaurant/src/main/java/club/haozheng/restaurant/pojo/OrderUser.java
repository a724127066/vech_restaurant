package club.haozheng.restaurant.pojo;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Component
@Entity
@Table(name="order_user")
public class OrderUser {
    @Id
    private String id;
    private String storeId;
    private String userId;
    private String userName;
    private String userPhone;
    private String orderTable;
    private String payChoose;
    private String storeName;
    private double orderCountActual;
    private double orderCountOriginal;
    private Date orderTimeBegin;
    private Date orderTimeFinish;
    private int orderIsPay;
    private Date orderPayTime;
    private int orderStatus;
    private int orderType;
    private String remake;


    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    @Override
    public String toString() {
        return "OrderUser{" +
                "id='" + id + '\'' +
                ", storeId='" + storeId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", orderTable='" + orderTable + '\'' +
                ", payChoose='" + payChoose + '\'' +
                ", storeName='" + storeName + '\'' +
                ", orderCountActual=" + orderCountActual +
                ", orderCountOriginal=" + orderCountOriginal +
                ", orderTimeBegin=" + orderTimeBegin +
                ", orderTimeFinish=" + orderTimeFinish +
                ", orderIsPay=" + orderIsPay +
                ", orderPayTime=" + orderPayTime +
                ", orderStatus=" + orderStatus +
                ", orderType=" + orderType +
                ", remake='" + remake + '\'' +
                '}';
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderIsPay() {
        return orderIsPay;
    }

    public void setOrderIsPay(int orderIsPay) {
        this.orderIsPay = orderIsPay;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(String orderTable) {
        this.orderTable = orderTable;
    }

    public String getPayChoose() {
        return payChoose;
    }

    public void setPayChoose(String payChoose) {
        this.payChoose = payChoose;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getOrderCountActual() {
        return orderCountActual;
    }

    public void setOrderCountActual(double orderCountActual) {
        this.orderCountActual = orderCountActual;
    }

    public double getOrderCountOriginal() {
        return orderCountOriginal;
    }

    public void setOrderCountOriginal(double orderCountOriginal) {
        this.orderCountOriginal = orderCountOriginal;
    }

    public Date getOrderTimeBegin() {
        return orderTimeBegin;
    }

    public void setOrderTimeBegin(Date orderTimeBegin) {
        this.orderTimeBegin = orderTimeBegin;
    }

    public Date getOrderTimeFinish() {
        return orderTimeFinish;
    }

    public void setOrderTimeFinish(Date orderTimeFinish) {
        this.orderTimeFinish = orderTimeFinish;
    }

}
