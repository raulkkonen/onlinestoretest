package academy.digitallab.store.shopping.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        final String authorization = HttpHeaders.AUTHORIZATION;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()){
            if (!template.headers().containsKey(authorization)) {
                System.out.println(authentication.getName());
                String currentToken = authentication.getCredentials().toString();
                System.out.println(currentToken);
                if (!StringUtils.isEmpty(currentToken)){
                    template.header(authorization,currentToken);

                }
            }
        }
    }
}
