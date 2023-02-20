package org.xwb.springcloud.utils;


import com.aspose.words.Document;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import com.itextpdf.text.Image;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import com.itextpdf.text.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * @author duan
 * 生成电子公章
 */
@SuppressWarnings("All")
@Slf4j
public class CreateImgUtils {


	private static final int WIDTH = 450;//图片宽度
	private static final int HEIGHT = 450;//图片高度

	static byte[] bytes;

	/**
	 * @param image 图片BufferedImage流
	 * @param rows  分割行
	 * @param cols  分割列
	 * @return BufferedImage[] 返回分割后的图片流
	 * @Title: splitImage
	 * @Description: 分割图片
	 */
	public static BufferedImage[] splitImage(BufferedImage image, int rows, int cols) {
		// 分割成4*4(16)个小图
		int chunks = rows * cols;
		// 计算每个小图的宽度和高度
		int chunkWidth = image.getWidth() / cols + 3;// 向右移动3
		int chunkHeight = image.getHeight() / rows;
		int count = 0;
		BufferedImage[] imgs = new BufferedImage[chunks];
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				//设置小图的大小和类型
				imgs[count] = new BufferedImage(chunkWidth, chunkHeight, BufferedImage.TYPE_INT_RGB);
				//写入图像内容
				Graphics2D gr = imgs[count].createGraphics();
				// 增加下面代码使得背景透明
				imgs[count] = gr.getDeviceConfiguration()
					.createCompatibleImage(chunkWidth, chunkHeight, Transparency.TRANSLUCENT);
				//                gr.setBackground(Color.WHITE);// 背景为白色
				//                 // 加上这句才算真正将背景颜色设置为透明色
				//                gr.clearRect(0, 0,chunkWidth,chunkHeight);
				gr.dispose();
				gr = imgs[count].createGraphics();
				gr.drawImage(image, 0, 0,
					chunkWidth, chunkHeight,
					chunkWidth * y, chunkHeight * x,
					chunkWidth * y + chunkWidth,
					chunkHeight * x + chunkHeight, null);
				gr.dispose();
				count++;
			}
		}
		return imgs;
	}

	/**
	 * @param message    公司名称
	 * @param centerName 公章类型，如：测试章
	 * @param year       时间
	 * @return BufferedImage 返回类型
	 * @Title: startGraphics2D
	 * @Description: 生成公司电子公章
	 */
	public static BufferedImage startGraphics2D(String message, String centerName, String year) {
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 增加下面代码使得背景透明
		buffImg = g.getDeviceConfiguration().createCompatibleImage(WIDTH, HEIGHT, Transparency.TRANSLUCENT);
		g.dispose();
		g = buffImg.createGraphics();
		// 背景透明代码结束
		g.setColor(Color.RED);
		//设置锯齿圆滑
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//绘制圆
		int radius = HEIGHT / 3;//周半径
		int CENTERX = WIDTH / 2;//画图所出位置
		int CENTERY = HEIGHT / 2;//画图所处位置

		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(CENTERX, CENTERY, CENTERX + radius, CENTERY + radius);
		g.setStroke(new BasicStroke(10));//设置圆的宽度
		g.draw(circle);

		int num = 120;
		int num1 = 40;
		//        num = 90;
		num1 = 72;

		//绘制中间的五角星
		g.setFont(new Font("宋体", Font.BOLD, num));
		g.drawString("★", CENTERX - (num / 2), CENTERY + (num / 3));

		//添加姓名
		g.setFont(new Font("宋体", Font.LAYOUT_LEFT_TO_RIGHT, 30));// 写入签名
		g.drawString(centerName, CENTERX - (num1), CENTERY + (30 + 50));

		//添加年份
		g.setFont(new Font("宋体", Font.LAYOUT_LEFT_TO_RIGHT, 20));// 写入签名
		g.drawString(year, CENTERX - (66), CENTERY + (30 + 78));

		//根据输入字符串得到字符数组
		char[] messages = message.toCharArray();

		//输入的字数
		int ilength = messages.length;

		//设置字体属性
		int fontsize = 40;
		Font f = new Font("Serif", Font.BOLD, fontsize);

		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, context);

		//字符宽度＝字符串长度/字符数
		double char_interval = (bounds.getWidth() / ilength);
		//上坡度
		double ascent = -bounds.getY() + 3;

		int first = 0, second = 0;
		boolean odd = false;
		if (ilength % 2 == 1) {
			first = (ilength - 1) / 2;
			odd = true;
		} else {
			first = (ilength) / 2 - 1;
			second = (ilength) / 2;
			odd = false;
		}

		double radius2 = radius - ascent;
		double x0 = CENTERX;
		double y0 = CENTERY - radius + ascent;
		//旋转角度
		double a = 2 * Math.asin(char_interval / (2 * radius2));

		if (odd) {
			g.setFont(f);
			g.drawString(messages[first] + "", (float) (x0 - char_interval / 2), (float) y0);

			//中心点的右边
			for (int i = first + 1; i < ilength; i++) {
				double aa = (i - first) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(messages[i] + "", (float) (x0 + ax - char_interval / 2 * Math.cos(aa)),
					(float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
			}
			//中心点的左边
			for (int i = first - 1; i > -1; i--) {
				double aa = (first - i) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(messages[i] + "", (float) (x0 - ax - char_interval / 2 * Math.cos(aa)),
					(float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
			}

		} else {
			//中心点的右边
			for (int i = second; i < ilength; i++) {
				double aa = (i - second + 0.5) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(messages[i] + "", (float) (x0 + ax - char_interval / 2 * Math.cos(aa)),
					(float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
			}

			//中心点的左边
			for (int i = first; i > -1; i--) {
				double aa = (first - i + 0.5) * a;
				double ax = radius2 * Math.sin(aa);
				double ay = radius2 - radius2 * Math.cos(aa);
				AffineTransform transform = AffineTransform.getRotateInstance(-aa);//,x0 + ax, y0 + ay);
				Font f2 = f.deriveFont(transform);
				g.setFont(f2);
				g.drawString(messages[i] + "", (float) (x0 - ax - char_interval / 2 * Math.cos(aa)),
					(float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
			}
		}

		return buffImg;
	}


	//	public static void main(String[] args) throws Exception{
	//		BufferedImage image = startGraphics2D("宁夏九鼎物流","合同专用章","");
	//
	//		String filePath = "";
	//		try {
	//			filePath = "D:/货主合同/"+new Date().getTime()+".png";
	//			ImageIO.write(image, "png", new File(filePath)); //将其保存在D:\\下，得有这个目录
	//		} catch (Exception ex) {
	//			ex.printStackTrace();
	//		}
	//	}


	/**
	 * @param inputStream pdf输入流
	 * @param carrieName  承运商名称
	 * @param partnerName 货主名称
	 * @param purpose     章子用途
	 * @param targetPath  存储路径
	 * @throws Exception
	 * @author duan
	 * 向PDF文件上盖章
	 */
	public static void itextPDFAddPicture(InputStream inputStream, String carrieName, String partnerName,
										  String purpose, String targetPath) throws Exception {
		// 1.1 读取模板文件
		PdfReader reader = new PdfReader(inputStream);
		// 1.2 创建文件输出流
		FileOutputStream out = new FileOutputStream(targetPath);
		// 2、创建PdfStamper对象
		PdfStamper stamper = new PdfStamper(reader, out);
		// 3、设置公章信息

		// 4、生成承运商公章
		BufferedImage bufferedImage = startGraphics2D(carrieName, purpose, "");// 整个公章图片流
		// 生成货主公章
		BufferedImage partnerImage = startGraphics2D(partnerName, purpose, "");// 整个公章图片流
		BufferedImage[] imgs = splitImage(bufferedImage, 1, 2);
		BufferedImage leftBufferedImage = imgs[0];// 左边公章图片流
		BufferedImage rightBufferedImage = imgs[1];// 右边公章图片流

		// 5、读公章图片
		Image image = Image.getInstance(imageToBytes(bufferedImage, "png"));
		Image pImage = Image.getInstance(imageToBytes(partnerImage, "png"));
		Image leftImage = Image.getInstance(imageToBytes(leftBufferedImage, "png"));
		Image rightImage = Image.getInstance(imageToBytes(rightBufferedImage, "png"));
		int chunkWidth = 200;// 公章大小，x轴
		int chunkHeight = 200;// 公章大小，y轴
		// 获取pdf页面的高和宽
		Rectangle pageSize = reader.getPageSize(1);
		float height = pageSize.getHeight();
		float width = pageSize.getWidth();
		// 6、为pdf每页加印章
		// 设置公章的位置
		float xL = width - chunkWidth / 2 - 2;
		float yL = height / 2 - chunkHeight / 2 - 25;

		float xR = width - chunkHeight / 2 + chunkHeight / 8 + 4;
		float yR = yL;
		// 6.1 第一页盖左章
		leftImage.scaleToFit(chunkWidth, chunkHeight);// 公章大小
		leftImage.setAbsolutePosition(xL, yL);// 公章位置
		// 6.2 第二页盖右章
		rightImage.scaleToFit(chunkWidth, chunkHeight);// 公章大小
		rightImage.setAbsolutePosition(xR, yR);// 公章位置
		int pdfPages = reader.getNumberOfPages();// pdf页面页码
		// 遍历为每页盖左章或右章
		for (int i = 1; i < pdfPages; i++) {
			if (i % 2 == 0) {// 盖右章
				stamper.getOverContent(i).addImage(rightImage);
			} else {// 盖左章
				stamper.getOverContent(i).addImage(leftImage);
			}
		}

		// 6.3 最后一页盖公章
		image.scaleToFit(chunkWidth, chunkWidth);
		image.setAbsolutePosition(width / 2 + 10, height - chunkHeight - 310);
		stamper.getOverContent(pdfPages).addImage(image);
		pImage.scaleToFit(chunkWidth, chunkWidth);
		pImage.setAbsolutePosition(width / 2 - 240, height - chunkHeight - 310);
		stamper.getOverContent(pdfPages).addImage(pImage);

		// 7、关闭相关流
		stamper.close();
		out.close();
		reader.close();
		inputStream.close();
	}


	/**
	 * 图片转为bytes
	 *
	 * @param bImage
	 * @param format
	 * @return
	 */
	public static byte[] imageToBytes(BufferedImage bImage, String format) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}


	/**
	 * word转为PDF
	 *
	 * @param doc
	 * @param targetFile
	 */

	public static String doc2pdf(Document doc, String targetFile, int docType) {
		try {
			long old = System.currentTimeMillis();
			//新建一个空白pdf文档
			File file = new File(targetFile);
			FileOutputStream os = new FileOutputStream(file);
			//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
			doc.save(os, docType);
			os.close();
			long now = System.currentTimeMillis();
			//转化用时
			System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "转换失败";
		}
	}


}
