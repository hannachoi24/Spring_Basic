package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 회원 찾기
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정 할인 정책

    // 단일 체계 원칙을 잘 구현하고 설계한 것 -> 만약 할인에 대한 변경이 있을 시 이 부분(할인쪽)만 수정하면 됨
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 조회
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

/**
주문 생성 요청이 오면, 먼저 회원 정보를 조회하고, 할인 정책에 회원과 아이템 가격을 넘김
 최종적으로 할인 된 가격을 받고 최종 생성된 주문을 반환
 MemoryMemberRepository와 FixDiscountPolicy 구현체로 생성해 사용하였음*/
