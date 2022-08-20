package academy.digitallab.store.security.domain.service;

import academy.digitallab.store.security.domain.error.exception.BusinessException;
import academy.digitallab.store.security.domain.error.exception.ResourceNotFoundException;
import academy.digitallab.store.security.domain.repository.AuthorityRepository;
import academy.digitallab.store.security.domain.repository.UserRepository;
import academy.digitallab.store.security.domain.repository.entity.Authority;
import academy.digitallab.store.security.domain.repository.entity.User;
import academy.digitallab.store.security.domain.utils.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDB = userRepository.findById(username).orElse(null);
        List<GrantedAuthority> authorities = buildAuthorities(userDB.getAuthorities());

        return buildUser(userDB,authorities);
    }
    private org.springframework.security.core.userdetails.User buildUser( User userDB, List<GrantedAuthority> authorities){

        return  new org.springframework.security.core.userdetails.User(userDB.getUsername(),userDB.getPassword(), userDB.isEnabled(),true,true,true,authorities);
    }

    private List<GrantedAuthority> buildAuthorities(List<Authority> authorities){
        Set<GrantedAuthority> auths=  new HashSet<>();
        for(Authority authority: authorities){
            auths.add(new SimpleGrantedAuthority(authority.getRole()));
        }
        return new ArrayList<>(auths);
    }


    @Secured("ROLE_ADMIN")
    public User createUser(User user){

        User userDB = null;
        try {
            //VALIDATE USER EXIST
            userDB = getUser(user.getUsername());
            if (null != userDB){
                new ResourceNotFoundException("user with username = " + user.getUsername() +" exist");
            }
            //VALIDATE EMAIL EXIST


            // GENERATE PASSWORD
            BCryptPasswordEncoder pe= new BCryptPasswordEncoder();
            user.setPassword(pe.encode(user.getPassword()));

            user.setStatus(Constant.STATE_CREATED);
            user.setEnabled(true);
            userDB = userRepository.save(user);

            //gerendo Authority
            Authority authority = Authority.builder()
                    .role(Constant.ROLE_USER)
                    .username(user.getUsername()).build();

            authorityRepository.save(authority);

        }catch (GenericJDBCException eq) {
            //throw new ServiceException(this, ResourceBundleUtil.getMessage(Constant.MENSAJE_ERROR_DATA_BD));
            throw new BusinessException("existe un error en la base de datos ");
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            throw new BusinessException(ex.getMessage());
        }
        finally{
            log.debug(getClass().getName() +"Fin save");
        }
        return userDB;
    }
    public User getUser(String username){
        return userRepository.findById(username)
                .orElse(null);
        //.orElseThrow(() -> new ResourceNotFoundException("Not found user with username = " + username));
    }
}
