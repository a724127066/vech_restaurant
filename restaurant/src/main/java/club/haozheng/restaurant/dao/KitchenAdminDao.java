package club.haozheng.restaurant.dao;


import club.haozheng.restaurant.pojo.KitchenAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KitchenAdminDao extends JpaRepository<KitchenAdmin,String> {

    KitchenAdmin findAllByKitchenAccount(String account);

    KitchenAdmin findAllById(String id);

}
