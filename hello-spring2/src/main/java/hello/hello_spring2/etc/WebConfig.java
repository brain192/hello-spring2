package hello.hello_spring2.etc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/boards/**") // 게시판 관련 페이지에만 적용
                .excludePathPatterns("/auth/**","/"); // 로그인/회원가입 페이지는 제외
    }

     */


}