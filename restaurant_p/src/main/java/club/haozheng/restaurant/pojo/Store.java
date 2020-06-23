package club.haozheng.restaurant.pojo;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.crypto.Data;
import java.util.Date;

@Component
@Entity
@Table(name="store")
public class Store {
    @Id
    private String id;
    private String hostId;
    private String storeName;
    private int storeTableNum;
    private String storeDescribe;
    private String storeTxUrl;
    private String businessHoursBegin;
    private String businessHoursEnd;
    private int businessStatus;
    private int rate;
    private int sellCount;

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", hostId='" + hostId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeTableNum=" + storeTableNum +
                ", storeDescribe='" + storeDescribe + '\'' +
                ", storeTxUrl='" + storeTxUrl + '\'' +
                ", businessHoursBegin='" + businessHoursBegin + '\'' +
                ", businessHoursEnd='" + businessHoursEnd + '\'' +
                ", businessStatus=" + businessStatus +
                ", rate=" + rate +
                ", sellCount=" + sellCount +
                '}';
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public String getBusinessHoursBegin() {
        return businessHoursBegin;
    }

    public void setBusinessHoursBegin(String businessHoursBegin) {
        this.businessHoursBegin = businessHoursBegin;
    }

    public String getBusinessHoursEnd() {
        return businessHoursEnd;
    }

    public void setBusinessHoursEnd(String businessHoursEnd) {
        this.businessHoursEnd = businessHoursEnd;
    }

    public int getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(int businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreTableNum() {
        return storeTableNum;
    }

    public void setStoreTableNum(int storeTableNum) {
        this.storeTableNum = storeTableNum;
    }

    public String getStoreDescribe() {
        return storeDescribe;
    }

    public void setStoreDescribe(String storeDescribe) {
        this.storeDescribe = storeDescribe;
    }

    public String getStoreTxUrl() {
        return storeTxUrl;
    }

    public void setStoreTxUrl(String storeTxUrl) {
        this.storeTxUrl = storeTxUrl;
    }
}
