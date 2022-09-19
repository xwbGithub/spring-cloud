package com.cloud.elasticsearch.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.entity.ContentType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

import static cn.hutool.core.swing.ScreenUtil.dimension;

/**
 * @author xwb
 * @description 气站二维码生成方法
 */
@SuppressWarnings("rawtypes")
@Slf4j
public class GasStationQrCodeUtils {
    /**
     * 二维码宽
     */
    private static final int HEIGHT = 350;
    /**
     * 二维码高
     */
    private static final int WIDTH = 350;
    /**
     * 背景色值
     */
    private static Color COLOR = new Color(239, 239, 238);
    /**
     * 气站二维码背景图
     */
//    private static final String GAS_STATION_BACKGROUND = "static/gasBackground.png";
    private static final String GAS_STATION_BACKGROUND = "E:\\project\\myProject\\spring-cloud\\cloud-other\\cloud" +
            "-elasticsearch\\src\\main\\resources\\static\\gasBackground2.png";

    /**
     * 上传递到本地的零时文件
     */
    private static final String PROFILE_FILE = "D:\\ruoyi\\uploadPath" + File.separator + "temp";

    /**
     * 生成气站二维码
     *
     * @param gasId 气站id
     * @return 返回生成后的图片信息
     */
    public static String createGasStationQrCode(long gasId, String stationName) {
        String qrCodePath = "";
        FileInputStream fis = null;
        try {
            qrCodePath = PROFILE_FILE + File.separator + gasId + ".png";
            qrCodePath = createQrCode(qrCodePath, gasId, stationName);
            if (StringUtils.isEmpty(qrCodePath)) {
                log.error("二维码图片生成失败！");
                return null;
            }
			assert qrCodePath != null;
			File qrcodeFile = new File(qrCodePath);
			//加载背景图
//			ClassPathResource backResource = new ClassPathResource(GAS_STATION_BACKGROUND);
//			File backResource=new File(GAS_STATION_BACKGROUND);
            File file = new File(GAS_STATION_BACKGROUND);
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedImage bjImage = ImageIO.read(fileInputStream);
			//读取背景图片  获取大小
			log.warn("背景图：width:[{}],height:[{}]", bjImage.getWidth(), bjImage.getHeight());
			//创建图片
			BufferedImage img = new BufferedImage(bjImage.getWidth(), bjImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			BufferedImage logo = ImageIO.read(qrcodeFile);
			//开启画图
			Graphics g = img.getGraphics();
			// 绘制缩小后的图
			g.drawImage(bjImage.getScaledInstance(bjImage.getWidth(), bjImage.getHeight(), Image.SCALE_DEFAULT), 0, 0,
				null);
			//圆角设置
            //g.drawRoundRect(bjImage.getWidth()-10, bjImage.getHeight()-10, 200, 200, 5, 5);
			//读取二维码图片大小
			BufferedImage qrImage = ImageIO.read(new FileInputStream(qrCodePath));

			log.warn("二维码：width:[{}],height:[{}]", qrImage.getWidth(), qrImage.getHeight());
			// 绘制缩小后的图
			g.drawImage(logo.getScaledInstance(160, 160, Image.SCALE_DEFAULT), 250, 330, null);


			//绘制文字
            drawWord1(img, stationName);

			//设置成和背景图一样的颜色值
			g.setColor(new Color(239, 239, 238));
			g.dispose();
			ImageIO.write(img, "png", new File(PROFILE_FILE + File.separator + gasId + ".png"));
			fis = new FileInputStream(qrCodePath);
			String fileName = file.getName();
			assert fileName != null;
			String contentType = ContentType.APPLICATION_OCTET_STREAM.toString();
			MultipartFile mf = new MockMultipartFile(fileName, fileName, contentType, fis);
//			String filePath = OSSUtils.uploadFile(mf);
//            本地生成地址
			String filePath = saveFileByDirectory(mf, File.separator+gasId + ".png");
			log.info("生成的阿里云地址：{}", filePath);
			return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //操作完成删除需要删除的零时文件
            if (!StringUtils.isEmpty(PROFILE_FILE)) {
                //FileToolUtils.deleteFile(new File(PROFILE_FILE));
            }
        }
        return null;
    }
    private static BitMatrix updateBit(BitMatrix matrix, int margin){
        int tempM = margin*2;
        //获取二维码图案的属性
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + tempM;
        int resHeight = rec[3] + tempM;
        // 按照自定义边框生成新的BitMatrix
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        //循环，将二维码图案绘制到新的bitMatrix中
        for(int i= margin; i < resWidth- margin; i++){
            for(int j=margin; j < resHeight-margin; j++){
                if(matrix.get(i-margin + rec[0], j-margin + rec[1])){
                    resMatrix.set(i,j);
                }
            }
        }
        return resMatrix;
    }



    /**
     * 图片放大缩小
     */
    public static BufferedImage  zoomInImage(BufferedImage  originalImage, int width, int height){
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;

    }
    /**
     * 删除白色边框
     *
     * @param matrix matrix
     * @return BitMatrix
     */
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }
    public static String createQrCode(String url, long gasId, String stationName) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            //设置二维码纠错级别map
            HashMap hint = new HashMap();
            // 矫错级别
            hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //设置UTF-8， 防止中文乱码
            hint.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            //创建比特矩阵(位矩阵)的QR码编码的字符串
            BitMatrix byteMatrix = qrCodeWriter.encode(String.valueOf(gasId), BarcodeFormat.QR_CODE, WIDTH, HEIGHT,
                    hint);
            byteMatrix=updateBit(byteMatrix,5);
            //byteMatrix=setWidthHeight(byteMatrix);
            //byteMatrix = deleteWhite(byteMatrix);
            // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
            int matrixWidth = byteMatrix.getWidth();
            int matrixHeight = byteMatrix.getHeight();
            //距离左右边框的距离
            BufferedImage image = new BufferedImage(matrixWidth-10, matrixHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            //背景色
            graphics.setColor(COLOR);
            // 1.0f为透明度 ，值从0-1.0，依次变得不透明
//            Composite alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
//            graphics.setComposite(alphaComp);
            graphics.fillRect(0, 0, matrixWidth, matrixHeight);
            // 使用比特矩阵画并保存图像
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i - 10, j - 10, 1, 1);
                    }
                }
            }
            //追加文字 绘制气站名称在二维码的下面
            //image = drawWord(image, stationName);
            FileToolUtils.createFile(url);
            log.info("临时文件信息:{}", url);
            fileOutputStream = new FileOutputStream(url);
            ImageIO.write(image, "png", fileOutputStream);
            File file = new File(url);
            inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
            String qrcodePath = saveFileByDirectory(multipartFile, gasId + ".png");
            graphics.dispose();
            return qrcodePath;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成二维码失败!{}", e.getMessage());
            return null;
        } finally {
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static BitMatrix setWidthHeight(BitMatrix input){
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        //以下两行源码是原始代码中控制边距的参数
        //int qrWidth = inputWidth + (quietZone << 1);
        //int qrHeight = inputHeight + (quietZone << 1);
        //以下两行源码是修改后的控制边距的参数
        int qrWidth = inputWidth + 2;
        int qrHeight = inputHeight + 2;
        int outputWidth = Math.max(WIDTH, qrWidth);
        int outputHeight = Math.max(HEIGHT, qrHeight);

        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        // Padding includes both the quiet zone and the extra white pixels to accommodate the requested
        // dimensions. For example, if input is 25x25 the QR will be 33x33 including the quiet zone.
        // If the requested size is 200x160, the multiple will be 4, for a QR of 132x132. These will
        // handle all the padding from 100x100 (the actual QR) up to 200x160.
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        int topPadding = (outputHeight - (inputHeight * multiple)) / 2;

        BitMatrix output = new BitMatrix(outputWidth, outputHeight);

        for (int inputY = 0, outputY = topPadding; inputY < inputHeight; inputY++, outputY += multiple) {
            // Write the contents of this row of the barcode
            for (int inputX = 0, outputX = leftPadding; inputX < inputWidth; inputX++, outputX += multiple) {
                if (input.get(inputX, inputY)) {
                    output.setRegion(outputX, outputY, multiple, multiple);
                }
            }
        }
        return output;
    }
    public static void setWhirl(Graphics2D graphics,int angle){
        AffineTransform trans = new AffineTransform();
        trans.rotate(Math.PI, WIDTH / 2, HEIGHT / 2);
        graphics.setTransform(trans);
    }
    private static int getFontSize(String note){
        int a = 0;
        String reg1 = "[\u4e00-\u9fa5]";
        String reg2 = "[A-Z]";
        String reg3 = "[a-z]";
        String reg4 = "[0-9]";
        char[] charArr = note.toCharArray();
        String[] strArr = new String[charArr.length];
        for (int i = 0; i < charArr.length; i++) {
            strArr[i] = String.valueOf(charArr[i]);
            if (strArr[i].matches(reg1)) {
                a = a + 19;
            } else if (strArr[i].matches(reg4)) {
                a = a + 11;
            } else if (strArr[i].matches(reg2) || strArr[i].matches(reg3)) {
                a = a + 9;
            } else {
                a = a + 5;
            }
        }
        return a;
    }
    private static BufferedImage drawWord1(BufferedImage image, String stationName) {
        int fontSize = getFontSize(stationName);
        log.info("fontsize:{}",fontSize);
        BufferedImage tag = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g1 = tag.getGraphics();
        Graphics2D g2 = image.createGraphics();
        Font font = new Font("微软雅黑", Font.BOLD, 16);
        g2.setFont(font);
        g2.setColor(Color.black);
        g2.setBackground(COLOR);
        g2.drawString(stationName, (image.getWidth()-fontSize)/2-40, image.getHeight()/2+100);
        g2.dispose();
        image.flush();
        return image;
    }
    private static BufferedImage drawWord(BufferedImage image, String stationName) {
        int fontSize = getFontSize(stationName);
        log.info("fontsize:{}",fontSize);
        Image src = image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
        BufferedImage tag = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g1 = tag.getGraphics();
        Graphics2D g2 = image.createGraphics();
        Font font = new Font("微软雅黑", Font.BOLD, 24);
        g2.setFont(font);
        g2.setColor(Color.black);
        g2.setBackground(COLOR);
        g2.drawString(stationName, (WIDTH-fontSize)/2, HEIGHT-25);
        g1.drawImage(src, 0, 0, null);
        g2.dispose();
        image = tag;
        image.flush();
        return image;
    }

    public static String saveFileByDirectory(MultipartFile file, String fileName) {
        try {
            // 复制文件
            File targetFile = new File(PROFILE_FILE, fileName);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
            return targetFile.getAbsolutePath();
        } catch (IOException e) {
            log.error("保存文件到服务器（本地）失败", e);
        }
        return null;
    }

    public static void main(String[] args) {
        createGasStationQrCode(Long.valueOf("298074092174"), "测试气站二维码图片信息得啦");
    }
}
