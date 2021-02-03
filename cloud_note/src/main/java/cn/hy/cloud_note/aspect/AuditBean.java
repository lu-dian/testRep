package cn.hy.cloud_note.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditBean {
	//性能审计
	//环绕通知
	@Around("within(cn.hy.cloud_note.service..*)")
	public Object audit(ProceedingJoinPoint point) {
		Object obj = null;
		//模拟切面过程
		//模拟service调用
		try {
			long timeStart = System.currentTimeMillis();
			obj = point.proceed();
			long timeEnd = System.currentTimeMillis();
			String str = point.getSignature().toString();
			System.out.println(str+" service响应时间： "+ (timeEnd - timeStart));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
}
