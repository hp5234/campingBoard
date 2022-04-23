package newcamping.campingboard;

import newcamping.campingboard.filter.LoginCheckFilter;
import newcamping.campingboard.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 인터셉터 등록과정
    // WebMvcConfigurer 의 구현 메서드
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**") // 검사 대상
                .excludePathPatterns("/", "/members/new", "/login", "/logout", "/css/**",
                        "/*.ico","/error", "/js/*", "/notice/list/*","/notice/{id}", "/members/check"); // 검사 제외
    }

    // 필터 등록 과정
    // @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter()); // 필터  설정
        filterRegistrationBean.setOrder(1); // 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); // 필터 url "/*" 의 경우 모든 url 에 적용

        return filterRegistrationBean;
    }


}
