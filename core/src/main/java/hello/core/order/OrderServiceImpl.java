package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 자동 스프링 빈 등록
public class OrderServiceImpl implements OrderService{
    // final이면 기본으로 또는 생성자를 통해 할당 되어야 한다.
    // private final MemberRepository memberRepository; // 회원 찾기
    // private final DiscountPolicy discountPolicy; // 밖에서(AppConfig) 생성을해 주입(Constructor based Injection)해주므로 추상화(인터페이스)에만 의존하게 됨 -> DIP 지킴(즉, 구체화에 대해서는 모르게 됨)
    // 생성자를 통해 MemoryMemberRepository, FixDiscountPolicy가 할당됨

    // 다양한 의존관계 주입 방법_수정자 주입(setter 주입)
    private MemberRepository memberRepository; // 회원 찾기
    private DiscountPolicy discountPolicy;

    // private final DiscountPolicy discountPolicy;
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); 회원 찾기
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // -> OrderServiceImpl이 직접 객체를 생성하고 구체적인(RateDiscountPolicy) 것 까지 선택 -> 관심사 분리 필요

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    // 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다. 물론 스프링 빈에만 해당
    // @Autowired
    // @Component를 하게되면 의존관계주입을 해줄 수 없게됨(수동으로 등록할 수 있는 곳이 없어서)
    // 그래서 @Autowired를 붙여 MemberRepository 타입에 맞는 스프링 빈을 찾아와 의존관계를 자동으로 연결해 주입
    // 마치 ac.getBean(MemberRepository.class)와 같이 동작
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // 생성자를 통해서 MemoryMemberRepository, FixDiscountPolicy가 넘어가고 위에 할당이 된다.



    // 단일 체계 원칙을 잘 구현하고 설계한 것 -> 만약 할인에 대한 변경이 있을 이 부분(할인쪽)만 수정하면 됨
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 조회
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

/**
주문 생성 요청이 오면, 먼저 회원 정보를 조회하고, 할인 정책에 회원과 아이템 가격을 넘김
 최종적으로 할인 된 가격을 받고 최종 생성된 주문을 반환
 MemoryMemberRepository와 FixDiscountPolicy 구현체로 생성해 사용하였음*/
