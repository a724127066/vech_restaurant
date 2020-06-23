package club.haozheng.restaurant.dao;


import club.haozheng.restaurant.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao  extends JpaRepository<User,String> {

    User getUserById(String id);

    User getUserByAccount(String account);

}
