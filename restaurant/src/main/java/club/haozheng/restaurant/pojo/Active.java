package club.haozheng.restaurant.pojo;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Component
@Entity
@Table(name="active")
public class Active {
    @Id
    private String id;
    private int activeType;
    private double activePrice;
    private String activeContent;
    private Date activeTimeBegin;
    private Date activeTimeEnd;
    private String storeId;
    private String activeIsActive;

    @Override
    public String toString() {
        return "Active{" +
                "id='" + id + '\'' +
                ", activeType=" + activeType +
                ", activePrice=" + activePrice +
                ", activeContent='" + activeContent + '\'' +
                ", activeTimeBegin=" + activeTimeBegin +
                ", activeTimeEnd=" + activeTimeEnd +
                ", storeId='" + storeId + '\'' +
                ", activeIsActive='" + activeIsActive + '\'' +
                '}';
    }

    public String getActiveIsActive() {
        return activeIsActive;
    }

    public void setActiveIsActive(String activeIsActive) {
        this.activeIsActive = activeIsActive;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getActiveType() {
        return activeType;
    }

    public void setActiveType(int activeType) {
        this.activeType = activeType;
    }

    public double getActivePrice() {
        return activePrice;
    }

    public void setActivePrice(double activePrice) {
        this.activePrice = activePrice;
    }

    public String getActiveContent() {
        return activeContent;
    }

    public void setActiveContent(String activeContent) {
        this.activeContent = activeContent;
    }

    public Date getActiveTimeBegin() {
        return activeTimeBegin;
    }

    public void setActiveTimeBegin(Date activeTimeBegin) {
        this.activeTimeBegin = activeTimeBegin;
    }

    public Date getActiveTimeEnd() {
        return activeTimeEnd;
    }

    public void setActiveTimeEnd(Date activeTimeEnd) {
        this.activeTimeEnd = activeTimeEnd;
    }
}
