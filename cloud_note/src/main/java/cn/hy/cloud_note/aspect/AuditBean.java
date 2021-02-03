package cn.hy.cloud_note.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditBean {
	//�������
	//����֪ͨ
	@Around("within(cn.hy.cloud_note.service..*)")
	public Object audit(ProceedingJoinPoint point) {
		Object obj = null;
		//ģ���������
		//ģ��service����
		try {
			long timeStart = System.currentTimeMillis();
			obj = point.proceed();
			long timeEnd = System.currentTimeMillis();
			String str = point.getSignature().toString();
			System.out.println(str+" service��Ӧʱ�䣺 "+ (timeEnd - timeStart));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
}
