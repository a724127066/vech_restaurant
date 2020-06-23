package club.haozheng.restaurant.pojo;


import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Entity
@Table(name="order_goods")
public class OrderGoods {
    @Id
    private String id;
    private String orderId;
    private int goodsNum;
    private String goodsPrice;
    private String goodsName;
    private String goodsDescribe;
    private String goodsImage;
    private String goodsId;
    private int status;



    @Override
    public String toString() {
        return "OrderGoods{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", goodsNum=" + goodsNum +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDescribe='" + goodsDescribe + '\'' +
                ", goodsImage='" + goodsImage + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

}
