package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect     // AOP는 @Aspect를 꼭 붙여야 함!!
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")   // 적용 대상 targeting, 패키지 하위 모두 적용
    public Object execute(ProceedingJoinPoint jointPoint) throws Throwable{
        long start = System.currentTimeMillis();

        System.out.println("START = " + jointPoint.toString());

        try{
            // 다음 메서드로 진행
            return jointPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END = " + jointPoint.toString() + " " + timeMs + "ms");
        }

    }

}
