package cn.hy.cloud_note.aspect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component  //ɨ�赽spring����
@Aspect		//��������Ϊ�������
public class ExceptionBean {
	
	//�쳣��־
	//eĿ������ܳ������쳣����
	@AfterThrowing(throwing = "e",pointcut="within(cn.hy.cloud_note.service..*)")
	public void eLog(Exception e) {
		//���쳣��Ϣ�����ļ�
		try {
			FileWriter fw = new FileWriter("F:\\note_error.log",true);
			PrintWriter pw = new PrintWriter(fw);
			//����pw����д���쳣��Ϣ
			//ʱ�� �쳣����
			Date time = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeStr = sdf.format(time);
			//�����ļ�ͷ
			pw.println("***************************************************");
			pw.println("**�쳣���ͣ�"+e);
			pw.println("**�쳣ʱ�䣺"+timeStr);
			pw.println("*******************�쳣��ϸ��Ϣ*************************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
			
		} catch (Exception ex) {
			System.out.println("��¼�쳣ʧ��");
		}
	}
}
