package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// 스프링 없이 자바로 테스트 하는 코드
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        // 외부에서 주입 (Di, Dependency Injection) -> 같은 메모리 객체 사용 가능
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    // 테스트 코드의 메서드 이름은 한글을 사용하여 직관적이게 해도 된다.
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // ()-> 로직을 실행할 때, IllegalStateException 예외가 발생해야 한다.
        IllegalStateException e =
                assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

       /* try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){   // exception이 발생해서 catch문에 들어오면 성공
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}