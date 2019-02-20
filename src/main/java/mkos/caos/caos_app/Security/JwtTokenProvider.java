package mkos.caos.caos_app.Security;

import io.jsonwebtoken.*;
import mkos.caos.caos_app.Model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static mkos.caos.caos_app.Security.Constants.EXPIRATION_TIME;
import static mkos.caos.caos_app.Security.Constants.SECRET;


@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication){
        User userPrincipal= (User) authentication.getPrincipal();
        Date time=new Date(System.currentTimeMillis());
        Date expiration=new Date(time.getTime()+EXPIRATION_TIME);
        String userID=Long.toString(userPrincipal.getId());
        Map<String,Object>claims=new HashMap<>();
        claims.put("id",(Long.toString(userPrincipal.getId())));
        claims.put("username", userPrincipal.getUsername());


        return Jwts.builder()
                .setSubject(userID)
                .setClaims(claims)
                .setIssuedAt(time)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("invalid jwt signature");
        }catch (MalformedJwtException ex){
            System.out.println("invalid jwt token");
        }catch (UnsupportedJwtException ex){
            System.out.println("unsupported jwt token");
        }catch (ExpiredJwtException ex){
            System.out.println("expired jwt token");
        }catch (IllegalArgumentException ex){
            System.out.println("jwt claims string is empty");
        }
        return false;
    }

    Long getUserIdFromJWTtoken(String token){
        Claims claims=Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id=(String)claims.get("id");
        return Long.parseLong(id);
    }
}
