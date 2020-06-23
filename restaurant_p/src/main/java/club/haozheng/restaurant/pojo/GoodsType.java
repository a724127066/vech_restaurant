package club.haozheng.restaurant.pojo;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Entity
@Table(name="goods_type")
public class GoodsType {

    @Id
    private String id;
    private String goodsType;
    private String storeId;

    @Override
    public String toString() {
        return "GoodsType{" +
                "id='" + id + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", storeId='" + storeId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
