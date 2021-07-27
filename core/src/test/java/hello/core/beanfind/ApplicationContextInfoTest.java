package hello.core.beanfind;

import hello.core.AppConfig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { // iter 입력하고 tab 누르면 for문 자동생성
            Object bean = ac.getBean(beanDefinitionName); // key-value라고 보면됨
            System.out.println("name = " + beanDefinitionName + "object = " + bean); // soutv 입력하고 tab 누르면 자동생성
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findAppaplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { 
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            
            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean);
            }
        }
    }
}
