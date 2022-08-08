package teamHTBP.vida.datagen.processor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author DustW
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcelDataProcessor {
    /**
     * 处理 excel 文件
     * @param file     文件本身
     * @param coNames  需要匹配的列名表
     * @param consumer 对每条合格的数据进行处理
     */
    public static void processor(File file, List<String> coNames, BiConsumer<String, String[]> consumer) throws IOException, InvalidFormatException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.rowIterator();

                Row first = rows.next();

                Iterator<Cell> cells = first.cellIterator();

                List<Integer> cols = new ArrayList<>();

                while (cols.size() != coNames.size()) {
                    Cell cell = cells.next();

                    if (cell.getStringCellValue().isEmpty()) {
                        break;
                    }

                    if (coNames.contains(cell.getStringCellValue())) {
                        cols.add(cell.getColumnIndex());
                    }
                }

                while (rows.hasNext()) {
                    Row row = rows.next();

                    String[] processorData = new String[cols.size()];

                    for (int i1 = 0; i1 < cols.size(); i1++) {
                        processorData[i1] = row.getCell(cols.get(i1)).getStringCellValue();
                    }

                    if (Arrays.stream(processorData).anyMatch(String::isEmpty)) {
                        break;
                    }

                    consumer.accept(sheet.getSheetName(), processorData);
                }
            }
        }
    }

    public static File excel(String sub) {
        return new File("../excel/" + sub);
    }
}
