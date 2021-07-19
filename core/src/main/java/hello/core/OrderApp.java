package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) { // psvm 입력하고 Enter침
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L; // 멤버를 먼저 저장해야되기 때문에 첫번 째로 작성해줌
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member); // memberService를 통해 메모리 객체에 넣어야함 그래야 주문해서 찾아 쓸 수 있기 때문

        Order order = orderService.createOrder(memberId, "itemA",10000);

        System.out.println("order = " + order);

    }
}

/*
위는 1차원적인 테스트 방법
JUnit을 사용해 test 해보자 -> core/test/java/hello.core에 order 패키지 생성 order패키지에 OrderServiceTest클래스 파일 생성*/