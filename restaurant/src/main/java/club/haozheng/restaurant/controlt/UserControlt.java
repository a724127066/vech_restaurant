package club.haozheng.restaurant.controlt;


import club.haozheng.restaurant.comom.Result;
import club.haozheng.restaurant.comom.StatusCode;
import club.haozheng.restaurant.comom.util.JWTUtil;
import club.haozheng.restaurant.pojo.*;
import club.haozheng.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserControlt {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;




    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Result addUser(@RequestBody User user){
        Result result;
        try {
            User newUser = userService.addUser(user);
            if (newUser == null){
                result = new Result(false,StatusCode.reSubmision,"账号重复");
            }else{
                result = new Result(true,StatusCode.ok,"添加成功",user);
            }
        } catch (Exception e) {
            result = new Result(false,StatusCode.error,"出现错误");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public Result loginUser(@RequestBody User user){
        Result result = userService.loginUser(user);
        if (result.getFlag()){
            User rightUser = (User) result.getData();
            HashMap data = new HashMap();
            data.put("token",jwtUtil.createJWt(user.getId()+"","user",rightUser));
            data.put("user",rightUser);
            result.setData(data);
        }
        return result;
    }

    @RequestMapping(value = "/test")
    public Result test(){
        return new Result(true, StatusCode.ok,"测试成功");
    }

    @RequestMapping(value = "/getStoreList",method = RequestMethod.GET)
    public Result getStoreList(){
        return new Result(true,StatusCode.ok,"获取成功",userService.getStoreList());
    }

    @RequestMapping(value = "/getStore",method = RequestMethod.GET)
    public Result getStore(String id){
        Result result;
        try {
            Store store = userService.getStore(id);
            if (store == null){
                result = new Result(false,StatusCode.noExist,"id不存在");
            }else{
                result = new Result(true,StatusCode.ok,"获取成功",store);
            }
        } catch (Exception e) {
            result = new Result(false,StatusCode.error,"获取失败");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getGoods",method = RequestMethod.GET)
    public Result getGoods(String id){
        Result result;
        try {
            HashMap hashMap = userService.getGoods(id);
            if ( hashMap.get("code") == "1"){
                result = new Result(true,StatusCode.ok,"获取成功",hashMap);
            }else if( hashMap.get("code") == "0"){
                result = new Result(false,StatusCode.noExist,"id不存在或商家无菜单");
            }else{
                result = new Result(false,StatusCode.error,"出现错误");
            }
        } catch (Exception e) {
            result = new Result(false,StatusCode.error,"出现错误");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getActiveUser",method = RequestMethod.GET)
    public Result getActiveUser(String id){
        List<Active> right = userService.getActive(id);
        return new Result(true,StatusCode.ok,"获取成功",right);
    }

    @RequestMapping(value = "/order/addOrder",method = RequestMethod.POST)
    public Result addOrder(@RequestBody OrderTemp orderTemp ){
        Result result;
        try {
            userService.addOrder(orderTemp.getOrderGoods(),orderTemp.getOrderUser());
            result = new Result(true,StatusCode.ok,"添加订单成功");
        } catch (Exception e) {
            result = new Result(false,StatusCode.error,"出现错误");
           // System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/order/getOrderUser",method = RequestMethod.GET)
    public Result getOrder(HttpServletRequest request,String storeId){
        Result result;
        try {
            String id = (String) request.getAttribute("id");
            System.out.println(id);
            List list = userService.getOrder(id,storeId);
            if (list.size() == 0){
                result = new Result(false,StatusCode.error,"订单不存在");
            }else{
                result = new Result(true,StatusCode.ok,"获取成功",list);
            }
        } catch (Exception e) {
            result = new Result(false,StatusCode.error,"出现错误");
            System.out.println(e.getMessage());
        }
        return result;
    }







}
