package common.utils;

import com.opencsv.CSVWriter;
import io.qameta.allure.Step;

import java.io.FileWriter;

public class CsvWriter {

    @Step
    public static void writeToCSVFile(String name, String email, String password, boolean append) {
        String[] data = new String[]{name, email, password};
        String pathToCSV = "src/test/java/common/test_data/users/Users.csv";
        try {
            CSVWriter write = new CSVWriter(new FileWriter(pathToCSV, append), ';',
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            write.writeNext(data);
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
