package club.haozheng.restaurant.pojo;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Component
@Entity
@Table(name="kitchen_admin")
public class KitchenAdmin {
    @Id
    private String id;
    private String kitchenAccount;
    private String kitchenPassword;
    private String storeId;
    private String kitchenName;


    @Override
    public String toString() {
        return "KitchenAdmin{" +
                "id='" + id + '\'' +
                ", kitchenAccount='" + kitchenAccount + '\'' +
                ", kitchenPassword='" + kitchenPassword + '\'' +
                ", storeId='" + storeId + '\'' +
                ", kitchenName='" + kitchenName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKitchenAccount() {
        return kitchenAccount;
    }

    public void setKitchenAccount(String kitchenAccount) {
        this.kitchenAccount = kitchenAccount;
    }

    public String getKitchenPassword() {
        return kitchenPassword;
    }

    public void setKitchenPassword(String kitchenPassword) {
        this.kitchenPassword = kitchenPassword;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getKitchenName() {
        return kitchenName;
    }

    public void setKitchenName(String kitchenName) {
        this.kitchenName = kitchenName;
    }
}
