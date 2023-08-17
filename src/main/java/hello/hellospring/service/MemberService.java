package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service    // 컨테이너에 등록된다.
//@Component
// 서비스 계층에 Transaction 추가
// JPA 사용 시, 필수 > JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행
@Transactional
public class MemberService {

    // new MemberRepository() -> 매번 다른 인스턴스 객체 생성, memberRepository 공유 안됨
    private final MemberRepository memberRepository;

    // 직접 생성이 아닌, 외부에서 주입하도록 변경
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 회원 가입 */
    public Long join(Member member){

        validateDuplicateMember(member);  // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())   // Optional 타입
            .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        // 중복 이름 X
        // Optional 타입의 값이 있으면 -> ifPresent

        //  Optional<Member> result = memberRepository.findByName(member.getName());
        // result.ifPresent(m -> {
        //     throw new IllegalStateException("이미 존재하는 회원입니다.");
        // });
    }

    /** 전체 회원 조회*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
