package club.haozheng.restaurant.pojo;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Entity
@Table(name="goods")
public class Goods {
    @Id
    private String id;
    private String storeId;
    private String goodsTypeId;
    private String goodsName;
    private int goodsSell;
    private String goodsDescribe;
    private String goodsImage;
    private double goodsPrice;
    private int goodsGood;
    private int goodIsSell;
    private int goodsStock;
    private int goodsStatus;






    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", storeId='" + storeId + '\'' +
                ", goodsTypeId='" + goodsTypeId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsSell=" + goodsSell +
                ", goodsDescribe='" + goodsDescribe + '\'' +
                ", goodsImage='" + goodsImage + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsGood=" + goodsGood +
                ", goodIsSell=" + goodIsSell +
                ", goodsStock=" + goodsStock +
                ", goodsStatus=" + goodsStatus +
                '}';
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }



    public int getGoodsGood() {
        return goodsGood;
    }

    public void setGoodsGood(int goodsGood) {
        this.goodsGood = goodsGood;
    }

    public int getGoodIsSell() {
        return goodIsSell;
    }

    public void setGoodIsSell(int goodIsSell) {
        this.goodIsSell = goodIsSell;
    }

    public int getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
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

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsSell() {
        return goodsSell;
    }

    public void setGoodsSell(int goodsSell) {
        this.goodsSell = goodsSell;
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
