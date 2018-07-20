package cn.yarne.com.base.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 支付结果通知接口
 * @author 10166
 * 2018-6-6 14:21:23
 */
public class WXPayResultNotifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public synchronized void service(HttpServletRequest request, HttpServletResponse response) {
		String res = "FAILED";
		try {
			// 读取参数为xml格式
			InputStream inputStream;
			StringBuffer sb = new StringBuffer();
			inputStream = request.getInputStream();
			String s;
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			while ((s = in.readLine()) != null) {
				sb.append(s);
			}
			in.close();
			inputStream.close();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
