package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")     // 보통 인터넷에 url 치고 들어가는 것 (조회할 때)
    public String createForm(){
        return "members/createMemberForm";  // url로 /members/new를 쳤을 때, templates에서 매핑됨
    }

    @PostMapping("/members/new")    // 보통 데이터를 등록할 때 사용
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";    // 회원 가입이 끝나면 home 화면으로 보내기
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        // Model을 이용해서 View에 데이터 넘겨주기
        model.addAttribute("members", members);     // key = members
        return "members/memberList";
    }
}
