package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // API는 View 이런 것 없이 데이터가 바로 전달된다.
    @GetMapping("hello-string")
    @ResponseBody   // http에서 응답 header의 body 부분에 직접 넣는다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        // 자바 빈 규약
        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
