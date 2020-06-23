package club.haozheng.restaurant.dao;


import club.haozheng.restaurant.pojo.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDao extends JpaRepository<OrderUser,String> {

    OrderUser getOrderById(String s);

    List<OrderUser> getOrderUserByUserIdAndStoreIdOrderByOrderTimeBeginDesc(String userId,String storeId);

    List<OrderUser> getOrderUserByStoreIdOrderByOrderPayTimeDesc(String id);
}
