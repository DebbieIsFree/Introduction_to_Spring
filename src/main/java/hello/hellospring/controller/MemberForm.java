package hello.hellospring.controller;

public class MemberForm {

    // 웹 화면의 데이터를 폼 객체 (MemberForm)에 전달할 때
    // form에 있는 name속성 (name="name")이랑 매칭된다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {  // name은 private으로 스프링이 setName()을 호출해서 값 변경
        this.name = name;
    }
}
