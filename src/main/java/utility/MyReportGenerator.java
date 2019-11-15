package main.java.utility;

import main.java.model.ExcelUtilModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.json.JSONObject;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Abhishek Jain
 */
public class MyReportGenerator {
    public void generateReport(List<ExcelUtilModel> excelUtilModelList) throws IOException {
        System.out.println(" Inside generateReport ");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Abhishek");

        XSSFCellStyle valueCellStyle = getStyleForMyReport(workbook);
        XSSFCellStyle editableStyle = workbook.createCellStyle();
        editableStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        editableStyle.setLocked(false);

        org.apache.poi.ss.usermodel.Row dataHeadingRow = sheet.createRow(0);
        List<JSONObject> headerColumns = getHeaderForMyReport(sheet);
        setHeaderForMyReport(workbook, sheet, valueCellStyle, dataHeadingRow, headerColumns);
        setDataInMyReport(excelUtilModelList, sheet, editableStyle);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("data/MyReport.xlsx");
            workbook.write(outputStream);
            System.out.println(" #### REPORT GENERATED SUCCESSFULLY #### ");
        } catch (IOException e) {
            System.out.println(" #### REPORT GENERATION FAILED!!! #### ");
            e.printStackTrace();
        }
        finally
        {
            outputStream.close();
        }
    }

    private void setHeaderForMyReport(XSSFWorkbook workbook, XSSFSheet sheet, XSSFCellStyle valueCellStyle, org.apache.poi.ss.usermodel.Row dataHeadingRow, List<JSONObject> headerColumns)
    {
        try {
            int index = 0;
            for (JSONObject headerColumn : headerColumns) {
                Cell cell = dataHeadingRow.createCell(index);
                if(headerColumn.opt("HEADER").toString() != null)
                    cell.setCellValue(headerColumn.opt("HEADER").toString());
                else
                    cell.setCellValue("");

                valueCellStyle.setFillForegroundColor(new XSSFColor(Color.YELLOW));
                valueCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

                cell.setCellStyle(valueCellStyle);
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<JSONObject> getHeaderForMyReport(XSSFSheet sheet)
    {
        List<JSONObject> headers = new ArrayList<JSONObject>();

        JSONObject obj = new JSONObject();
        obj.put("HEADER", "custid_pk");
        headers.add(obj);

        obj = new JSONObject();
        obj.put("HEADER", "name");
        headers.add(obj);

        obj = new JSONObject();
        obj.put("HEADER", "orderid");
        headers.add(obj);

        obj = new JSONObject();
        obj.put("HEADER", "price");
        headers.add(obj);

        obj = new JSONObject();
        obj.put("HEADER", "state");
        headers.add(obj);

        obj = new JSONObject();
        obj.put("HEADER", "type");
        headers.add(obj);

        obj = new JSONObject();
        obj.put("HEADER", "unit");
        headers.add(obj);

        return headers;
    }

    private XSSFCellStyle getStyleForMyReport(XSSFWorkbook workbook)
    {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setWrapText(true);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        return style;
    }

    private void setDataInMyReport(List<ExcelUtilModel> excelUtilModelList, XSSFSheet sheet, XSSFCellStyle editableStyle)
    {
        org.apache.poi.ss.usermodel.Row rowHeader;
        int rowIndex = 1;
        try {
            if(excelUtilModelList!=null)
            {
                for (ExcelUtilModel excelUtilModel : excelUtilModelList) {
                    if(excelUtilModel != null)
                    {
                        rowHeader = sheet.createRow(rowIndex++);
                        createRow(rowHeader, editableStyle, true, sheet,
                                excelUtilModel.getCustid_pk(),
                                excelUtilModel.getName(),
                                excelUtilModel.getOrderid(),
                                excelUtilModel.getPrice(),
                                excelUtilModel.getState(),
                                excelUtilModel.getType(),
                                excelUtilModel.getUnit());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int createRow(org.apache.poi.ss.usermodel.Row row, CellStyle cellStyle, boolean isAutosizeEnable, Sheet sheet, Object... values)
    {
        int i;
        for (i = 0;  i < values.length; i++) {
            try {
                Cell cell = row.createCell(i);
                writeValue(cell, values[i]);
                cell.setCellStyle(cellStyle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;

    }

    private static void writeValue(Cell cell, Object object)
    {
        if(object == null || object.equals(Double.NaN))
            cell.setCellValue("_");
        else if(object instanceof String)
            cell.setCellValue((String) object);
        else if(object instanceof Integer)
            cell.setCellValue((Integer) object);
        else if(object instanceof Long)
            cell.setCellValue((Long) object);
        else if(object instanceof Float)
            cell.setCellValue((Float) object);
        else if(object instanceof Date)
            cell.setCellValue((Date) object);
        else if(object instanceof Double)
            cell.setCellValue((Double) object);
        else if(object instanceof Boolean)
            cell.setCellValue((Boolean) object);
    }
}
