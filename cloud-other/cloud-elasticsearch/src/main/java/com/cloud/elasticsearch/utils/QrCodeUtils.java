package com.cloud.elasticsearch.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

public class QrCodeUtils {
    /**
     * 生成包含字符串信息的二维码图片
     * @param outputStream 文件输出流路径
     * @param content 二维码携带信息
     */
    public static boolean createQrCode(OutputStream outputStream, String content) throws WriterException, IOException {
        //设置二维码纠错级别ＭＡＰ
        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别
        //设置UTF-8， 防止中文乱码
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置二维码四周白色区域的大小
        //hintMap.put(EncodeHintType.MARGIN,0);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //创建比特矩阵(位矩阵)的QR码编码的字符串
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hintMap);
        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();
        System.out.println(matrixWidth);
        BufferedImage image = new BufferedImage(matrixWidth-20, matrixWidth-20, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        // 1.0f为透明度 ，值从0-1.0，依次变得不透明
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.9999f));
        //背景色
        graphics.setColor(new Color(239, 239, 238));
//        graphics.setColor(new Color(255, 255, 255));
        graphics.setBackground(Color.BLUE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++){
            for (int j = 0; j < matrixWidth; j++){
                if (byteMatrix.get(i, j)){
                    graphics.fillRect(i-10, j-10, 1, 1);
                }
            }
        }
        return ImageIO.write(image, "JPEG", outputStream);
    }

    // 调用生成二维码
    public static void main(String[] args) throws IOException, WriterException {
        OutputStream out = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\123456.png"));
        String content = "突收此物甚为疑之\n" +
                "  -解之-\n" +
                "既符沉鱼落雁之容\n" +
                "又拥闭月羞花之貌\n" +
                "终悟之美女彦华也";
        createQrCode(out,content);
    }
}
