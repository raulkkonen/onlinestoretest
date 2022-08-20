package academy.digitallab.store.shopping.config;




import academy.digitallab.store.shopping.auth.JWTAuthenticationFilter;
import academy.digitallab.store.shopping.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RedisService redisService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/*").permitAll()
                .anyRequest().authenticated()
                .and()
                        // Filtar las rutas JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(redisService),
                        UsernamePasswordAuthenticationFilter.class);

    }
}