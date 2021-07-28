package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) { // psvm 입력하고 Enter침
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService(); // appConfig에 memberService 달라고 하면 memberService 인터페이스를 넘겨줌 memberService에는 MemberServiceImpl이 담김
        // MemberService memberService = new MemberServiceImpl();
        
        // 스프링 컨테이너 적용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // ApplicationContext를 스프링 컨테이너로 생각하면 됨 -> 모든 객체들(AppConfig에서 @Bean부분)을 관리해줌
        // 즉, 스프링이 AppConfig에 있는 환경설정 정보들(객체 생성한 것들)을 다 스프링 컨테이너에 넣어서 관리해준다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // (객체 이름(보통 메소드이름), 반환타입)
        // 스프링 컨테이너를 통해서 찾아온다.

        Member member = new Member(1L, "memberA", Grade.VIP); // Ctrl + Alt + V(Extract Variable) 눌러서 member 변수 꺼내옴
                                                                       // 1L: Long 타입이어서 뒤에 L을 붙여줘야함
        memberService.join(member);

        // 제대로 가입이 되는지 확인
        Member findMember = memberService.findMember(1L); // Ctrl + Alt + V(Extract Variable) 눌러서 findMember 변수 꺼내옴
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}

/*
위는 1차원적인 테스트 방법
JUnit을 사용해 test 해보자 -> core/test/java/hello.core에 member 패키지 생성 member패키지에 MemberServiceTest클래스 파일 생성*/
