/*
 * 文件名：CopyPOIUtils.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：zhiyong
 * 修改时间：2017年8月8日
 */

package com.bonc.nerv.tioa.week.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 从不同的XSSFWorkbook复制Sheet工具类
 * 〈功能详细描述〉
 * @author zhiyong
 * @version 2017年8月8日
 * @see CopyPOIUtils
 * @since
 */
public class CopyPOIUtils {

        /**
         * 复制工作表
         * 此方法主要用于复制2个不同HSSFWorkbook间的工作表
         */
        public static void copySheet(XSSFWorkbook fromWorkbook, XSSFWorkbook toWorkbook, int fromSheetIndex, int toSheetIndex) {
            toWorkbook.setSheetName(toSheetIndex, fromWorkbook.getSheetName(fromSheetIndex));
            XSSFSheet fromSheet = fromWorkbook.getSheetAt(fromSheetIndex);
            for (int i = fromSheet.getFirstRowNum(); i <= fromSheet.getLastRowNum(); i++) {
                copyRows(fromWorkbook, toWorkbook, fromSheetIndex, toSheetIndex, i, i, i);
            }
        }

        /**
         * 复制行
         * 此方法主要用于复制2个不同HSSFWorkbook间的行
         */
        public static void copyRows(XSSFWorkbook fromWorkbook, XSSFWorkbook toWorkbook, int fromSheetIndex, int toSheetIndex, int startRow, int endRow, int position) {
            XSSFSheet fromSheet = fromWorkbook.getSheetAt(fromSheetIndex);
            XSSFSheet toSheet = toWorkbook.getSheetAt(toSheetIndex);
            int i;
            int j;

            if ((startRow == -1) || (endRow == -1)) {
                return;
            }

            List<CellRangeAddress> oldRanges = new ArrayList<CellRangeAddress>();
            for (i = 0; i < fromSheet.getNumMergedRegions(); i++) {
                oldRanges.add(fromSheet.getMergedRegion(i));
            }

            // 拷贝合并的单元格。原理：复制当前合并单元格后，原位置的格式会移动到新位置，需在原位置生成旧格式
            for (CellRangeAddress oldRange : oldRanges) {
                CellRangeAddress newRange = new CellRangeAddress(oldRange.getFirstRow(), oldRange.getLastRow(),
                        oldRange.getFirstColumn(), oldRange.getLastColumn());

                if (oldRange.getFirstRow() >= startRow && oldRange.getLastRow() <= endRow) {
                    int targetRowFrom = oldRange.getFirstRow() - startRow + position;
                    int targetRowTo = oldRange.getLastRow() - startRow + position;
                    oldRange.setFirstRow(targetRowFrom);
                    oldRange.setLastRow(targetRowTo);
                    toSheet.addMergedRegion(oldRange);
                    fromSheet.addMergedRegion(newRange);
                }
            }

            // 设置列宽
            for (i = startRow; i <= endRow; i++) {
                XSSFRow fromRow = fromSheet.getRow(i);
                if (fromRow != null) {
                    for (j = fromRow.getLastCellNum(); j >= fromRow.getFirstCellNum(); j--) {
                        toSheet.setColumnWidth(j, fromSheet.getColumnWidth(j));
                        toSheet.setColumnHidden(j, false);
                    }
                    break;
                }
            }

            // 拷贝行并填充数据
            for (; i <= endRow; i++) {
                XSSFRow fromRow = fromSheet.getRow(i);
                if (fromRow == null) {
                    continue;
                }
                XSSFRow toRow = toSheet.createRow(i - startRow + position);
                toRow.setHeight(fromRow.getHeight());
                for (j = fromRow.getFirstCellNum(); j <= fromRow.getPhysicalNumberOfCells(); j++) {
                    XSSFCell fromCell = fromRow.getCell(j);
                    if (fromCell == null) {
                        continue;
                    }
                    XSSFCell toCell = toRow.createCell(j);
                    XSSFCellStyle toStyle = toWorkbook.createCellStyle();
                    copyCellStyle(fromWorkbook, toWorkbook, fromCell.getCellStyle(), toStyle);
                    toCell.setCellStyle(toStyle);
                    int cType = fromCell.getCellType();
                    toCell.setCellType(cType);
                    switch (cType) {
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            toCell.setCellValue(fromCell.getBooleanCellValue());
                            // System.out.println("--------TYPE_BOOLEAN:" +
                            // targetCell.getBooleanCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            toCell.setCellErrorValue(fromCell.getErrorCellValue());
                            // System.out.println("--------TYPE_ERROR:" +
                            // targetCell.getErrorCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            toCell.setCellFormula(parseFormula(fromCell.getCellFormula()));
                            // System.out.println("--------TYPE_FORMULA:" +
                            // targetCell.getCellFormula());
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            toCell.setCellValue(fromCell.getNumericCellValue());
                            // System.out.println("--------TYPE_NUMERIC:" +
                            // targetCell.getNumericCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            toCell.setCellValue(fromCell.getRichStringCellValue());
                            // System.out.println("--------TYPE_STRING:" + i +
                            // targetCell.getRichStringCellValue());
                            break;
                    }
                }
            }
        }

        /**
         * 复制行
         * 如果是同一个HSSFWorkbook中的行请用此方法
         */
        public static void copyRows(XSSFWorkbook workbook, int fromSheetIndex, int toSheetIndex, int startRow, int endRow, int position) {
            XSSFSheet fromSheet = workbook.getSheetAt(fromSheetIndex);
            XSSFSheet toSheet = workbook.getSheetAt(toSheetIndex);
            int i;
            int j;

            if ((startRow == -1) || (endRow == -1)) {
                return;
            }

            List<CellRangeAddress> oldRanges = new ArrayList<CellRangeAddress>();
            for (i = 0; i < fromSheet.getNumMergedRegions(); i++) {
                oldRanges.add(fromSheet.getMergedRegion(i));
            }

            // 拷贝合并的单元格。原理：复制当前合并单元格后，原位置的格式会移动到新位置，需在原位置生成旧格式
            for (CellRangeAddress oldRange : oldRanges) {
                CellRangeAddress newRange = new CellRangeAddress(oldRange.getFirstRow(), oldRange.getLastRow(),
                        oldRange.getFirstColumn(), oldRange.getLastColumn());

                if (oldRange.getFirstRow() >= startRow && oldRange.getLastRow() <= endRow) {
                    int targetRowFrom = oldRange.getFirstRow() - startRow + position;
                    int targetRowTo = oldRange.getLastRow() - startRow + position;
                    oldRange.setFirstRow(targetRowFrom);
                    oldRange.setLastRow(targetRowTo);
                    toSheet.addMergedRegion(oldRange);
                    fromSheet.addMergedRegion(newRange);
                }
            }

            // 设置列宽
            for (i = startRow; i <= endRow; i++) {
                XSSFRow fromRow = fromSheet.getRow(i);
                if (fromRow != null) {
                    for (j = fromRow.getLastCellNum(); j >= fromRow.getFirstCellNum(); j--) {
                        toSheet.setColumnWidth(j, fromSheet.getColumnWidth(j));
                        toSheet.setColumnHidden(j, false);
                    }
                    break;
                }
            }

            // 拷贝行并填充数据
            for (; i <= endRow; i++) {
                XSSFRow fromRow = fromSheet.getRow(i);
                if (fromRow == null) {
                    continue;
                }
                XSSFRow toRow = toSheet.createRow(i - startRow + position);
                toRow.setHeight(fromRow.getHeight());
                for (j = fromRow.getFirstCellNum(); j <= fromRow.getPhysicalNumberOfCells(); j++) {
                    XSSFCell fromCell = fromRow.getCell(j);
                    if (fromCell == null) {
                        continue;
                    }
                    XSSFCell toCell = toRow.createCell(j);
                    toCell.setCellStyle(fromCell.getCellStyle());
                    int cType = fromCell.getCellType();
                    toCell.setCellType(cType);
                    switch (cType) {
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            toCell.setCellValue(fromCell.getBooleanCellValue());
                            // System.out.println("--------TYPE_BOOLEAN:" +
                            // targetCell.getBooleanCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            toCell.setCellErrorValue(fromCell.getErrorCellValue());
                            // System.out.println("--------TYPE_ERROR:" +
                            // targetCell.getErrorCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            toCell.setCellFormula(parseFormula(fromCell.getCellFormula()));
                            // System.out.println("--------TYPE_FORMULA:" +
                            // targetCell.getCellFormula());
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            toCell.setCellValue(fromCell.getNumericCellValue());
                            // System.out.println("--------TYPE_NUMERIC:" +
                            // targetCell.getNumericCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            toCell.setCellValue(fromCell.getRichStringCellValue());
                            // System.out.println("--------TYPE_STRING:" + i +
                            // targetCell.getRichStringCellValue());
                            break;
                    }
                }
            }
        }

        /**
         * 复制单元格样式
         * 此方法主要用于复制2个不同HSSFWorkbook间的单元格样式
         */
        public static void copyCellStyle(XSSFWorkbook fromWorkbook, XSSFWorkbook toWorkbook, XSSFCellStyle fromStyle, XSSFCellStyle toStyle) {
            toStyle.setAlignment(fromStyle.getAlignment());

            // 边框和边框颜色
            toStyle.setBorderBottom(fromStyle.getBorderBottom());
            toStyle.setBorderLeft(fromStyle.getBorderLeft());
            toStyle.setBorderRight(fromStyle.getBorderRight());
            toStyle.setBorderTop(fromStyle.getBorderTop());
            toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
            toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
            toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
            toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

            // 字体
            XSSFFont tofont = toWorkbook.createFont();
            copyFont(fromStyle.getFont(), tofont);
            toStyle.setFont(tofont);

            // 背景和前景
            toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
            toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

            toStyle.setDataFormat(fromStyle.getDataFormat());
            toStyle.setFillPattern(fromStyle.getFillPattern());
            toStyle.setHidden(fromStyle.getHidden());
            toStyle.setIndention(fromStyle.getIndention());
            toStyle.setLocked(fromStyle.getLocked());
            toStyle.setRotation(fromStyle.getRotation());
            toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
            toStyle.setWrapText(fromStyle.getWrapText());
        }

        /**
         * 复制字体
         * 此方法主要用于复制2个不同HSSFWorkbook间的字体
         */
        public static void copyFont(XSSFFont fromFont, XSSFFont toFont) {
            toFont.setBoldweight(fromFont.getBoldweight());
            toFont.setCharSet(fromFont.getCharSet());
            toFont.setColor(fromFont.getColor());
            toFont.setFontHeight(fromFont.getFontHeight());
            toFont.setFontHeightInPoints(fromFont.getFontHeightInPoints());
            toFont.setFontName(fromFont.getFontName());
            toFont.setItalic(fromFont.getItalic());
            toFont.setStrikeout(fromFont.getStrikeout());
            toFont.setTypeOffset(fromFont.getTypeOffset());
            toFont.setUnderline(fromFont.getUnderline());
        }

        private static String parseFormula(String pPOIFormula) {
            final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$
            StringBuffer result;
            int index;

            result = new StringBuffer();
            index = pPOIFormula.indexOf(cstReplaceString);
            if (index >= 0) {
                result.append(pPOIFormula.substring(0, index));
                result.append(pPOIFormula.substring(index + cstReplaceString.length()));
            } else {
                result.append(pPOIFormula);
            }

            return result.toString();
       }
}
