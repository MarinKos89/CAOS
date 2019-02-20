package mkos.caos.caos_app.Security;


import mkos.caos.caos_app.Model.User;
import mkos.caos.caos_app.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static mkos.caos.caos_app.Security.Constants.HEADER_STRING;
import static mkos.caos.caos_app.Security.Constants.TOKEN_PREFIX;

public class JwtAuthFilter extends OncePerRequestFilter {


    @Autowired
    private  JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService detailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt=getJWT(httpServletRequest);
            if (StringUtils.hasText(jwt)&&jwtTokenProvider.validateToken(jwt)){
                Long userID=jwtTokenProvider.getUserIdFromJWTtoken(jwt);
                User details=detailsService.loadUserById(userID);

                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(details,null, Collections.emptyList());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception ex){
            logger.error("couldn't set user authentication");
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String getJWT(HttpServletRequest httpServletRequest) {
        String bearer=httpServletRequest.getHeader(HEADER_STRING);
        if(StringUtils.hasText(bearer)&&bearer.startsWith(TOKEN_PREFIX)){
            return bearer.substring(7);
        }
        return null;
    }
}
