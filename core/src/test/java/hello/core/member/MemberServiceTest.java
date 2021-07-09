package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member); // join을 했을 때
        Member findMember =memberService.findMember(1L); // 있는지 확인

        // then
        Assertions.assertThat(member).isEqualTo(findMember); // findMember와 똑같은지
    }
}
