package com.szlaun.launtech.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * 生成一张图片验证码
 */
public class VerificationCode {
	private String az="abcdefghijklmnopqrstuvwxyz0123456789";
	private String[] fontArr={"宋体","华文楷体","黑体","华文新魏","华文隶书","微软雅黑","楷体_GB2312"};
	private int[]sizeArr={28,34,36,44,40};
	private int width=200;//宽
	private int height=80;//高
	private String result;
	private Random rd=new Random();//随机对象
	
	public static void main(String[] args) throws IOException {
		VerificationCode vs=new VerificationCode();
		List<Object> list = vs.createImage("D://kk.jpg");
		System.out.println(list.get(1));
		System.out.println(list.get(0));
	}//测试验证码

	/**
	 * 创建一张图片验证码
	 * 返回图片（list.get(0)）形式和验证码结果（list.get(1)）
	 * @return
	 */
	public List<Object> createImage(){
		List<Object> list=new ArrayList<Object>();
		BufferedImage bi=draWingTwo(create());
		list.add(bi);
		list.add(result);
		return list;
	}

	/**
	 * 生成一张验证码图形写入指定地址
	 * @param path 生成的验证码图片写入的绝对位置
	 * @throws IOException
	 */
	public List<Object> createImage(String path) throws IOException{
		List<Object> list=new ArrayList<Object>();
		path=path.replace("\\", "//");
		OutputStream os=new FileOutputStream(new File(path));
		//对图片做出写画操作
		BufferedImage bi=draWingTwo(create());

//		try {
//			将图片转为inputStream
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write(bi, "png", baos);
//			InputStream input = new ByteArrayInputStream(baos.toByteArray());

//			将图片转为String
//			String result = new BufferedReader(new InputStreamReader(input))
//					.lines().collect(Collectors.joining(System.lineSeparator()));
//			System.out.println(result);
//		} catch (IOException e) {
//		}
		//将这张图片 通过流 以指定格式写入内存
		save(bi,os);
		list.add(bi);
		list.add(result);
		return list;
	}
	
	/**
	 * 生成一张指定背景色的图片
	 * @return
	 */
	private BufferedImage create(){
		//描述指定宽高生成一个图形
		BufferedImage bi=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//设置画笔对象的颜色
		bi.getGraphics().setColor(new Color(240,240,240));
		//使用画笔对象填充背景色
		bi.getGraphics().fillRect(0,0, width, height);
		return bi;
	}
	
	/**
	 * 将图片写入内存
	 * @param bi
	 * @param os
	 * @throws IOException
	 */
	private static void save(BufferedImage bi,OutputStream os) throws IOException{
		//将这张图片 通过流 以指定格式写入内存
		ImageIO.write(bi,"jpeg",os);
	}
	/**
	 * 画板方法 对图片编辑内容
	 * @param bi 图片对象
	 * @return
	 */
	private BufferedImage draWingTwo(BufferedImage bi){
		//得到图片对象的画笔
		Graphics g=bi.getGraphics();
		//设置画笔的颜色
		g.setColor(new Color(240,240,240));
		result="";//初始化结果
		//随机写入五个字母
		for (int i = 0; i < 5; i++) {
			String ch=getstring();//随机一个字符
			g.setColor(getColor());//随机一种颜色
			g.setFont(getFont());//随机抽取一个字体大小
			g.drawString(ch,width/5*i+1,height-20);//将字符写入到指定位置
			result+=ch;//保存结果
		}
		drawLine(bi);
		return bi;
	}
	/**
	 * 随机描绘干扰线
	 * @param bi 图片
	 */
	private void drawLine(BufferedImage bi){
		//得到一只可改变粗细的画笔对象
		Graphics2D gd=(Graphics2D)bi.getGraphics();
		
		gd.setColor(Color.BLACK);//设置画笔颜色  黑色
		
		gd.setStroke(new BasicStroke(1.5f));//设置画笔粗细
		
		for (int i = 0; i < 5; i++) {
			//生成一个随机的点
			int x1=rd.nextInt(width);//x轴坐标
			int y1=rd.nextInt(height);//y轴坐标
			
			//再次生成一个随机的点
			int x2=rd.nextInt(width);//x轴坐标
			int y2=rd.nextInt(height);//y轴坐标
			
			//将随机的点使用画笔连接
			gd.drawLine(x1, y1, x2, y2);
		}
	}
	/**
	 * 随机抽取一个指定的字体大小
	 * @return
	 */
	private Font getFont(){
		int size=rd.nextInt(sizeArr.length);//字体
		size=sizeArr[size];//大小
		int style=rd.nextInt(4);//样式1-4
		
		//参数			一：字体				 二：样式1-4	    三：大小
		Font f=new Font(fontArr[rd.nextInt(fontArr.length)],style,size);
		return f;
	}
	/**
	 * 生成一个随机的rgb颜色
	 * @return
	 */
	private Color getColor(){
		return new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
	}
	/**
	 * 从字符串中随机抽取一个字符
	 * @return
	 */
	private String getstring(){
		char[]arr=az.toCharArray();
		return arr[rd.nextInt(arr.length)]+"";
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
