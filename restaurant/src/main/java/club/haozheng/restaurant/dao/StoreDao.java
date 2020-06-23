package club.haozheng.restaurant.dao;


import club.haozheng.restaurant.pojo.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDao extends JpaRepository<Store,String> {

    Store findAllByHostId(String id);

    Store getAllById(String id);


}
