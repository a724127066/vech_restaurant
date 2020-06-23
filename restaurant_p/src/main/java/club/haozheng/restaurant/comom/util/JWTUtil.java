

package club.haozheng.restaurant.comom.util;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;



public class JWTUtil {

    private String key = "haozheng.club/vech";

    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 1;



    public String createJWt(String id, String subject, Object data){
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();  JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(iatDate)
                .signWith(SignatureAlgorithm.HS256,key)
                .claim("data",data);
        builder.setExpiration(expiresDate);

        return builder.compact();
    }



    public Claims parseJWT(String jwtStr){
        return Jwts.parser()
                .setSigningKey(this.key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }



}
