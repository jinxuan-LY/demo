package org.example.alert;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.alert.target.StdRuleDTO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanqiang.liao
 * @description TODO
 * @createTime 2022-11-30 00:18:00
 */
public class ParserUtil {


    public static String getStringFromFile(File file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);

            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write2Excel(List<StdRuleDTO> rules, String targetPath, String fileName) {
        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream out = null;
        fileName = fileName + ".xlsx";
        String filePath = targetPath + fileName;
        try {
            //删除之前的文件
            Files.deleteIfExists(Paths.get(filePath));
            writeDetails(wb, rules);
            out = new FileOutputStream(filePath);
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                wb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在EXCEL文件头添加概述信息
     *
     * @param wb
     * @param stdRuleDTOList
     */
    private static void writeDetails(XSSFWorkbook wb, List<StdRuleDTO> stdRuleDTOList) {
        Sheet sheet = wb.createSheet();
        sheet.setColumnWidth(0, 2 * 20 * 256);
        sheet.setColumnWidth(1, 4 * 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 4 * 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);

        List<String> strList = new ArrayList<>();

        int rowNum = 0;

        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        List<String> colList = new ArrayList<>();
        colList.add("serviceName");
        colList.add("displayName");
        colList.add("severity");
        colList.add("expression");
        colList.add("duration");
        colList.add("alertGroup");
        colList.add("status");
        Row titleRow = sheet.createRow(rowNum++);
        for (int cellNum = 0; cellNum < colList.size(); cellNum++) {
            Cell cell = titleRow.createCell(cellNum);
            cell.setCellValue(colList.get(cellNum));
            cell.setCellStyle(titleStyle);
        }

        if (CollectionUtils.isNotEmpty(stdRuleDTOList)) {
            for (StdRuleDTO item : stdRuleDTOList) {
                Row detailRow = sheet.createRow(rowNum++);
                int cellNum = 0;
                Cell cell0 = detailRow.createCell(cellNum++);
                cell0.setCellValue(item.getServiceName());

                Cell cell1 = detailRow.createCell(cellNum++);
                cell1.setCellValue(item.getDisplayName());

                Cell cell2 = detailRow.createCell(cellNum++);
                cell2.setCellValue(item.getSeverity());

                Cell cell3 = detailRow.createCell(cellNum++);
                cell3.setCellValue(item.getExpression());

                Cell cell4 = detailRow.createCell(cellNum++);
                cell4.setCellValue(item.getDuration());

                Cell cell5 = detailRow.createCell(cellNum++);
                cell5.setCellValue(item.getAlertGroup());

                Cell cell6 = detailRow.createCell(cellNum++);
                cell6.setCellValue(item.getStatus());
            }
        }
    }
}
