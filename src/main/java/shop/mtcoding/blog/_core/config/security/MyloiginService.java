package shop.mtcoding.blog._core.config.security;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;

//Post, /login, x-www-form-urlencoded, 키값이 username, password
@RequiredArgsConstructor
@Service //내부에 컴포넌트가 있어 스캔됨, 기존에 있는 detailsService를 무력화시켜 Service가 떠있어서 무조건 loadUserByUsername 때려짐
public class MyloiginService implements UserDetailsService {//시큐리티 라이브러리를 달면 new가되서 IoC에 등록되있음
    private final UserRepository userRepository;
    private final HttpSession session;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //UserDetails 타입으로 반환해야함 왜? 세션을 저장해야하니깐 내가 저장안하고 라이브러리에서 저장해주니깐 타입을 맞춰줘야함 왜 패스워드를 안 받을까?

        System.out.println("loadUserByUsername = " + username);
        User user = userRepository.findByUsername(username);//시큐리티가 username밖에 안주니깐 그걸로 확인해야함

        if (user == null){
            System.out.println("user는 null");
            return null;
        }else{
            System.out.println("user를 찾았어요");
            session.setAttribute("sessionUser", user); //머스태치에서만 가져오자
            return new MyloginUser(user); //SecurityContextHolder 저장
        }
    }
}
