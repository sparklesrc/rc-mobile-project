package pe.com.rc.mobile.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import pe.com.rc.mobile.core.model.DefaultResponse;

@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* pe.com.rc.mobile.web.controller..*(..))", throwing = "excep")
	public DefaultResponse afterThrowing(JoinPoint joinPoint, Throwable excep) throws Throwable {
		return new DefaultResponse(500, "There was an error.");
	}
}
