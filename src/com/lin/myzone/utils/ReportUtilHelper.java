package com.lin.myzone.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ReportUtilHelper {
	
	
	public static HSSFCellStyle genTitleStyle(HSSFWorkbook workbook){
		   HSSFCellStyle titleStyle = workbook.createCellStyle();
		   titleStyle.setFillForegroundColor(HSSFColor.PINK.index);
	       titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	       titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	       return titleStyle;
	}   
	
	public static HSSFCellStyle genColumn_titleStyle(HSSFWorkbook workbook){
		   HSSFCellStyle column_title = workbook.createCellStyle();
	       column_title.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	       column_title.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	       column_title.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	       column_title.setBorderTop(HSSFCellStyle.BORDER_THIN);
	       column_title.setBorderRight(HSSFCellStyle.BORDER_THIN);
	       column_title.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	       column_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	       column_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	       column_title.setWrapText(true);
	       return column_title;
	}
	
	public static HSSFCellStyle genColumn_valueStyle(HSSFWorkbook workbook){
		   HSSFCellStyle column_value = workbook.createCellStyle();
	       column_value.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	       column_value.setBorderTop(HSSFCellStyle.BORDER_THIN);
	       column_value.setBorderRight(HSSFCellStyle.BORDER_THIN);
	       column_value.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	       column_value.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	       column_value.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	       column_value.setWrapText(true);
	       return column_value;
	}
	
	

}
