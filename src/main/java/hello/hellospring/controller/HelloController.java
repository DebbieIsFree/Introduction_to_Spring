package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    // (MVC) 모델, 뷰, 컨트롤러
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");  // key-value
        return "hello";     // templates 폴더에 있는 hello.html로 렌더링 된다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";    // hello-template로 이동
    }
}
