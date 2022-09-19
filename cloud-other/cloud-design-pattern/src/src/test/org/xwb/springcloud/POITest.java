package org.xwb.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 */
@Slf4j
public class POITest {

    private static String fileName = "E:\\dataDictExport.xlsx";
    private static String newFileName = "E:\\dataDictExportNew.xlsx";


    public static void main(String[] args) {
        Map<Integer, String> integerStringMap = readExcel();
//        writeExcel(integerStringMap);
    }

    private InputStream getInputStream(File fileName) {
        InputStream fis = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("");
            fis = classPathResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fis;
    }


    public static Map<Integer, String>  readExcel(){

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //读取工作簿
        XSSFWorkbook workBook = null;
        try {
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取工作表
        XSSFSheet sheet = workBook.getSheetAt(0);
        //最后一行
        Map<Integer, String> list = new HashMap<>();
        StringBuffer sb = null;
        int lastRowNum = sheet.getLastRowNum() + 1;
        for (int i = 0; i < lastRowNum; i++) {
            sb = new StringBuffer();
            //读取行
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 5; j++) {
                //读取单元格
                XSSFCell cell = row.getCell(j);
                try {
                    String value = cell.getStringCellValue();
                    sb.append(value + "#");
                } catch (Exception e) {
                    sb.append("#");
                }
                list.put(i, sb.toString());
            }
        }
        try {
            inputStream.close();//关闭工作簿
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, String> integerStringMap = setValue(list);

//        //遍历
        for (Integer key : integerStringMap.keySet()) {
            String value=integerStringMap.get(key);
            String[] split = value.split("#");
            System.out.println("insert into area(id,name,parent_id,level) values("+split[0]+",'"+split[1]+"',"+split[2]+
                    ","+split[3]+");");
        }
        return integerStringMap;
    }

    public static Map<Integer, String> setValue(Map<Integer, String> list) {
        Map<Integer, String> list1 = new HashMap<>();
        for (Integer key : list.keySet()) {
            String[] split = list.get(key).split("#");
            String code = split[2];
            int level = getLevel(split[2]);
            String parentCode = getCode(code, level);
            list1.put(key,split[0]+"#"+split[1]+"#"+parentCode+"#"+level);
        }
        return list1;
    }

    public static int getLevel(String s) {
        String[] split1 = s.split("\\.");
        int i = split1.length;
        int s1=i == 1 ? 0 : i == 2 ? 1 : i == 3 ? 2 :i==4?3: 1;
        return s1;
    }

    public static String getCode(String s, int level) {
        String[] split = s.split("\\.");
        if (0 == level) {
            return 100000+"";
        } else if (1 == level) {
            return split[0];
        } else if (2 == level) {
            return split[1];
        } else if (3 == level) {
            return split[2];
        }else{
            return "";
        }
    }

    /**
     * 写入excel
     *
     * @throws IOException
     */
    public void writeExcel(Map<Integer, String> integerStringMap) throws IOException {
        String fileName = newFileName;
        //创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = workBook.createSheet("helloWorld");
        //创建行
        XSSFRow row = sheet.createRow(0);
        //创建单元格，操作第三行第三列
        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("helloWorld");

        FileOutputStream outputStream = new FileOutputStream(new File(fileName));
        workBook.write(outputStream);

        workBook.close();//记得关闭工作簿
    }
}
