package cn.hy.cloud_note.aspect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component  //扫描到spring容器
@Aspect		//将该类作为切面组件
public class ExceptionBean {
	
	//异常日志
	//e目标组件跑出来的异常对象
	@AfterThrowing(throwing = "e",pointcut="within(cn.hy.cloud_note.service..*)")
	public void eLog(Exception e) {
		//将异常信息输入文件
		try {
			FileWriter fw = new FileWriter("F:\\note_error.log",true);
			PrintWriter pw = new PrintWriter(fw);
			//利用pw对象写入异常信息
			//时间 异常类型
			Date time = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeStr = sdf.format(time);
			//构造文件头
			pw.println("***************************************************");
			pw.println("**异常类型："+e);
			pw.println("**异常时间："+timeStr);
			pw.println("*******************异常详细信息*************************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
			
		} catch (Exception ex) {
			System.out.println("记录异常失败");
		}
	}
}
