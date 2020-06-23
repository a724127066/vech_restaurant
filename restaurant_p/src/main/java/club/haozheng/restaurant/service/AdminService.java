package club.haozheng.restaurant.service;


import club.haozheng.restaurant.comom.IdWorker;
import club.haozheng.restaurant.comom.Result;
import club.haozheng.restaurant.comom.StatusCode;
import club.haozheng.restaurant.dao.*;
import club.haozheng.restaurant.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class AdminService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private int ttl = 60;

    @Autowired
    private RestaurantHostDao restaurantHostDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Autowired
    private ActiveDao activeDao;

    @Autowired
    private OrderGoodsDao orderGoodsDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private KitchenAdminDao kitchenAdminDao;


    public RestaurantHost addHost(RestaurantHost restaurantHost){
            String id = idWorker.nextId()+"";
            restaurantHost.setPassword(encoder.encode(restaurantHost.getPassword()));
            restaurantHost.setId(id);
            restaurantHostDao.save(restaurantHost);
            return restaurantHostDao.getById(id);
    }

    public Store addStore(Store store){
        System.out.println(store.getHostId());
        String id = idWorker.nextId()+"";
        store.setId(id);
        store.setRate(5);
        store.setSellCount(0);
        store.setBusinessStatus(1);
        storeDao.save(store);
        return storeDao.getAllById(id);
    }

    public Result LoginAdmin(RestaurantHost restaurantHost){
        RestaurantHost right = restaurantHostDao.findAllByAccount(restaurantHost.getAccount());
        if (right == null){
            return new Result(false, StatusCode.error,"账号不存在");
        }
        if ((String)redisTemplate.opsForValue().get("restaurantAdmin"+right.getId()) != null){//判断是否重新登录
            return  new Result(false, StatusCode.reSubmision,"重复登录");
        }
        if (encoder.matches(restaurantHost.getPassword(),right.getPassword())){
            right.setPassword("0");
            redisTemplate.opsForValue().set("restaurantAdmin"+right.getId(),right.toString());
            redisTemplate.expire("restaurantAdmin"+right.getId(),this.ttl, TimeUnit.SECONDS);
            return new Result(true,StatusCode.ok,"登录成功",right);
        }
        return new Result(false,StatusCode.noPower,"账号密码错误");
    }

    public Store getStore(String id){
        return  storeDao.findAllByHostId(id);
    }

    public Store editStore(Store store,String adminId){
        Store right = storeDao.getAllById(store.getId());
        if (right == null){
            System.out.println("更改店铺id不正确");
            return null;
        }
        if (adminId == right.getHostId() ){
            System.out.println("更改用户不符合");
            System.out.println(adminId);
            System.out.println(right.getHostId());
            return null;
        }
        storeDao.save(store);
        return storeDao.getAllById(store.getId());
    }

    public HashMap getGoods(String id){
        List<GoodsType> goodsTypeList = goodsTypeDao.getGoodsTypeByStoreId(id);
        List<Goods> goodsList = goodsDao.getGoodsByStoreId(id);
        HashMap hashMap = new HashMap();
        hashMap.put("goodsType",goodsTypeList);
        hashMap.put("goods",goodsList);
        return hashMap;
    }

    public GoodsType addGoodsType(GoodsType goodsType){
        String id = idWorker.nextId()+"";
        goodsType.setId(id);
        goodsTypeDao.save(goodsType);
        return goodsTypeDao.getGoodsTypeById(id);
    }

    public Goods addGoods(Goods goods){
        String id = idWorker.nextId()+"";
        goods.setGoodIsSell(1);
        goods.setGoodsSell(0);
        goods.setId(id);
        goodsDao.save(goods);
        return goodsDao.getGoodsById(id);
    }

    public boolean deleteGoodsType(String id){
        System.out.println(id);
        goodsDao.deleteAllByGoodsTypeId(id);
        goodsTypeDao.deleteById(id);
        return true;
    }

    public boolean deleteGoods(String id){
        goodsDao.deleteById(id);
        return true;
    }

    public GoodsType editGoodsType(GoodsType goodsType){
        GoodsType exitGoodsType = goodsTypeDao.getGoodsTypeById(goodsType.getId());
        if (exitGoodsType == null){
            return null;
        }
        goodsTypeDao.save(goodsType);
        return goodsTypeDao.getGoodsTypeById(goodsType.getId());
    }

    public Goods editGoods(Goods goods){
        Goods exitGoods = goodsDao.getGoodsById(goods.getId());
        if (exitGoods == null){
            return null;
        }
        goodsDao.save(goods);
        return goodsDao.getGoodsById(goods.getId());
    }

    public String getStoreId(String id){
        String rightId;
        Store store  = storeDao.findAllByHostId(id);
        System.out.println(store+"cccccccccccccccccccccc");
        if (store == null){
            rightId = kitchenAdminDao.findAllById(id).getStoreId();
            System.out.println(rightId+"aaaaaaaaaaaaaaa");
        }else{
            rightId = store.getId();
            System.out.println(rightId+"bbbbbbbbbbbbbbbb");
        }
        return rightId;
    }

    public List getOrder(String id){
        List list = new ArrayList();
        List<OrderUser> userList = orderDao.getOrderUserByStoreIdOrderByOrderPayTimeDesc(id);
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

    public void deleteOrder(String orderId){
        List<OrderGoods>orderGoodsList = orderGoodsDao.findAllByOrderId(orderId);
        for(OrderGoods orderGoods:orderGoodsList){
            orderGoodsDao.deleteById(orderGoods.getId());
        }
        orderDao.deleteById(orderId);
    }

    public OrderGoods editOrderGoods(OrderGoods orderGoods){
            System.out.println(orderGoods);
            OrderGoods rightOrderGoods = orderGoodsDao.getOrderGoodsById(orderGoods.getId());
            System.out.println(rightOrderGoods);
            rightOrderGoods.setStatus(orderGoods.getStatus());
            orderGoodsDao.save(rightOrderGoods);
            return rightOrderGoods;
    }

    public OrderUser editOrderStatus(String id,int status){
            OrderUser orderUser = orderDao.getOrderById(id);
            orderUser.setOrderStatus(status);
            orderDao.save(orderUser);
            return orderDao.getOrderById(id);
    }

    public RestaurantHost editAdminPassword(String password,String userId){
        RestaurantHost restaurantHost = restaurantHostDao.getById(userId);
        if (restaurantHost == null){
            return null;
        }
        restaurantHost.setPassword(encoder.encode(password));
        restaurantHostDao.save(restaurantHost);
        restaurantHost.setPassword("0");
        return restaurantHost;
    }

    public Active addActive(Active active){
         String id = idWorker.nextId()+"";
         active.setId(id);
         activeDao.save(active);
         return activeDao.getById(id);
    }

    public int deleteActive(String activeId,String storeId){
        Active right = activeDao.getById(activeId);
        System.out.println(activeId);
        if (right == null){
            return 0;
        }

        if (!storeId.equals(right.getStoreId())){
            System.out.println(storeId);
            return 0;
        }

        activeDao.deleteById(activeId);

        return 1;
    }

    public Active editActive(Active active,String storeId){
        System.out.println(active);
        Active right = activeDao.getById(active.getId());
        if (right == null){
            System.out.println(active);
            return null;
        }
        if (!right.getStoreId().equals(storeId)){
            System.out.println(right.getStoreId());
            return null;
        }
        activeDao.save(active);
        return activeDao.getById(active.getId());
    }

    public List getActive(String id){
        return activeDao.getActiveByStoreIdOrderByIdDesc(id);
    }



}
