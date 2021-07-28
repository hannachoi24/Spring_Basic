package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class); // getBean(빈이름, 타입)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // memberService가 MemberServiceImpl의 객체이면 성공
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); // getBean(빈이름, 타입)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // memberService가 MemberServiceImpl의 객체이면 성공
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class); // getBean(빈이름, 타입)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // memberService가 MemberServiceImpl의 객체이면 성공
    } // 구체에 의존하기 때문에 별로 좋지않은 코드

    // 실패 테스트 케이스 (무조건 예외가 터져야 성공)
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanNameX() {
        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
        // 화살표 오른쪽 로직을 실행하면 화살표 왼쪽이 예외가 터져야함
    }

}
