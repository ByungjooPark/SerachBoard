package kr.co.hk;

public class Utils {
	public static int stringToint_one(String str) {
		int tmp = 1;
		try {
			tmp = Integer.parseInt(str);
		} catch (Exception e) {
			
		}
		
		return tmp;
	}
}
