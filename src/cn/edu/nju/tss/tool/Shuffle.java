package cn.edu.nju.tss.tool;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Shuffle {
	/**
	 * @param scode 源字符串
	 * @return 乱序后的字符串
	 */
	public static String shuffle(String scode){
		String code = new String(scode);
		List<String> list = Arrays.asList(code.split(""));
		Collections.shuffle(list);
		String result = "";
		for(String temp:list){
			result += temp;
		}
		return result;
	}
}
