package club.haozheng.restaurant.dao;


import club.haozheng.restaurant.pojo.RestaurantHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantHostDao  extends JpaRepository<RestaurantHost,String> {

            RestaurantHost findAllByAccount(String account);

            RestaurantHost getById(String id);


}
