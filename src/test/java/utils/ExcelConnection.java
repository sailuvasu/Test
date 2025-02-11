package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConnection {

    public static HashMap<String, String> getTestData() {
        HashMap<String, String> mp = new HashMap();
        try {
            File file = new File(System.getProperty("user.dir") + "/TestData.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
                //System.out.println(sheet.getRow(0).getCell   (i).getStringCellValue());
                mp.put(sheet.getRow(0).getCell(i).getStringCellValue(), sheet.getRow(1).getCell(i).getStringCellValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mp;
    }

    public static String getData(String colName) {
        String data = publicVariables.data.get(colName);
        return data;
    }

}

