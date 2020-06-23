package club.haozheng.restaurant.service;


import club.haozheng.restaurant.comom.IdWorker;
import club.haozheng.restaurant.comom.Result;
import club.haozheng.restaurant.comom.StatusCode;
import club.haozheng.restaurant.dao.KitchenAdminDao;
import club.haozheng.restaurant.pojo.KitchenAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class KitchenService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private int ttl = 60;

    @Autowired
    private KitchenAdminDao kitchenAdminDao;



    public KitchenAdmin addKitchen(KitchenAdmin kitchenAdmin){
        System.out.println(kitchenAdmin);
        String id = idWorker.nextId()+"";
        kitchenAdmin.setId(id);
        kitchenAdmin.setKitchenPassword(encoder.encode(kitchenAdmin.getKitchenPassword()));
        kitchenAdminDao.save(kitchenAdmin);
        KitchenAdmin k = kitchenAdminDao.findAllById(id);
        k.setKitchenPassword("0");
        return k;
    }

    public Result loginKitchenAdmin(KitchenAdmin kitchenAdmin){
        System.out.println(kitchenAdmin);
        KitchenAdmin right = kitchenAdminDao.findAllByKitchenAccount(kitchenAdmin.getKitchenAccount());
        if (right == null){
            return new Result(false, StatusCode.error,"账号不存在");
        }
        if ((String)redisTemplate.opsForValue().get("kitchen"+right.getId()) != null){//判断是否重新登录
            return  new Result(false, StatusCode.reSubmision,"重复登录");
        }
        if (encoder.matches(kitchenAdmin.getKitchenPassword(),right.getKitchenPassword())){
            right.setKitchenPassword("0");
            redisTemplate.opsForValue().set("kitchen"+right.getId(),right.toString());
            redisTemplate.expire("kitchen"+right.getId(),this.ttl, TimeUnit.SECONDS);
            return new Result(true,StatusCode.ok,"登录成功",right);
        }
        return new Result(false,StatusCode.noPower,"账号密码错误");
    }
}
