package com.bonc.nerv.tioa.week.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author zhangwen
 */
public class PoiUtils {
	/**
	 * @param fileName 文件名称
	 * @param headers 表头
	 * @param dataset 数据集
	 * @param isSortDataSet 是否对数据排序
	 * @param response HttpServletResponse
	 * @param mergeBasis 合并基准列 可选
	 * @param mergeCells 要合并的列 可选
	 * @param sumCellsMap 要求和的列 可选
	 * @param timeCells 时间列 可选
	 * @throws IOException
	 */
	public static void exportExelMerge(String fileName,final String[] headers,List<String[]> dataset,boolean isSortDataSet,HttpServletResponse response, final Integer[] mergeBasis, final Integer[] mergeCells, final Integer[] sumCells, final Integer[] timeCells)
	    throws IOException{
		String title = "Sheet1";
		response.setContentType("application/vnd.ms-excel;charset=utf-8"); 
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		
		createExcelMerge(title,headers,dataset,isSortDataSet,response.getOutputStream(),mergeBasis,mergeCells,sumCells,timeCells);
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.flushBuffer();
	}
	
	/**
	 * @param title 文件名称
	 * @param headers 表头
	 * @param dataset 数据集
	 * @param isSortDataSet 是否对数据排序
	 * @param out OutputStream
	 * @param mergeBasis 合并基准列 可选
	 * @param mergeCells 要合并的列
	 * @param sumCells 要求和的列
	 * @param timeCells 时间列 可选
	 */
	public static void createExcelMerge(String title, final String[] headers,List<String[]> dataset,boolean isSortDataSet, OutputStream out, final Integer[] mergeBasis, final Integer[] mergeCells, final Integer[] sumCells, final Integer[] timeCells){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(title);
		
		sheet.setDefaultColumnWidth(15); // 设置表格默认列宽度为15个字节  
		HSSFCellStyle headStyle = createHeadStyle(workbook); // 生成头部样式 
		HSSFCellStyle commonDataStyle = createCommonDataStyle(workbook); // 生成一般数据样式  

		if(headers == null || headers.length <= 0){
			return;
		}
		
		HSSFRow row = sheet.createRow(0); // 产生表格标题行  
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		if(isSortDataSet && mergeBasis != null && mergeBasis.length > 0){ //是否排序数据
			Collections.sort(dataset, new Comparator<String[]>() {
				public int compare(String[] o1, String[] o2) {
					String s1 = "";
					String s2 = "";
					for(int i = 0 ; i < mergeBasis.length ; i++){
						s1+=(o1[mergeBasis[i].intValue()]+Character.valueOf((char)127).toString());
						s2+=(o2[mergeBasis[i].intValue()]+Character.valueOf((char)127).toString());
					}
					if(timeCells != null && timeCells.length > 0){
						for(int i = 0 ; i < timeCells.length ; i++){
							s1+= o1[timeCells[i].intValue()];
							s2+= o2[timeCells[i].intValue()];
						}
					}
					if(s1.compareTo(s2) < 0){
						return -1;
					}else if(s1.compareTo(s2) == 0){
						return 0;
					}else{
						return 1;
					}
				}
	        });
		}
		// 遍历集合数据，产生数据行  
		Iterator<String[]> it = dataset.iterator();  
		int index = 0;  
		while (it.hasNext()) {
			index++;  
			row = sheet.createRow(index);  
			String[] dataSources = it.next() ;
			for (int i = 0; i < dataSources.length; i++) {  
				HSSFCell cell = row.createCell(i);  
				cell.setCellStyle(commonDataStyle);
				cell.setCellValue(dataSources[i]);
			}
		}  
		try {  
			if(mergeBasis != null && mergeBasis.length > 0 && mergeCells != null && mergeCells.length > 0){
				for(int i = 0 ; i < mergeCells.length ; i++){
					mergedRegion(sheet,mergeCells[i],1,sheet.getLastRowNum(),workbook,mergeBasis);
				}
			}
			workbook.write(out);  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
    /**
	 * 合并单元格
	 * @param sheet
	 * @param cellLine
	 * @param startRow
	 * @param endRow
	 * @param workbook
	 * @param mergeBasis
	 */
	private static void mergedRegion(HSSFSheet sheet, int cellLine,int startRow, int endRow, HSSFWorkbook workbook, Integer[] mergeBasis) {
		HSSFCellStyle style = workbook.createCellStyle(); 			// 样式对象  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	// 垂直  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);				// 水平  
        String s_will = sheet.getRow(startRow).getCell(cellLine).getStringCellValue();  // 获取第一行的数据,以便后面进行比较  
        int count = 0;  
        Set<Integer> set = new HashSet<Integer>();
        CollectionUtils.addAll(set, mergeBasis);
        for (int i = 2; i <= endRow; i++) {  
        	String s_current = sheet.getRow(i).getCell(cellLine).getStringCellValue();  
            if (s_will.equals(s_current)) { 
            	boolean isMerge = true;
            	if(!set.contains(cellLine)){//如果不是作为基准列的列 需要所有基准列都相同
	            	for(int j = 0 ; j < mergeBasis.length ; j++){
		            	if(!sheet.getRow(i).getCell(mergeBasis[j]).getStringCellValue()
		            			.equals(sheet.getRow(i-1).getCell(mergeBasis[j]).getStringCellValue())){
		            		isMerge = false;
		            	}
	            	}
            	}else{//如果作为基准列的列 只需要比较列号比本列号小的列相同
            		for(int j = 0 ; j < mergeBasis.length && mergeBasis[j] < cellLine ; j++){
		            	if(!sheet.getRow(i).getCell(mergeBasis[j]).getStringCellValue()
		            			.equals(sheet.getRow(i-1).getCell(mergeBasis[j]).getStringCellValue())){
		            		isMerge = false;
		            	}
	            	}
            	}
            	if(isMerge){
            		count++;
            	}else{
            		sheet.addMergedRegion(new CellRangeAddress( startRow, startRow+count,cellLine , cellLine));
            		startRow = i;  
                    s_will = s_current;
                    count = 0;
            	}
            } else {  
            	sheet.addMergedRegion(new CellRangeAddress( startRow, startRow+count,cellLine , cellLine));  
            	startRow = i;  
                s_will = s_current;
                count = 0;
            }  
            if (i == endRow && count > 0) {  
                sheet.addMergedRegion(new CellRangeAddress(startRow,startRow+count ,cellLine , cellLine));  
            }  
        }  
	}

	/**
	 * 标题单元格样式
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle createHeadStyle(HSSFWorkbook workbook){
		//标题单元格样式
		HSSFCellStyle headStyle = workbook.createCellStyle();   
		headStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		//标题单元格字体  
		HSSFFont headFont = workbook.createFont();  
		headFont.setColor(HSSFColor.BLACK.index);  
		headFont.setFontHeightInPoints((short) 12);  
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		// 把字体应用到当前的样式  
		headStyle.setFont(headFont);  
		return headStyle;
	}
	
	/**
	 * 普通数据单元格样式 
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle createCommonDataStyle(HSSFWorkbook workbook){
		//普通数据单元格样式 
		HSSFCellStyle commonDataStyle = workbook.createCellStyle();  
		commonDataStyle.setFillForegroundColor(HSSFColor.WHITE.index);  
		commonDataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		commonDataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		commonDataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		commonDataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		commonDataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		commonDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		commonDataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		//普通数据单元格字体  
		HSSFFont commonDataFont = workbook.createFont();  
		commonDataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
		//把字体应用到当前的样式  
		commonDataStyle.setFont(commonDataFont); 
		return commonDataStyle;
	}
	
}
