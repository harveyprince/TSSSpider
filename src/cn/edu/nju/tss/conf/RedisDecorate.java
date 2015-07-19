package cn.edu.nju.tss.conf;

/**
 * @author harveyprince
 *	用于redis的增查格式统一
 */
public class RedisDecorate {
	public static String activateDec(String info){
		return "activate."+info;
	}
	public static String nameDec(String info){
		return "name."+info;
	}
	public static String passDec(String info){
		return "pass."+info;
	}
}
