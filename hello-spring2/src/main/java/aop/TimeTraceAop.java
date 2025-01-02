package aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
어노테이션
https://velog.io/@youngerjesus/%EC%9E%90%EB%B0%94-Annotation

Aspect 를 사용하는 프로그래밍 방식을 AOP (Aspect-Oriented Programming)
AOP참조
https://velog.io/@jkh9615/AOP%EC%9D%98-%EA%B0%9C%EB%85%90%EA%B3%BC-Aspect
 */
@Component
@Aspect
public class TimeTraceAop {
    /*
    @Around 는 ‘핵심관심사’의 실패여부와 상관없이 전 후로 실행되록 하는 Advice로,
    아래와 같이 메서드를 생성할 때,
    return 값은 Object 이고, 인자는 ProceedingJoinPoint 이다.

    @Around 어노테이션을 이점을 살려서 사용하는 경우, 크게 두가지가 존재한다.

    1. request와 response를 로깅할 수 있다.

    2. proceed 메서드에 인자값을 Object[] 로 넣어주는 경우, 해당 인자값을 핵심관심사에게 넘겨준다.
    이를 잘 활용하면, 본래 인자값을 핵심관심사로 넘기기 전에 가공해서 넣어줄 수 있다.

    execute:매개변수로 전달받은 SQL 구문을 수행하는 메소드
     */
    @Around("execution(* hello.hello_spring2..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                    "ms");
        }
    }
}
