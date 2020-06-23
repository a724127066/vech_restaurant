package club.haozheng.restaurant.config;


import club.haozheng.restaurant.comom.Result;
import club.haozheng.restaurant.comom.StatusCode;
import club.haozheng.restaurant.comom.util.JWTUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

public class LogcostInterceptor implements HandlerInterceptor {

    private String key = "haozheng.club/vech";
    //preHandle是在请求执行前执行的

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("begin--------------------"+new Date()+"--------------------");
        System.out.println("\n");
        System.out.println("requestUrl:"+request.getRequestURI());
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
           return true;
        }
        String  token = request.getHeader("token");
        try {
            Claims claims = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
            String adminId =  (String)((HashMap)claims.get("data")).get("id");
            request.setAttribute("id",adminId);
            return true;
        } catch (Exception e) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(new Result(false, StatusCode.noPower,"权限不足")));
            System.out.println(e.getMessage());
        }
        return false;
        //返回true,postHandler和afterCompletion方法才能执行
        // 否则false为拒绝执行，起到拦截器控制作用
    }

    //postHandler是在请求结束之后,视图渲染之前执行的,但只有preHandle方法返回true的时候才会执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("\n");
        System.out.println("end--------------------"+new Date()+"--------------------");
    }

    //afterCompletion是视图渲染完成之后才执行,同样需要preHandle返回true，
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //该方法通常用于清理资源等工作
    }
}
