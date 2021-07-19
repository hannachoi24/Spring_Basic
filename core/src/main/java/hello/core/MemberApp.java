package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) { // psvm 입력하고 Enter침
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); // appConfig에 memberService 달라고 하면 memberService 인터페이스를 넘겨줌 memberService에는 MemberServiceImpl이 담김
        // MemberService memberService = new MemberServiceImpl();
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
