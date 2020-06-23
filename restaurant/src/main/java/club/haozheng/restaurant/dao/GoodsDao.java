package club.haozheng.restaurant.dao;

import club.haozheng.restaurant.pojo.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface GoodsDao extends JpaRepository<Goods,String> {
    List getGoodsByStoreId(String id);

    Goods getGoodsById(String id);

    List<Goods>getAllByGoodsTypeId(String id);

    @Transactional
    void deleteAllByGoodsTypeId(String id);
}
