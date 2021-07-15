package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();// 구현 객체 선택
    // Ctrl + Shift + Enter -> 문장 자동 완성

    /** 문제점이 존재
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
}

/*해야 되는 일
회원 도메인이 잘 실행이 되는지 테스트 코드 작성이 필요!
core/main/java/hello.core에 MemberApp 클래스 만들기
*/


