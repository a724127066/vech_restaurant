package club.haozheng.restaurant.dao;


import club.haozheng.restaurant.pojo.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsTypeDao extends JpaRepository<GoodsType,String> {

    List<GoodsType> getGoodsTypeByStoreId(String id);

    GoodsType getGoodsTypeById(String id);

}
