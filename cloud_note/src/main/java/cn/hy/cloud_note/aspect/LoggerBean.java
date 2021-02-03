package cn.hy.cloud_note.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerBean {
	@Before("within(cn.hy.cloud_note.controller..*)")
	public void logController() {
		System.out.println("AOP功能注入Controller层");
	}
	@Before("within(cn.hy.cloud_note.service..*)")
	public void logService() {
		System.out.println("Service层注入AOP功能");
	}
}
