package com.lh.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Test3 {

	public static void main(String[] args) {
		// System.out.println("开始备份...");
		// backup();
		// System.out.println("备份成功...");

		System.out.println("开始还原...");
		load();
		System.out.println("还原成功...");

	}

	private static void backup() {
		try {
			Runtime rt = Runtime.getRuntime();

			// 调用 调用mysql的安装目录的命令
			Process child = rt.exec("mysqldump -h localhost -uroot -proot ry");
			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流

			InputStreamReader xx = new InputStreamReader(in, "utf-8");
			// 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码

			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();

			// 要用来做导入用的sql目标文件：
			FileOutputStream fout = new FileOutputStream("d:/ry.sql");
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();

			System.out.println("end");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void load() {
		try {
			String fPath = "d:/ry.sql";
			Runtime rt = Runtime.getRuntime();

			// 调用 mysql 安装目录的命令
			Process child = rt.exec("mysql -u root -p root ry");

			OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fPath), "utf-8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			System.out.println(outStr);
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
			System.out.println("7777777777777777777777777777777777777");
			writer.write(outStr);
			System.out.println("888888888888888888888888888888888888888");
			writer.flush();
			out.close();
			br.close();
			writer.close();

			System.out.println("");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
