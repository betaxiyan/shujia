package com.bonc.nerv.tioa.week.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set; 
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangwen
 */
public class PoiUtils {
    
    /**
     * xls文档
     */
    private static final String XLS = "xls";

    /**
     * xlsx文档
     */
    private static final String XLSX = "xlsx";
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
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(title);
		
		sheet.setDefaultColumnWidth(15); // 设置表格默认列宽度为15个字节  
		XSSFCellStyle headStyle = createHeadStyle(workbook); // 生成头部样式 
		XSSFCellStyle commonDataStyle = createCommonDataStyle(workbook); // 生成一般数据样式  

		if(headers == null || headers.length <= 0){
			return;
		}
		
		XSSFRow row = sheet.createRow(0); // 产生表格标题行  
		for (int i = 0; i < headers.length; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			XSSFRichTextString text = new XSSFRichTextString(headers[i]);
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
				XSSFCell cell = row.createCell(i);  
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
	private static void mergedRegion(XSSFSheet sheet, int cellLine,int startRow, int endRow, XSSFWorkbook workbook, Integer[] mergeBasis) {
		XSSFCellStyle style = workbook.createCellStyle(); 			// 样式对象  
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);	// 垂直  
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);				// 水平  
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
	private static XSSFCellStyle createHeadStyle(XSSFWorkbook workbook){
		//标题单元格样式
		XSSFCellStyle headStyle = workbook.createCellStyle();   
		headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());  
		headStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  
		headStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
		headStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
		headStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  
		headStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  
		headStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);  
		//标题单元格字体  
		XSSFFont headFont = workbook.createFont();  
		headFont.setColor(IndexedColors.BLACK.getIndex());  
		headFont.setFontHeightInPoints((short) 12);  
		headFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
		// 把字体应用到当前的样式  
		headStyle.setFont(headFont);  
		return headStyle;
	}
	
	/**
	 * 普通数据单元格样式 
	 * @param workbook
	 * @return
	 */
	private static XSSFCellStyle createCommonDataStyle(XSSFWorkbook workbook){
		//普通数据单元格样式 
		XSSFCellStyle commonDataStyle = workbook.createCellStyle();  
		commonDataStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());  
		commonDataStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  
		commonDataStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  
		commonDataStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  
		commonDataStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  
		commonDataStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  
		commonDataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);  
		commonDataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
		//普通数据单元格字体  
		XSSFFont commonDataFont = workbook.createFont();  
		commonDataFont.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);  
		//把字体应用到当前的样式  
		commonDataStyle.setFont(commonDataFont); 
		return commonDataStyle;
	}
	
	
	  /**
     * 读取Excel文档
     * @param file  
     * @return list
     * @throws IOException 
     * @see
     */
    public static List<String[]> readExcel(MultipartFile file)
        throws IOException {

        //检查文件是否存在
        checkFile(file);

        //获得工作簿对象
        Workbook workbook = getWorkBook(file);

        //创建返回对象，把每行的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();

        if (workbook != null) {
            // for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){  
            //获得当前sheet工作表  
            Sheet sheet = workbook.getSheetAt(0);
            /*if(sheet == null){  
                continue;  
            }*/
            //获得当前sheet的开始行  
            int firstRowNum = sheet.getFirstRowNum();

            //获得当前sheet的结束行  
            int lastRowNum = sheet.getLastRowNum();
            //循环除了第二行的所有行  
            for (int rowNum = firstRowNum + 2; rowNum <= lastRowNum; rowNum++ ) {
                //获得当前行  
                Row row = sheet.getRow(rowNum);
                if (row.getCell(0) == null || row.getCell(0).equals("")) {
                    break;
                }
                //获得当前行的开始列  
                int firstCellNum = row.getFirstCellNum();
                //获得当前行的列数  
                int lastCellNum = row.getPhysicalNumberOfCells();
                String[] cells = new String[row.getPhysicalNumberOfCells()];
                //循环当前行  
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++ ) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell);
                }
                list.add(cells);
            }
            //}  
            //workbook.close();
        }
        return list;
    }

    /**
     * 判断文件是否存在
     * @param file  
     * @throws IOException 
     * @see
     */
    public static void checkFile(MultipartFile file)
        throws IOException {
        //判断文件是否存在  
        if (null == file) {
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名  
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件  
        if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            throw new IOException(fileName + "不是excel文件");
        }
    }

    /**
     * 获得Workbook对象
     * @param file  

     * @return workbook
     * @see
     */
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名  
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;
        try {
            //获取excel文件的io流  
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if (fileName.endsWith(XLS)) {
                //2003  
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(XLSX)) {
                //2007  
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {}
        return workbook;
    }

    /**
     * 
     * @param cell 
     * @return String
     * @see
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况  
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型  
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字  
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串  
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式  
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
