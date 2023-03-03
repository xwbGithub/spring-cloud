package org.xwb.springcloud.utils.document;

import cn.hutool.core.util.ObjectUtil;
import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.xwb.springcloud.utils.CreateImgUtils;
import org.xwb.springcloud.utils.DateUtils;
import org.xwb.springcloud.utils.FileToolUtils;
import org.xwb.springcloud.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description 生成单据工具类
 */
@SuppressWarnings("ALL")
@Slf4j
public class ElectBillUtils {
    public static final String base_url = "/mtl/doc";
    //装车卸车电子榜单
    public static final String LOADING_UNLOAD = "/mtl/docFile/weightBill.docx";
    //软件榜单
    //public static final String SOFTWARE = "/mtl/docFile/software.docx";
    //提货单
    public static final String PICKUP = "/mtl/docFile/pickup.docx";
    //过磅单
    public static final String WEIGHS = "/mtl/docFile/weights.docx";
    //装车单
    public static final String LOADING = "/mtl/docFile/loading.docx";
    //卸货单
    public static final String UNLOADING = "/mtl/docFile/unloading.docx";
    //入库单
    public static final String INPUT_BOUND = "/mtl/docFile/inputBound.docx";
    //出库单
    public static final String OUT_BOUND = "/mtl/docFile/outBound.docx";
    //货物清单
    public static final String GOODS_LIST = "/mtl/docFile/goodsList.docx";
    //计划单
    public static final String PLAN = "/mtl/docFile/plan.docx";


    /**
     * 塞入的值不用判断是否为空，直接装换为字符串或者是指定图片还是字符串即可
     *
     * @param docBackFile 原始模板文件
     * @param list        要组装的数据文件
     * @return 返回生成的url图片文件
     */
    public static String createCommon(String docBackFile, List<DocField> list) {
        log.info("===============================");
        log.info("生成的基础业务数据：{}", list.toString());
        log.info("===============================");
        String path = null;
        String folder = base_url;
        FindReplaceOptions fro = ElectBillUtils.instanceFro();
        try {
            Document document = new Document(docBackFile);
            Range range = document.getRange();
            if (ObjectUtils.isNotEmpty(list) && list.size() > 0) {
                for (DocField doc : list) {
                    String field = doc.getField();
                    String value = doc.getValue();
                    String type = doc.getType();
                    int typeOfLocation = doc.getTypeOfLocation();
                    //字符串
                    if ("1".equals(type)) {
                        range.replace(field, ElectBillUtils.getStrValue(value), fro);
                    } else {
                        //该值是图片类型
                        String phone = StringUtils.isEmpty(value) ? null : FileToolUtils.downloadNetUrlImg(folder,
                                value);
                        ElectBillUtils.setImg(range, phone, "" + field + "", 0, 0, 0, typeOfLocation);
                    }
                }
            }
            String tempPath = folder + File.separator + UUID.randomUUID().toString() + ".png";
            path = ElectBillUtils.saveImgUploadOss(document, tempPath);
            log.info("本地地址：{},上传后oss地址：{}", tempPath, path);
            document.cleanup();
        } catch (Exception e) {
            log.error("生成电子榜单失败：{}", e.getMessage());
        } finally {
            //删除下载的临时文件
            //FileToolUtils.deleteFile(new File(folder));
        }
        return path;
    }

    /**
     * 保存document到本地,然后上传到oss阿里云服务器上
     *
     * @param document      word文档对象
     * @param fileLocalPath 要保存的本地的文件路径url
     * @return 保存到阿里云的服务器返回的oss地址
     */
    public static String saveImgUploadOss(Document document, String fileLocalPath) {
        String saveLocalPath = CreateImgUtils.doc2pdf(document, fileLocalPath, SaveFormat.PNG);
        log.info("保存结果：{}", saveLocalPath);
        //上传阿里云
        String path = saveOssFile(fileLocalPath);
        log.info("上传阿里云保存路径：{}", path);
        return path;
    }

    /**
     * 保存文件到本地
     *
     * @param file     文件
     * @param fileName 文件名称
     * @return 返回保存的路径
     */

    public static String saveFileByDirectory(MultipartFile file, String fileName) {
        try {
            // 复制文件
            File targetFile = new File(base_url, fileName);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
            return targetFile.getAbsolutePath();
        } catch (IOException e) {
            log.error("保存文件到服务器（本地）失败", e);
        }
        return null;
    }

    /**
     * 新创建
     *
     * @return
     */
    public static FindReplaceOptions instanceFro() {
        return new FindReplaceOptions();
    }

    /**
     * @param range   绘制图片的段落对象
     * @param fileUrl 本地下载下来的文件路径
     * @param field   替换字段
     * @param width   图片本身的宽度
     * @param height  图片本身的高度
     * @param left    距离左边的像素距离
     * @param type    对齐方式：0-7(默认为2) {@link HorizontalAlignment}
     *                <p>
     *                <br/>0 NONE | DEFAULT<br/>1：LEFT<br/>2 CENTER<br/>3 RIGHT<br/>4 INSIDE<br/>5 OUTSIDE<br/>
     *                </pre>
     */

    public static void setImg(Range range, String fileUrl, String field, int width, int height, int left, int type) {
        try {
            if (StringUtils.isNotEmpty(fileUrl)) {
                width = ObjectUtils.isEmpty(width) || 0 == width ? 120 : width;
                height = ObjectUtils.isEmpty(height) || 0 == height ? 80 : height;
                left = ObjectUtils.isEmpty(left) || 0 == left ? 80 : left;
                //此处的right也是默认的3
                type = ObjectUtils.isEmpty(type) ? HorizontalAlignment.CENTER : type;
                ReplaceSignPhoto rangeImg = new ReplaceSignPhoto(fileUrl, field, width, height, left, type);
                range.replace(field, "", new FindReplaceOptions(rangeImg));
            } else {
                range.replace(field, "", new FindReplaceOptions());
            }
        } catch (Exception e) {
            log.error("绘制图片异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 返回默认值str值
     *
     * @param str 传入的值
     * @return 返回判断后的值
     */

    public static String getStrValue(String str) {
        return StringUtils.isNotEmpty(str) ? str : "";
    }

    /**
     * 返回时间的返回值
     *
     * @param time   时间值
     * @param format 格式化样式
     * @return 返回格式化后的值
     */


    public static String getTime(Date time, String format) {
        format = StringUtils.isEmpty(format) ? "yyyy年MM月dd日-HH时mm分ss秒" : format;
        String orderTime = ObjectUtil.isNotEmpty(time) ? DateUtils.parseDateToStr(format, time) : "";
        log.info("时间：{}", orderTime);
        return orderTime;
    }

    public static String getBigDecimal(BigDecimal data) {
        if (ObjectUtil.isNotEmpty(data) && data.compareTo(BigDecimal.ZERO) > 0) {
            return data.toString();
        }
        return "";
    }

    /**
     * 保存文件到本地
     *
     * @param url 本地url的地址
     * @return 返回阿里云保存的地址url
     */
    public static String saveOssFile(String url) {
        try {
            File file = new File(url);
            FileInputStream inputStream = new FileInputStream(file);
            String fileName = file.getName();
            MockMultipartFile mf = new MockMultipartFile(fileName, fileName, ContentType.IMAGE_JPEG.toString(),
                    inputStream);
            //本地生成地址
            String savePath = saveFileByDirectory(mf, File.separator + "0000000000000" + ".png");
            return savePath;
        } catch (Exception e) {
            log.error("上传阿里云文件异常:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
