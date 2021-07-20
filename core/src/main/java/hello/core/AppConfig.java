package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// Constructor based Injection (생성자를 통한 주입) => 외부에서 생성자를 만들어 주입
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
        // AppConfig를 통해서 MemberService를 불러서 사용 그러면 MemberServiceImpl 객체가 생성이 됨
        // 이거에 대한 참조값(MemoryMemberRepository)을 MemberServiceImpl에서 생성자 부분인 memberRepository에 넣어주게 됨
        // 즉 MemberServiceImpl을 만들고 만든 MemberServiceImpl은 MemoryMemberRepository를 사용해 주입할거야!
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // AppConfig를 통해서 OrderService를 조회하면 OrderServiceImpl이 반환이 되는데 거기에는 MemoryMemberRepository, FixDiscountPolicy가 들어감
        // 즉 OrderServiceImpl이 MemoryMemberRepository, FixDiscountPolicy를 참조하도록
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}

/**이전에는 MemberServiceImpl과 OrderServiceImpl이 직접 객체를 생성하고 여기에 어떤 인터페이스가 할당 되어야 하는지 구체적인 것 까지 직접 했었음
 ->ex) MemberServiceImpl이 MemoryMemberRepository를 직접 지정했음(배우가 직접 담당 배우를 섭외하는 것과도 같음)
 -> 해결 방법은 AppConfig에서 애플리케이션 환경 구성을 다 한다. (구체적(구체화)인 것 까지)*/

/**
 AppConfig 리팩터링을 해주면 역할과 구현이 한눈에 보인다. -> 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
 */