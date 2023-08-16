package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller     // @Controller를 붙여야 스프링 부트가 컨트롤러로 인식, 안 붙으면 그냥 자바 코드
public class HomeController {

    @GetMapping("/")    // 첫화면
    public String home(){
        return "home";  // templates/home.html이 호출됨
    }
}
