package club.haozheng.restaurant.service;


import club.haozheng.restaurant.comom.IdWorker;
import club.haozheng.restaurant.comom.Result;
import club.haozheng.restaurant.comom.StatusCode;
import club.haozheng.restaurant.dao.*;
import club.haozheng.restaurant.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static club.haozheng.restaurant.comom.util.objectToMap.objectToMap;

@Service
public class UserService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private int ttl = 60;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderGoodsDao orderGoodsDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ActiveDao activeDao;

    public User addUser(User user){
        System.out.println(user);
        User user1 = userDao.getUserByAccount(user.getAccount());
        if (user1 != null){
            return null;
        }
        String id = idWorker.nextId()+"";
        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        user.setPassword("0");
        return userDao.getUserById(id);
    }

    public Result loginUser(User user){
        User rightUser = userDao.getUserByAccount(user.getAccount());
        if (rightUser == null){
           return new Result(false, StatusCode.noExist,"账号不存在");
        }
        if ((String)redisTemplate.opsForValue().get("restaurantUser"+rightUser.getId()) != null){
            return  new Result(false, StatusCode.reSubmision,"重复登录");
        }
        if (encoder.matches(user.getPassword(),rightUser.getPassword())){
            rightUser.setPassword("0");
            redisTemplate.opsForValue().set("restaurantUser"+rightUser.getId(),rightUser.toString());
            redisTemplate.expire("restaurantUser"+rightUser.getId(),this.ttl, TimeUnit.SECONDS);
            return new Result(true,StatusCode.ok,"登录成功",rightUser);
        }
        return new Result(false,StatusCode.error,"账号或密码错误");
    }

    public List<Store>getStoreList(){
        return storeDao.findAll();
    }

    public Store getStore(String id){
        return storeDao.getAllById(id);
    }

    public HashMap getGoods(String id){
        HashMap<String,Object> hashMap = new HashMap();
        List<GoodsType> goodsTypeList = goodsTypeDao.getGoodsTypeByStoreId(id);
        if (goodsTypeList.size() == 0){
            hashMap.put("code","0");
        }else{
            List goodsList = new ArrayList();
            Map goodsMap = new HashMap();
            for (GoodsType goodsType: goodsTypeList) {
                goodsMap = objectToMap(goodsType);
                goodsMap.put("goods",goodsDao.getAllByGoodsTypeId(goodsType.getId()));
                goodsList.add(goodsMap);
            }
            hashMap.put("goodsList",goodsList);
            hashMap.put("code","1");
        }
        return hashMap;
    }

    public void addOrder(List<OrderGoods> goodsList, OrderUser orderUser){
        String orderId = idWorker.nextId()+"";
        orderUser.setId(orderId);
        orderUser.setOrderStatus(1);
        orderUser.setOrderTimeBegin(new Date());
        orderUser.setOrderIsPay(1);
        orderUser.setOrderPayTime(new Date());
        orderDao.save(orderUser);
        for (OrderGoods orderGoods : goodsList) {
            orderGoods.setGoodsId(orderGoods.getId());
            orderGoods.setId(idWorker.nextId() + "");
            orderGoods.setStatus(1);
            orderGoods.setOrderId(orderId);
            orderGoodsDao.save(orderGoods);
        }
    }

    public List getOrder(String userId,String storeId){
        List list = new ArrayList();
        List<OrderUser> userList = orderDao.getOrderUserByUserIdAndStoreIdOrderByOrderTimeBeginDesc(userId,storeId);
        if (userList == null){
            return null;
        }
        for (OrderUser orderUser : userList) {
            HashMap temp = new HashMap();
            temp.put("order", orderUser);
            List orderGoodsList = orderGoodsDao.findAllByOrderId(orderUser.getId());
            temp.put("orderGoodsList",orderGoodsList);
            list.add(temp);
        }
        return list;
    }

    public List<Active> getActive(String id){
        return activeDao.getActiveByUser(id);
    }



}
