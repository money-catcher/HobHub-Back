package MoneyCatcher.HobHub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    private String resourcePath = "/images/**";//view 에서 접근할경로
//    private String savePath = "http://13.236.203.189/i";//실제 파일저장경로. 이 경로를 위의 경로로 접근하겠다~
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler(resourcePath) //요청경로가 업로드 어쩌고인경우
//                .addResourceLocations(savePath); //실제 파일이 저장된 경로인 c://어쩌고에서 파일을 찾는다.
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
