import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ Author：ke
 * @ Date： 2019/8/13 21:11
 * @ Version 1.0
 */

public class Demo00 {
    public static void main(String[] args) throws IOException {
        //生成一个简单的excel表格加内容
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("表一");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("我是第一行第一列");
        FileOutputStream os = new FileOutputStream("D:/test.xls");
        workbook.write(os);
        os.close();
    }

}
