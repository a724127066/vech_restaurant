package club.haozheng.restaurant.controlt;


import club.haozheng.restaurant.comom.Result;
import club.haozheng.restaurant.comom.StatusCode;
import club.haozheng.restaurant.comom.util.JWTUtil;
import club.haozheng.restaurant.pojo.*;
import club.haozheng.restaurant.service.AdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RestController
@CrossOrigin
public class AdminControlt {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/test")
    public Result test(){
        return new Result(true, StatusCode.ok,"测试成功");
    }

    @RequestMapping(value = "/addHost",method = RequestMethod.POST)
    public Result addHost(@RequestBody RestaurantHost restaurantHost){
        Result result;
        try {
            result = new Result(true,StatusCode.ok,"添加成功",adminService.addHost(restaurantHost));
        } catch (Exception e) {
           result = new Result(false,StatusCode.error,"添加失败,或账号重复");
           System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/addStore",method = RequestMethod.POST)
    public Result addStore(@RequestBody Store store,HttpServletRequest request){
        Result result;
        String adminId = (String) request.getAttribute("id");
        store.setHostId(adminId);
        try{
            result = new Result(true, StatusCode.ok, "添加成功", adminService.addStore(store));
        }catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误或重复添加店铺");
        }
        return result;
    }

    @RequestMapping(value = "/loginAdmin",method = RequestMethod.POST)
    public Result loginAdmin(@RequestBody RestaurantHost restaurantHost){
        Result result = adminService.LoginAdmin(restaurantHost);
        if (result.getFlag()){
            RestaurantHost rightRestaurantHost = (RestaurantHost)result.getData();
            HashMap data = new HashMap();
            data.put("token",jwtUtil.createJWt(restaurantHost.getId()+"","admin",rightRestaurantHost));
            data.put("admin",rightRestaurantHost);
            result.setData(data);
        }
        return result;
    }

    @RequestMapping(value = "/getStore",method = RequestMethod.GET)
    public Result getStore(HttpServletRequest request){
        Result result;
        try {
            String adminId = (String) request.getAttribute("id");
            result = new Result(true,StatusCode.ok,"获取成功",adminService.getStore(adminId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/editStore",method = RequestMethod.POST)
    public Result editStore(@RequestBody Store store,HttpServletRequest request){
        Result result;
        try {
            String adminId = (String) request.getAttribute("id");
            Store newStore = adminService.editStore(store,adminId);
            if (newStore == null){
                result = new Result(false,StatusCode.error,"店铺不存在或token错误");
            }else{
                result = new Result(true,StatusCode.ok,"更改成功",newStore);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = new Result(false,StatusCode.error,"出现错误，更改失败");
        }
        return result;
    }

    @RequestMapping(value = "/getGoods",method = RequestMethod.GET)
    public Result getGoods(HttpServletRequest request){
        Result result;
        try {
            result = new Result(true,StatusCode.ok,"获取成功",adminService.getGoods(this.getStoreId(request)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/addGoodsType",method = RequestMethod.POST)
    public Result addGoodsType(@RequestBody GoodsType goodsType,HttpServletRequest request){
        Result result;
        goodsType.setStoreId(this.getStoreId(request));
        try {
            result = new Result(true,StatusCode.ok,"添加成功",adminService.addGoodsType(goodsType));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/addGoods",method = RequestMethod.POST)
    public Result addGoodsType(@RequestBody Goods goods,HttpServletRequest request){
        Result result;
        goods.setStoreId(this.getStoreId(request));
        try {
            result = new Result(true,StatusCode.ok,"添加成功",adminService.addGoods(goods));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/deleteGoodsType",method = RequestMethod.GET)
    public Result deleteGoodsType(String goodsTypeId){
        Result result;
        try {
            result = new Result(true,StatusCode.ok,"删除成功",adminService.deleteGoodsType(goodsTypeId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/deleteGoods",method = RequestMethod.GET)
    public Result deleteGoods(String goodsId){
        Result result;
        try {
            result = new Result(true,StatusCode.ok,"删除成功",adminService.deleteGoods(goodsId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/editGoodsType",method = RequestMethod.POST)
    public Result editGoodsType(@RequestBody GoodsType goodsType,HttpServletRequest request){
        Result result;
        goodsType.setStoreId(this.getStoreId(request));
        try {
            GoodsType newGoodsType =  adminService.editGoodsType(goodsType);
            if (newGoodsType != null){
                result = new Result(true,StatusCode.ok,"更改成功",newGoodsType);
            }else{
                result = new Result(false,StatusCode.error,"不存在菜单");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/editGoods",method = RequestMethod.POST)
    public Result editGoods(@RequestBody Goods goods,HttpServletRequest request){
        Result result;
        goods.setStoreId(this.getStoreId(request));
        try {
            Goods newGoods =  adminService.editGoods(goods);
            if (newGoods != null){
                result = new Result(true,StatusCode.ok,"更改成功",newGoods);
            }else{
                result = new Result(false,StatusCode.error,"不存在菜品");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result =  new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/getOrderAdmin",method = RequestMethod.GET)
    public Result getOrderAdmin(HttpServletRequest request){
        Result result;
        try {
            String adminId = this.getStoreId(request);
            System.out.println(adminId);
            List list = adminService.getOrder(adminId);
            if (list.size() == 0){
                result = new Result(false,StatusCode.error,"订单不存在");
            }else{
                result = new Result(true,StatusCode.ok,"获取成功",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false,StatusCode.error,"出现错误");
        }
        return result;
    }

    @RequestMapping(value = "/deleteOrder",method = RequestMethod.GET)
    public Result deleteOrder(String id){
        Result result;
        try {
            adminService.deleteOrder(id);
            result = new Result(true,StatusCode.ok,"删除成功");
        } catch (Exception e) {
            result = new Result(false,StatusCode.error,"出现错误");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/editOrderGoodsStatus",method = RequestMethod.POST)
    public Result editOrderGoodsStatus(@RequestBody OrderGoods orderGoods){
        System.out.println(orderGoods);
        Result result;
        try {
            result = new Result(true,StatusCode.ok,"更改成功",adminService.editOrderGoods(orderGoods));
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false,StatusCode.error,"出现错误");
//            System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/editOrderStatus",method = RequestMethod.POST)
    public Result editOrderStatus(@RequestBody OrderUser orderUser){
        Result result;
        try {
            result = new Result(true,StatusCode.ok,"更改成功",adminService.editOrderStatus(orderUser.getId(),orderUser.getOrderStatus()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false,StatusCode.error,"出现错误");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/editAdminPassword",method = RequestMethod.POST)
    public Result editAdminPassword(@RequestBody RestaurantHost restaurantHost){
        Result result;
        try {
            adminService.editAdminPassword(restaurantHost.getPassword(),restaurantHost.getId());
            result = new Result(true,StatusCode.ok,"更改成功",adminService.editAdminPassword(restaurantHost.getPassword(),restaurantHost.getId()));
        } catch (Exception e) {
            result = new Result(false,StatusCode.error,"出现错误");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/addActive",method = RequestMethod.POST)
    public Result addActive(@RequestBody Active active,HttpServletRequest request){
        Result result;
        active.setStoreId(this.getStoreId(request));
        try {
            result = new Result(true,StatusCode.ok,"查找",adminService.addActive(active));

        } catch (Exception e) {
            result = new Result(false,StatusCode.error,"出现错误");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getActive",method = RequestMethod.GET)
    public Result getActive(HttpServletRequest request){
       return new  Result(true,StatusCode.ok,"获取成功",adminService.getActive(this.getStoreId(request)));
    }


    @RequestMapping(value = "/deleteActive",method = RequestMethod.GET)
    public Result deleteActive(String activeId,HttpServletRequest request){
        Result result;
        try {
            int status = adminService.deleteActive(activeId,this.getStoreId(request));
            System.out.println(status);
            if (status == 1){
                result = new Result(true,StatusCode.ok,"删除成功");
            } else if (status == 0){
                result = new Result(false,StatusCode.error,"token错误或活动不存在");
            }else{
                result = new Result(false,StatusCode.error,"删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false,StatusCode.error,"删除失败");
        }
        return result;
    }

    @RequestMapping(value = "/editActive",method = RequestMethod.POST)
    public Result editActive(@RequestBody Active active,HttpServletRequest request){
        Result result;
        try {
            System.out.println(active);
            Active newActive = adminService.editActive(active,this.getStoreId(request));
            if (newActive != null){
                result = new Result(true,StatusCode.ok,"更改成功",newActive);
            } else{
                result = new Result(false,StatusCode.error,"更改失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = new Result(false,StatusCode.error,"更改失败");
        }
        return result;
    }


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(@RequestParam("file") MultipartFile mulFile,HttpServletRequest request){
        String  UPLOAD_FOLDER = "";
        if (Objects.isNull(mulFile) || mulFile.isEmpty()) {//判断非空
            // logger.error("文件为空");
            return new Result(false,StatusCode.error,"文件为空");
        }
        try {
            String mulFileName = System.currentTimeMillis() + mulFile.getOriginalFilename();

            String fileName =  File.separator + mulFileName;

//            File file = new File("D:\\testImage" + fileName);

            File file = new File("/usr/local/nginx/html/upload" + fileName);

            file.getParentFile().mkdirs();

            mulFile.transferTo(file);

            return new Result(false,StatusCode.error,"上传成功","http://haozheng.club/upload"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,StatusCode.error,"后台出错");
        }
    }

    private static String getFileExtension(MultipartFile File) {
        String originalFileName = File.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));

    }

    public String getStoreId(HttpServletRequest request){
        String adminId = (String) request.getAttribute("id");
        return adminService.getStoreId(adminId);
    }


}
