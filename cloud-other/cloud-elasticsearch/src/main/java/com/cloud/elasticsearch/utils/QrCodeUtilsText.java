package com.cloud.elasticsearch.utils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class QrCodeUtilsText {
    /**
     * 编码
     *
     * @param content   二维码内容
     * @param pressText 追加文字
     * @param imgPath   生成图片路径
     * @param width     宽
     * @param height    高度
     */
    public static void encode(String content, String pressText, int width, int height, String imgPath) throws WriterException {

        //设置二维码参数
        Map hints = new HashMap();

        //设置UTF-8， 防止中文乱码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        // 设置二维码四周白色区域的大小
        hints.put(EncodeHintType.MARGIN, 2);

        // 设置二维码的容错性
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        //
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        File file = new File(imgPath);

        if (!file.exists()) {
            file.mkdirs();
        }

        //获取二维码流
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        //追加文字
        pressText(pressText, bufferedImage, file);

        bufferedImage.flush();

    }
    /**
     * 编码
     *
     * @param pressText 追加文字
     * @param bufferedImage
     * @param file
     */
    public static void pressText(String pressText, BufferedImage bufferedImage, File file) {
        try {

            Font font = new Font("宋体", 12, 18);

            FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);

            //计算画布高度
            int height = bufferedImage.getHeight() + 20;

            for (String txt:pressText.split(";")) {
                //判断是否需要换行
                if(metrics.stringWidth(txt) >= bufferedImage.getWidth()-50){
                    int num = (int) Math.ceil(metrics.stringWidth(txt)/(bufferedImage.getWidth()-50.0)) ;
                    height += (5+metrics.getHeight()) * num ;
                }else {
                    height += 5+metrics.getHeight() ;
                }
            }


            log.info("height {}",height);

            //创建画布
            BufferedImage bi = new BufferedImage(bufferedImage.getWidth(), height, BufferedImage.TYPE_INT_RGB);

            //创建画笔
            Graphics2D g = bi.createGraphics();
            g.setBackground(Color.white);

            //填充
            g.fillRect(0, 0, bufferedImage.getWidth(), height);

            //设置绘制图像 0, 0 代表在画布长宽坐标分别是0，0开始
            g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);

            g.setColor(Color.black);

            g.setFont(font);

            /** 防止生成的文字带有锯齿 **/
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            double baseY = bufferedImage.getHeight();

            for (String txt:pressText.split(";")) {

                //判断是否需要换行
                if(metrics.stringWidth(txt) >= (bufferedImage.getWidth()-50)){

                    //设置换行次数
                    int num =  (int)Math.ceil(metrics.stringWidth(txt) / (bufferedImage.getWidth()-50.0)) ;

                    for (int i = 0; i < num; i++) {
                        baseY += 5 + metrics.getHeight();

                        int let = (int)Math.ceil(txt.length()/num) ;

                        int star = (let) * i ;
                        int end = (let) * (i + 1)  ;

                        String t = txt.substring(star,end) ;

                        double x = (bufferedImage.getWidth() - metrics.stringWidth(t)) / 2;

                        g.drawString(t, (int) x, (int) baseY);
                    }

                }else {
                    double x = (bufferedImage.getWidth() - metrics.stringWidth(txt)) / 2;
                    //设置字体内容 坐标x,y
                    baseY += 5 + metrics.getHeight();
                    g.drawString(txt, (int) x, (int) baseY );
                }
            }

            //释放对象
            g.dispose();

            ImageIO.write(bi, "png", file);

            bi.flush();

        } catch (Exception e) {
            log.info("二维码追加文字 err ", e);
        }
    }


    public static void main(String[] args) throws IOException, WriterException {
        encode("wioeuyr239084wefhw9eq8ouyrwerl", "新圳河（AJ-05) 2020/06/02 ; ", 450, 450, "C:\\Users\\Administrator" +
                "\\Desktop\\123456.png");
    }
}
