package itat.zttc.reg;

public class TestReg05 {
	public static void main(String[] args) {
		String str = "234445sdsff3444sdss";
		//��һ��������������ʽ���ڶ�������Ҫ�滻��ֵ
		System.out.println(str.replaceAll("\\d", "*"));
		System.out.println(str.replaceAll("\\d+", "*"));
		System.out.println("13222331111".replaceAll("\\d{4}$", "****"));
	}
}
