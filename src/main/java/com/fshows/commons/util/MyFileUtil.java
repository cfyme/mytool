package com.fshows.commons.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyFileUtil {

	public static List<String> getListFromFile(String fileName) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			InputStream is = new FileInputStream(fileName);
			reader = new BufferedReader(new InputStreamReader(is));
			String tempString = null;
			//一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (tempString.trim().length() > 0) {
					list.add(tempString.trim().replace("<", "").replace(">", ""));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	/**
	 * 读取txt文件的内容
	 * @param file 想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String getStrFromFile(String file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));//构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static List<String> getListFromFile2(InputStream inputStream) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String tempString = null;
			//一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (tempString.trim().length() > 0) {
					list.add(tempString.trim().replace("<", "").replace(">", ""));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		//获取classpath路径
		//System.out.println("classpath路径： " + MyFileUtil.class.getClassLoader().getResource("").getPath());

		//获取当前类的加载路径
		//System.out.println("当前类加载路径： " + MyFileUtil.class.getResource("item.txt").getPath());
	}
}
