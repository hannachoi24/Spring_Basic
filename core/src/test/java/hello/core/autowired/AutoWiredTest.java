package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutoWiredTest {
    // 옵션 처리
    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // 스프링 빈 자동 등록
    }

    static class TestBean {
        
        // 호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) { // 여기서 Member은 스프링 빈이 아님
            System.out.println("noBean1 = " + noBean1);
        }
        
        // null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
        
        // Optional.empty 호출
        @Autowired
        public void setNoBean3(@Nullable Member noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
