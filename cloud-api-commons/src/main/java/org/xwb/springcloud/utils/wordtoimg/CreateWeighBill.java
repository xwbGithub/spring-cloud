package org.xwb.springcloud.utils.wordtoimg;

import cn.hutool.core.io.resource.ClassPathResource;
import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;
import org.xwb.springcloud.utils.CreateImgUtils;
import org.xwb.springcloud.utils.FileToolUtils;
import org.xwb.springcloud.utils.StringUtils;

import java.io.File;
import java.util.UUID;

/**
 * @description 过磅单生成
 */
@Slf4j
public class CreateWeighBill {
    /**
     * 生成货主合同并上传到OSS
     */
    public static String getPartnerContract(String listUrl, String sendUrl, String receiveUrl) {
        Document firstDocument = null;
        // 合同地址
        String path = null;
        //目标word
        Document document = null;
        String folder = "/file/img";
        String folder1 = "/file/img1";
        try {
            //重新下载oss上的图片到本地，准备生成电子榜单做准备
            listUrl = FileToolUtils.downloadNetUrlImg(folder, listUrl);
            sendUrl = FileToolUtils.downloadNetUrlImg(folder, sendUrl);
            receiveUrl = FileToolUtils.downloadNetUrlImg(folder, receiveUrl);
            // Linux下使用
            //String path = this.getClass().getClassLoader().getResource("static/货主合同模板.docx").getPath();
            // Windows下使用
            ClassPathResource classPathResource = new ClassPathResource("static/weightBill.docx");
            File file = classPathResource.getFile();
            String absolutePath = file.getAbsolutePath();
            log.info("真实地址{}", absolutePath);
            document = new Document(file.getAbsolutePath());
            Range range = document.getRange();
            ReplaceSignPhoto hy = new ReplaceSignPhoto(listUrl, "cy", 120, 80, 10, HorizontalAlignment.RIGHT);
            range.replace("hy", "", new FindReplaceOptions(StringUtils.isNotEmpty(listUrl) ? hy : null));
            range.replace("time", "2021-12-23", new FindReplaceOptions());
            range.replace("ticketNumber", "12312", new FindReplaceOptions());
            range.replace("sendCompany", "百度科技", new FindReplaceOptions());
            range.replace("goodsCategory", "科技公司", new FindReplaceOptions());
            range.replace("goodsName", "货品名称", new FindReplaceOptions());
            range.replace("unit", "个", new FindReplaceOptions());
            range.replace("carNumber", "234234", new FindReplaceOptions());
            range.replace("weight", "40", new FindReplaceOptions());
            range.replace("receivedAddress", "上海市", new FindReplaceOptions());
            range.replace("driverPhone", "18888899888", new FindReplaceOptions());
            range.replace("driverName", "卡车", new FindReplaceOptions());
            ReplaceSignPhoto cy = new ReplaceSignPhoto(sendUrl, "cy", 120, 80, 10, HorizontalAlignment.CENTER);
            range.replace("cy", "", new FindReplaceOptions(StringUtils.isNotEmpty(sendUrl) ? cy : null));
            ReplaceSignPhoto sh = new ReplaceSignPhoto(receiveUrl, "sh", 120, 80, 10, HorizontalAlignment.CENTER);
            range.replace("sh", "", new FindReplaceOptions(StringUtils.isNotEmpty(receiveUrl) ? sh : null));
            firstDocument = document;
            String tempPath = folder1 + File.separator + UUID.randomUUID() + ".png";
            String result1 = CreateImgUtils.doc2pdf(firstDocument, tempPath, SaveFormat.PNG);
            log.info("本地电子榜单保存路径：{},保存结果：{}", tempPath, result1.toString());
            //上传阿里云
            firstDocument.cleanup();
        } catch (Exception e) {
            log.error("生成电子榜单失败：{}", e.getMessage());
        } finally {
            //删除下载的临时文件
            FileToolUtils.deleteFile(new File(folder));
        }
        return path;
    }

    public static void main(String[] args) {
        String listUrl = "https://img-home.csdnimg.cn/images/20201124032511.png";
        String sendUrl = "https://www.baidu.com/img/flexible/logo/pc/result.png";
        String receiveUrl = "https://csdnimg.cn/release/blogv2/dist/pc/img/original.png";
        String partnerContract = getPartnerContract(listUrl, sendUrl, receiveUrl);
        log.info("生成的地址：{}", partnerContract);
    }

}
