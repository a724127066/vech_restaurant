package club.haozheng.restaurant.dao;

import club.haozheng.restaurant.pojo.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActiveDao extends JpaRepository<Active,String> {
    Active getById(String id);

    List getActiveByStoreIdOrderByIdDesc(String id);

    @Query(value = "select * from active where store_id = ? and active_is_active = 1 and active_time_end > now() and active_time_begin < now()",nativeQuery = true)
    List<Active> getActiveByUser(String id);
 }
