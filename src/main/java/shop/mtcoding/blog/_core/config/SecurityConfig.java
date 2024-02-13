package shop.mtcoding.blog._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration //컴퍼넌트 스캔 : 설정파일은 configuration을 붙여야함
public class SecurityConfig {
    //시큐리티를 위한 필터가 체인이 걸려있음 이 필터체인(묶여있는거) 수정할거임
    //컨트롤러, 컨포넌트 , 컨피그레이션, ,서비스
    @Bean
    public BCryptPasswordEncoder encoder(){ // IoC 등록, 시큐리티가 로그인할 때 어떤 해시로 만들었는지 알 수 있음
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignore(){//정적파일 무시하기
        return w -> w.ignoring().requestMatchers("/static/**", "/h2-console/**");
    }
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http.csrf(c->c.disable()); //정상적인 접근만 막을 수 있음

        http.authorizeHttpRequests(a -> {
            a.requestMatchers(RegexRequestMatcher.regexMatcher("/board/\\d++")).permitAll()
                    .requestMatchers("/user/**","/board/**").authenticated()
                    .anyRequest().permitAll();
        });

        http.formLogin(f -> {
            f.loginPage("/loginForm").loginProcessingUrl("/login") //시큐리티가 들고 있는 로그인을 사용 장점 최초의 로그인화면 커스텀마이즈를 할 예정
                    .defaultSuccessUrl("/") //성공하면 메인페이지로
                    .failureUrl("/loginForm"); //실패하면 로그인페이지로
        });

        return http.build();
    }
}
