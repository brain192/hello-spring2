package hello.hello_spring2;

import hello.hello_spring2.service.MemberDetailsService2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig2 {
    private final MemberDetailsService2 memberDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // CSRF 보안 비활성화 (테스트용)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/css/**").permitAll() // 회원가입/로그인은 모두 허용
                        .anyRequest().authenticated()  // 나머지는 로그인 필요
                )
                .formLogin(form -> form
                        .loginPage("/login")              // 커스텀 로그인 페이지 경로
                        .defaultSuccessUrl("/board/list", true)  // 로그인 성공 시 이동할 페이지
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")      // 로그아웃 성공 후 이동 경로
                        .invalidateHttpSession(true)
                );

        return http.build();
    }

    /**
     * 비밀번호 암호화를 위한 Bean.
     * BCrypt를 이용해 안전한 암호화 수행.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
