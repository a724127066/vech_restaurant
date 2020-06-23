package club.haozheng.restaurant.controlt;


import club.haozheng.restaurant.comom.Result;
import club.haozheng.restaurant.comom.StatusCode;
import club.haozheng.restaurant.comom.util.JWTUtil;
import club.haozheng.restaurant.pojo.KitchenAdmin;
import club.haozheng.restaurant.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/admin/kitchen")
@RestController
@CrossOrigin
public class KitchenControlt {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private KitchenService kitchenService;

    @RequestMapping(value = "/addKitchen",method = RequestMethod.POST)
    public Result addKitchen(@RequestBody KitchenAdmin kitchenAdmin){
        Result result;
        try {
            KitchenAdmin newKitchen = kitchenService.addKitchen(kitchenAdmin);
            result = new Result(true, StatusCode.ok,"注册成功",newKitchen);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = new Result(false, StatusCode.error,"数据错误或账号重复");
        }
        return result;
    }

    @RequestMapping(value = "/loginKitchenAdmin",method = RequestMethod.POST)
    public Result loginAdmin(@RequestBody KitchenAdmin kitchenAdmin){
        Result result = kitchenService.loginKitchenAdmin(kitchenAdmin);
        if (result.getFlag()){
            KitchenAdmin right = (KitchenAdmin)result.getData();
            HashMap data = new HashMap();
            data.put("token",jwtUtil.createJWt(right.getId()+"","kitchen",right));
            data.put("kitchen",right);
            result.setData(data);
        }
        return result;
    }



}
