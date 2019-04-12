package com.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CountText {
	public static void main(String[] args) throws IOException {
		StringBuffer sb=readFile();
		Map<String, Integer> word = getWord(sb);
		System.out.println("a");
		for(String key:word.keySet()){
			Integer i=word.get(key);
			System.out.println(key+":"+i);
		}
	}
	/**根据map的value排序
	 * 
	 */
	 private static List<Map.Entry<String, Integer>> sortValue(Map<String, Integer> map) {
	        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
	        /**
	         * Comparator（接口）是匿名内部类，compare是创建匿名内部类要实现的抽象方法
	         * Comparator可看作一个排序器
	         */
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            /**
	             * 对list进行排序；o1和o2谁在compareTo之前，谁就从list第一位开始取，在compateTo之后的从第二位开始取
	             * 当o2小于o1时（也就是返回值为-1时），交换o2和o1的位置
	             * @param o1    list从第二位开始取
	             * @param o2    list从第一位开始取
	             * @return      返回0和1时位置不变，返回-1时交换当前o1和o2的位置
	             */
	            @Override
	            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	                return o2.getValue().compareTo(o1.getValue());  //compareTo是Comparable接口的的方法，返回值为1，0，-1
	            }
	        });
	        return list;
	    }
	
	/**把stringbuffer中的单个单词取出作为map的key出现的次数作为map的value
	 * 
	 */
	public static Map<String, Integer> getWord(StringBuffer sb){
		Map<String,Integer> map=new TreeMap<String,Integer>();
		StringBuffer word=new StringBuffer();
		for(int i=0;i<sb.length();i++){
			char c = sb.charAt(i);
			if(c != ' '){
				word.append(c);
			}else{
				String str = word.toString();
				if(map.containsKey(str)){
					Integer value=map.get(str);
					map.put(str, ++value);
				}else{
					Integer value=1;
					map.put(str, value);
				}
				word=new StringBuffer();
			}
		}
		return map;
	}
	
	/**
	 * 从文件中读取内容存入stringbufferead
	 * @throws IOException 
	 */
	public static StringBuffer readFile() throws IOException{
		StringBuffer sb=new StringBuffer();
		FileReader fis=new FileReader("E:/Code/wordCount/word.txt");
		BufferedReader br=new BufferedReader(fis);
		String it = br.readLine();
		while(it!=null ){
			sb.append(it);
			sb.append("");
			it=br.readLine();
		}
		return sb;
	}

}
