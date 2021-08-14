package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @ComponentScan: @Component 어노테이션이 붙은 클래스를 찾아서 다 자동으로 스프링 빈에 등록 해준다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // AppConfig는 수동으로 등록해준거라 빼고 등록해야 충돌이 나지 않음

public class AutoAppConfig {
}
