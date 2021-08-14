package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 자동 스프링 빈 등록
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; // 밖에서(AppConfig) 생성을해 주입(Constructor based Injection)해주므로 추상화(인터페이스)에만 의존하게 됨 -> DIP 지킴(즉, 구체화에 대해서는 모르게 됨)
    // 생성자를 통해 MemoryMemberRepository가 들어와서 할당이됨

    // private final MemberRepository memberRepository = new MemoryMemberRepository(); -> 구현 객체 선택

    @Autowired // @Component를 하게되면 의존관계주입을 해줄 수 없게됨(수동으로 등록할 수 있는 곳이 없어서)
               // 그래서 @Autowired를 붙여 MemberRepository 타입에 맞는 스프링 빈을 찾아와 의존관계를 자동으로 연결해 주입
               // 마치 ac.getBean(MemberRepository.class)와 같이 동작
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 생성자를 통해서 memberRepository에 어떤 구현체(MemoryMemberRepository)가 들어가는지 결정
    // 생성자를 통해서 MemoryMemberRepository가 넘어가고 위에 할당이 된다.


    // Ctrl + Shift + Enter -> 문장 자동 완성

    /** 문제점이 존재
     private final MemberRepository memberRepository = new MemoryMemberRepository(); -> 구현 객체 선택
     위의 MemberRepository는 인터페이스를 잘 의존하고 있음 그런데 할당하는 부분인 MemoryMemberRepository는 구현체에 의존
     즉, MemberServiceImpl은 추상화, 구체화 둘 다에 의존하고 있음 -> 나중에 변경이 있을 때 매우 안좋음(DIP 위반)
     해결방법은 다음 주문과 할인 도메인 설계 때 알아보도록함
     */

    @Override
    public void join(Member memeber) {
        memberRepository.save(memeber); // join에서 save를 호출하면 다형성에 의해 MemoryMemberRepository에 있는 save가 호출됨
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

/*해야 되는 일
회원 도메인이 잘 실행이 되는지 테스트 코드 작성이 필요!
core/main/java/hello.core에 MemberApp 클래스 만들기
*/



