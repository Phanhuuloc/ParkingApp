package com.sungwoo.aps.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Aspect
@Component
public class ServiceMonitor {
    private final static Logger LOGGER = Logger.getLogger(ServiceMonitor.class.getName());
//    @AfterReturning("execution(* com.sungwoo.aps..*Controller.*(..))")
//    public void logServiceAccess(JoinPoint joinPoint) {
//        System.out.println("Completed: " + joinPoint);
//    }

//    @After("execution(* com.sungwoo.aps..*Controller.*(..))")
//    public void logReturn(JoinPoint joinPoint) {
//        System.out.println("Completed: " + joinPoint);
//    }

//    @Before("execution(* com.sungwoo.aps..*Controller.*(..))")
//    public void logReturnBefore(JoinPoint joinPoint) {
//        System.out.println(String.format("Execute %s with param %d", joinPoint, uid));
//    }

//    @Before("execution(* com.sungwoo.aps..*Controller.*(..)) && @args")
//    public void logArgsReturnBrfore(JoinPoint joinPoint, int uid) {
//        System.out.println(String.format("Execute %s with param %d", joinPoint, uid));
//    }

//    @Before("SystemArchitecture.controllerOperation() && args(uid)")
//    public void logArgsReturnBefore(JoinPoint joinPoint, int uid) {
//        System.out.println(String.format(" Execute %s with param %d", joinPoint, uid));
//    }

    /**
     *
     */
//    @Before(value = "SystemArchitecture.intArgsRequest(uid)", argNames = "joinPoint,uid")
//    public void intArgsRequest(JoinPoint joinPoint, int uid) {
//        System.out.println(String.format("intArgsRequest Execute %s with param %d", joinPoint, uid));
//    }

    /**
     * @param joinPoint
     * @return
     */
    private String controllerMethod(JoinPoint joinPoint) {
        Signature s = joinPoint.getSignature();
        String ret = s.getDeclaringType().getSimpleName() + "." + s.getName() + "(";

        Object[] args = joinPoint.getArgs();
        int argsSize = args.length;
        for (int i = 0; i < argsSize; i++) {
            ret += args[i];
            if (i < argsSize - 1) {
                ret += ",";
            }
        }

        return ret + ")";
    }

    /**
     * @param joinPoint
     * @return
     */
    private String getRequest(JoinPoint joinPoint) {
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();

        String prm = "";
        if (null != m.getDeclaringClass().getAnnotation(RequestMapping.class)) {
            prm = m.getDeclaringClass().getAnnotation(RequestMapping.class).value()[0];
        }
        String retVal = "";
        String rm = "POST: ";
        PostMapping postMapping = m.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            retVal = rm + prm + postMapping.value()[0];
        } else {
            rm = "GET: ";
            retVal = rm + prm + m.getAnnotation(GetMapping.class).value()[0];
        }
        return retVal;
    }

    @Around("SystemArchitecture.secureMethod() && args(isAdmin)")
    public Object checkSecurity(ProceedingJoinPoint pjp, boolean isAdmin) throws Throwable {
        if (isAdmin) {
            return pjp.proceed();
        } else {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Before(value = "SystemArchitecture.getMethodOperation()",
            argNames = "joinPoint")
    public void getMethodOperation(JoinPoint joinPoint) {
        LOGGER.info(String.format("--------------------- RECEIVE GET REQUEST: %s --------------------",
                getRequest(joinPoint)));
        LOGGER.info(String.format("Call %s", controllerMethod(joinPoint)));
    }

    @Before(value = "SystemArchitecture.postMethodOperation()",
            argNames = "joinPoint")
    public void postMethodOperation(JoinPoint joinPoint) {
        LOGGER.info(String.format("--------------------- RECEIVE POST REQUEST: %s --------------------",
                getRequest(joinPoint)));
        LOGGER.info(String.format("Call %s", controllerMethod(joinPoint)));
    }

//    @Before(value = "SystemArchitecture.controllerOperation() && SystemArchitecture.intArgsRequest(reqVal)",
//            argNames = "joinPoint, reqVal")
//    public void getMethodOperation(JoinPoint joinPoint, Object reqVal) {
//        LOGGER.info(String.format("--------------------- RECEIVE REQUEST: %s --------------------",
//                getRequest(joinPoint)));
//        LOGGER.info(String.format("--------------------- BEGIN GET METHOD: %s --------------------",
//                controllerMethod(joinPoint)));
//        LOGGER.info(String.format("%s with param %s",
//                joinPoint, reqVal));
//    }

//    @AfterReturning(pointcut = "SystemArchitecture.dataAccessOperation()", returning = "retVal")
//    public void doAccessCheck(Object retVal) {
//        System.out.println(String.format("doAccessCheck Execute, return %s ", retVal));
//    }

    @AfterReturning(pointcut = "SystemArchitecture.controllerOperation()", argNames = "joinPoint, retVal",
            returning = "retVal")
    public void onControllerReturn(JoinPoint joinPoint, Object retVal) {
        LOGGER.info(String.format("%s return %s",
                joinPoint, retVal));
        LOGGER.info("--------------------------------------------- END --------------------------------------------");
    }

    @AfterReturning(pointcut = "SystemArchitecture.businessService()", argNames = "joinPoint, retVal",
            returning = "retVal")
    public void onServiceReturn(JoinPoint joinPoint, Object retVal) {
        LOGGER.info(String.format("%s, return %s ", joinPoint, retVal));
    }

    @AfterReturning(pointcut = "SystemArchitecture.dataAccessOperation()", argNames = "joinPoint, retVal",
            returning = "retVal")
    public void onDataReturn(JoinPoint joinPoint, Object retVal) {
        LOGGER.info(String.format("%s, return %s ", joinPoint, retVal));
    }

//    @AfterThrowing(pointcut = "SystemArchitecture.controllerOperation()", throwing = "ex")
//    public void doRecoveryActions(Exception ex) {
//        LOGGER.info(ex.getMessage());
//    }

}
