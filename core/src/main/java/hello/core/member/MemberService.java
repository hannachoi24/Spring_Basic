package hello.core.member;

public interface MemberService {
    // 회원 가입
    void join(Member memeber);

    // 회원 조회
    Member findMember(Long memberId);
}
