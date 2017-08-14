/*
 * 文件名：ExcelAnalyseServiceImpl.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：yuanpeng
 * 修改时间：2017年8月14日
 */

package com.bonc.nerv.tioa.week.service.impl;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bonc.nerv.tioa.week.dao.CpuMemoryMidDao;
import com.bonc.nerv.tioa.week.entity.CpuMemoryMidEntity;
import com.bonc.nerv.tioa.week.dao.ResUsaMidDao;
import com.bonc.nerv.tioa.week.entity.ResourceUsageMidEntity;
import com.bonc.nerv.tioa.week.service.ExcelAnalyseService;
import com.bonc.nerv.tioa.week.util.POIUtil;


/**
 * Excel分析服务类
 * 
 * @author yuanpeng
 * @version 2017年8月14日
 * @see ExcelAnalyseServiceImpl
 * @since
 */
@Service("excelAnalyseService")
public class ExcelAnalyseServiceImpl implements ExcelAnalyseService {
    
    /**
     * CpuMemoryMidDao
     */
    @Autowired
    private CpuMemoryMidDao cpuMemoryMidDao;

    /**
     * ResourceUsageMidEntity的Dao
     */
    @Autowired
    private ResUsaMidDao resUsaMidDao;

    /* (non-Javadoc)
     * @see com.bonc.nerv.tioa.week.service.ExcelAnalyseService#analyseOrcelExcel()
     * @author xiyan
     */
    @SuppressWarnings("finally")
    @Override
    public List<ResourceUsageMidEntity> analyseOrcelExcel(Workbook workbook)   {
        List<ResourceUsageMidEntity> list = new ArrayList<ResourceUsageMidEntity>();
        //获取第一张Sheet表   
        Sheet readsheet = workbook.getSheetAt(0); 
        //获得当前sheet的开始行  
        int firstRowNum  = readsheet.getFirstRowNum();  
        //获得当前sheet的结束行  
        int lastRowNum = readsheet.getLastRowNum();  
            
        //循环除了第一行的所有行,此处不加一即可不跳过第一行
        //修改之处。
        for(int rowNum = firstRowNum+1;rowNum < lastRowNum;rowNum++){  
            try{
                //获得当前行  
                Row row = readsheet.getRow(rowNum);  
                if(row == null){  
                    continue;  
                }  
                ResourceUsageMidEntity resourceUsageMidEntity = new ResourceUsageMidEntity();
                Cell cell1 = row.getCell(0);  
                Cell cell2 = row.getCell(5);  
                resourceUsageMidEntity.setKeyword(cell1.toString());
                resourceUsageMidEntity.setStorageUsage(Double.parseDouble(cell2.toString()));
                resourceUsageMidEntity.setType("Orcal");
                list.add(resourceUsageMidEntity);  
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                continue;
            }
        }   
        return list;
    }

    @Override
    public void orcalToDb(List<ResourceUsageMidEntity> reMidEntities) {
        resUsaMidDao.save(reMidEntities);

    }

    @SuppressWarnings("finally")
    @Override
    public  List<ResourceUsageMidEntity> analyseFtpExcel(Workbook workbook) {
        List<ResourceUsageMidEntity> list = new ArrayList<ResourceUsageMidEntity>();
        //Sheet的下标是从0开始   
        //获取第3张Sheet表   
        Sheet readsheet = workbook.getSheetAt(2); 
        //获得当前sheet的开始行  
        int firstRowNum  = readsheet.getFirstRowNum();  
        //获得当前sheet的结束行  
        int lastRowNum = readsheet.getLastRowNum();  
        //循环第一行到倒数第1行
        for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
            try {
                //获得当前行  
                Row row = readsheet.getRow(rowNum);  
                if(row == null){  
                    continue;  
                }  
                String cellstring = row.getCell(0).toString(); 
                if (cellstring.length()>=90) {//获取长度大于90的行   
                    StringBuffer stringBuffer = new StringBuffer(cellstring.substring(90));
                    while (stringBuffer.charAt(0)==' '){    //字符仍然为空
                        //减去为空的部分
                        stringBuffer = stringBuffer.deleteCharAt(0);
                    }
                    String string1 = null;   //数值
                    String string2 = null;   //路径
                    for(int i=0;i<stringBuffer.length();i++){
                        if (stringBuffer.charAt(i)=='/') {
                            string1 = stringBuffer.substring(0,i-1);//如果为0 只有1 位。
                            string2 = stringBuffer.substring(i,stringBuffer.length());
                            break;
                        }
                    }
                    ResourceUsageMidEntity resourceUsageMidEntity = new ResourceUsageMidEntity();
                    resourceUsageMidEntity.setKeyword(string2);
                    resourceUsageMidEntity.setType("FTP");
                    if (!string1.equals("0")) {   //String1 !=0
                        //对数值继续处理
                        String str1 = string1.substring(0,string1.length()-1);
                        char X = string1.charAt(string1.length()-1);
                        Double useing = Double.parseDouble(str1);
                        if (X == 'K') {
                            useing = useing/1024/1024;
                        }else if (X=='M') {
                            useing = useing/1024;
                        }else if (X=='t') {
                            useing = useing*1024;
                        }
                        resourceUsageMidEntity.setStorageUsage(useing);
                    }
                    else {//String1 ==0
                        resourceUsageMidEntity.setStorageUsage(0.0);
                    }
                    list.add(resourceUsageMidEntity);
                }
            } catch (Exception e) {
            e.printStackTrace();                
            }finally {
                continue;
            }
        }    
        return list;
    }

    

    @Override
    public void ftpToDb(List<ResourceUsageMidEntity> reMidEntities) {
        resUsaMidDao.save(reMidEntities);

    }
    
    /**
     * 解析yarn使用CPU内存资源统计信息表
     */
    @Override
    public List<CpuMemoryMidEntity> analyseYarnExcel(Workbook workbook) {
        
        //声明CpuMemoryMidEntity对象
        CpuMemoryMidEntity cpuMemoryMidEntity = null;
        
        //创建list对象，存储CpuMemoryMidEntity数据
        List<CpuMemoryMidEntity> cpuMemoryList = new ArrayList<CpuMemoryMidEntity>(); 
        
        
        if (workbook !=null) {
            
            //获取当前sheet工作表
            Sheet sheet = workbook.getSheetAt(0);
            
            //获得当前sheet的开始行  
            int firstRowNum = sheet.getFirstRowNum();
            
            //获得当前sheet的结束行  
            int lastRowNum = sheet.getPhysicalNumberOfRows();
            
            for (int rowNum = firstRowNum+2;rowNum <= lastRowNum; rowNum++) {
                
                //获取当前行数据
                Row row = sheet.getRow(rowNum);
                
                //遍历表中数据
                if( row!=null ){
                    cpuMemoryMidEntity = new CpuMemoryMidEntity();
                    cpuMemoryMidEntity.setSequenceName(POIUtil.getCellValue(row.getCell(1)));//获取队列名
                    cpuMemoryMidEntity.setCpuMax(POIUtil.getCellValue(row.getCell(2)));//获取CPU使用最大值
                    cpuMemoryMidEntity.setCpuAvg(POIUtil.getCellValue(row.getCell(3)));//CPU周平均值
                    cpuMemoryMidEntity.setMemoryMax(POIUtil.getCellValue(row.getCell(4)));//内存使用最大值(G)
                    cpuMemoryMidEntity.setMemoryAvg(POIUtil.getCellValue(row.getCell(5)));//内存周平均值(G)
                    cpuMemoryList.add(cpuMemoryMidEntity);
                }
            }
        }
        return cpuMemoryList;
    }
    
    /**
     * 将yarn使用CPU内存资源数据写入数据库
     */
    @Override
    public void yarnToDb(List<CpuMemoryMidEntity> reMidEntities) {
       
        if(reMidEntities !=null && reMidEntities.size()>0){
            cpuMemoryMidDao.save(reMidEntities);
            System.out.println("CPU内存资源数据写入数据库成功！");
        }
    }

    @Override
    public List<ResourceUsageMidEntity> analyseHbaseText(Workbook workbook) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void hbaseToDb(List<ResourceUsageMidEntity> reMidEntities) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ResourceUsageMidEntity> analyseWebSeverExcel(Workbook workbook) {

        // 创建返回对象的数组
        List<ResourceUsageMidEntity> list = new ArrayList<>();

        if (workbook != null) {
            // 获得当前sheet工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            // 获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            // 循环除了第二行的所有行
            for (int rowNum = firstRowNum + 2; rowNum <= lastRowNum; rowNum++ ) {
                // 获得当前行
                Row row = sheet.getRow(rowNum);
                if (row.getCell(0) == null || row.getCell(0).equals("")) {
                    break;
                }
                ResourceUsageMidEntity resourceUsageMidEntity = new ResourceUsageMidEntity();
                resourceUsageMidEntity.setType("web服务器");

                // 拿keyword Ip，并封装到实体
                Cell cell01 = row.getCell(0);
                resourceUsageMidEntity.setKeyword(POIUtil.getCellValue(cell01));
                // 拿租户空间已使用大小，并封装到实体
                Cell cell03 = row.getCell(3);
                String value = POIUtil.getCellValue(cell03);
                Double storageUsage = Double.parseDouble(value);
                // 保留两位小数，四舍五入
                BigDecimal bg = new BigDecimal(storageUsage);
                storageUsage = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                resourceUsageMidEntity.setStorageUsage(storageUsage);

                list.add(resourceUsageMidEntity);
            }
        }
        return list;

    }

    @Override
    public void webSeverToDb(List<ResourceUsageMidEntity> reMidEntities) {
        resUsaMidDao.save(reMidEntities);
    }

}
