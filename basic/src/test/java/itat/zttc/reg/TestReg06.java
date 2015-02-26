package itat.zttc.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg06 {
	public static void main(String[] args) {
		String str = "532101197612210039,532122199802120034,534501198212110029,532101780203009";
		//ʹ�����Ž��з���
		Pattern p = Pattern.compile("((\\d{6})(\\d{8}))\\d{4}");
		Matcher m = p.matcher(str);
		while(m.find()) {
			System.out.println(m.group());
			System.out.println("��Դ��:"+m.group(2)+"��������:"+m.group(3));
		}
		String h = "<table><td>���</td><td>��Һ�</td><td>ÿ���˶���</td></table>";
		//̰��ģʽ��ָ����.*��ƥ�����е���Ϣ���˴�����������Ϣ
		p = Pattern.compile("<td>(.*)</td>");
		m = p.matcher(h);
		while(m.find()) {
			System.out.println(m.group(1));
			System.out.println(m.start()+","+m.end());
		}
		//�ҵ��Ľ��:���</td><td>��Һ�</td><td>ÿ���˶���
		
		//��̰��ģʽ������ֻ��ƥ���һ����β,�ر�ע��:������*+֮��ͱ�ʾʹ���˷�̰��ģʽ
		p = Pattern.compile("<td>(.*?)</td>");
		m = p.matcher(h);
		while(m.find()) {
			System.out.println(m.group(1));
			System.out.println(m.start()+","+m.end());
		}
	}
}
