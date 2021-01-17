package common.utils;
import io.qameta.allure.Step;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static common.logger.CustomLogger.logger;

public class ExcelWriter {

    @Step
    public static void writeToExcelFile(String var1, String var2, String var3) {
        String excelFilePath = "src/test/java/common/test_data/users/Users.xlsx";
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Object[][] dataToWrite = {
                    {var1, var2, var3},
            };
            int rowCount = sheet.getLastRowNum();
            for (Object[] data : dataToWrite) {
                Row row = sheet.createRow(++rowCount);
                int columnCount = 0;
                Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);
                for (Object field : data) {
                    cell = row.createCell(columnCount++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        logger.info("Name: " + var1 + " - Email: " + var3 + " - ok");
    }
}
