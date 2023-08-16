package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 자바 코드로 스프링 빈 직접 등록
public class SpringConfig {

    // 스프링 빈 등록
    @Bean
    public MemberService memberService(){
        // 이 로직을 호출해서 스프링 빈에 등록
        // 스프링 빈에 등록된 memberRepository를 MemberService에 의존관계 주입한다.
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){  // MemberRepository는 인터페이스
        return new MemoryMemberRepository();     // MemoryMemberRepository는 구현체
    }
}
