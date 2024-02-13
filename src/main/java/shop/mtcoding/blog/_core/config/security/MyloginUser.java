package shop.mtcoding.blog._core.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shop.mtcoding.blog.user.User;

import java.util.Collection;

//세션에 저장되는 오브젝트
@RequiredArgsConstructor
public class MyloginUser implements UserDetails {
    private final User user;
    public User getUser() { //세션유저를 저장하기전에 유저와 비교해서 인증을 함
        return user; //DB에서 조회된 패스워드값을 넣어야함
    }
    @Override
    public String getPassword() { //세션유저를 저장하기전에 유저와 비교해서 인증을 함
        return user.getPassword(); //DB에서 조회된 패스워드값을 넣어야함
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
