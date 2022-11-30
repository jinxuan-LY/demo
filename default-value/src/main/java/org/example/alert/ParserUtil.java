package org.example.alert;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.alert.target.StdRuleDTO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

            // 按照服务名分组写入excel
            int sheetNum = 1;
            Map<String, List<StdRuleDTO>> ruleMap = rules.stream().collect(Collectors.groupingBy(StdRuleDTO::getServiceName));
            TreeMap<String, List<StdRuleDTO>> ruleMapSorted = new TreeMap<>(ruleMap);

            for (Map.Entry<String, List<StdRuleDTO>> entry : ruleMapSorted.entrySet()) {
                String key = entry.getKey();
                if (StringUtils.containsAny(entry.getKey(), "[")) {
                    key = "common" + sheetNum++;
                }
                Sheet sheet = wb.createSheet(key);
                writeDetails(wb, sheet, entry.getValue());
            }

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
    private static void writeDetails(XSSFWorkbook wb, Sheet sheet, List<StdRuleDTO> stdRuleDTOList) {
        int columnIndex = 0;
        sheet.setColumnWidth(columnIndex++, 20 * 256);
        sheet.setColumnWidth(columnIndex++, 20 * 256);
        sheet.setColumnWidth(columnIndex++, 3 * 20 * 256);
        sheet.setColumnWidth(columnIndex++, 20 * 256);
        sheet.setColumnWidth(columnIndex++, 4 * 20 * 256);
        sheet.setColumnWidth(columnIndex++, 10 * 256);
        sheet.setColumnWidth(columnIndex++, 40 * 256);
        sheet.setColumnWidth(columnIndex++, 30 * 256);
        sheet.setColumnWidth(columnIndex++, 10 * 256);
        sheet.setColumnWidth(columnIndex++, 10 * 256);

        int rowNum = 0;
        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        List<String> colList = new ArrayList<>();
        colList.add("规则ID");
        colList.add("服务名称");
        colList.add("规则名称");
        colList.add("告警等级");
        colList.add("表达式");
        colList.add("持续时间");
        colList.add("规则说明");
        colList.add("告警群");
        colList.add("电话告警");
        colList.add("状态");
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
                Cell cell = detailRow.createCell(cellNum++);
                cell.setCellValue(item.getRuleId());

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

                Cell cell4_1 = detailRow.createCell(cellNum++);
                cell4_1.setCellValue("");

                Cell cell5 = detailRow.createCell(cellNum++);
                cell5.setCellValue(item.getAlertGroup());

                Cell cell5_1 = detailRow.createCell(cellNum++);
                cell5_1.setCellValue(item.getCall());

                Cell cell6 = detailRow.createCell(cellNum++);
                cell6.setCellValue(item.getStatus());
            }
        }
    }
}
