package academy.digitallab.store.customer.auth;



import academy.digitallab.store.customer.service.RedisService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {


    private RedisService redisService;

    public JWTAuthenticationFilter( RedisService redisService){
        this.redisService =redisService;
    }


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest) request);

        String token = TokenAuthenticationService.getToken((HttpServletRequest) request);
        if(token != null){
            if(this.redisService.existToken(token)){
                authentication = null;
            }
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }

}
