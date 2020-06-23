package club.haozheng.restaurant.dao;


import club.haozheng.restaurant.pojo.OrderGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderGoodsDao  extends JpaRepository<OrderGoods,String> {

    OrderGoods getOrderGoodsById(String id);

    List findAllByOrderId(String id);

}
