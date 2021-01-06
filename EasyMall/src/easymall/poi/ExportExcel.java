package easymall.poi;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.*;

import easymall.pojo.MyProducts2;

public class ExportExcel {
	
		@SuppressWarnings({ "unchecked", "resource" })
	   public void exportExcel(String[] headers,List<MyProducts2> dataset, OutputStream out) {
		// ����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����һ�����
		HSSFSheet sheet = workbook.createSheet("���۱���");
//		// ����һ����ʽ
//		HSSFCellStyle style = workbook.createCellStyle();
//		// ����һ������
//	    HSSFFont font = workbook.createFont();
//	    font.setFontHeightInPoints((short) 12);
//	    // ������Ӧ�õ���ǰ����ʽ
//	    style.setFont(font);
	   
	    //������������
	      HSSFRow row = sheet.createRow(0);
	      for (short i = 0; i < headers.length; i++) {
	         HSSFCell cell = row.createCell(i);
	         HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	         cell.setCellValue(text);
	      }
	    //�������ݣ�����������
	      Iterator<MyProducts2> it = dataset.iterator();
	      int index = 0;
	      while (it.hasNext()) {
	         index++;
	         row = sheet.createRow(index);
	         MyProducts2 t = (MyProducts2) it.next();
	         //���÷��䣬����javabean���Ե��Ⱥ�˳�򣬶�̬����getXxx()�����õ�����ֵ
	         Field[] fields = t.getClass().getDeclaredFields();
	         for (short i = 0; i < fields.length; i++) {
	            HSSFCell cell = row.createCell(i);
	            Field field = fields[i];
	            String fieldName = field.getName();
	            String getMethodName = "get"
	                   + fieldName.substring(0, 1).toUpperCase()
	                   + fieldName.substring(1);
	            try {
	                Class tCls = t.getClass();
	                Method getMethod = tCls.getMethod(getMethodName,
	                      new Class[] {});
	                Object value = getMethod.invoke(t, new Object[] {});
					String textValue = null;
					 //�����������Ͷ������ַ����򵥴���
	                   textValue = value.toString();
	                //����������ʽ�ж�textValue�Ƿ�ȫ�����������
	                if(textValue!=null){
	                   Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
	                   Matcher matcher = p.matcher(textValue);
	                   if(matcher.matches()){
	                      //�����ֵ���double����
	                      cell.setCellValue(Double.parseDouble(textValue));
	                   }else{
	                      HSSFRichTextString richString = new HSSFRichTextString(textValue);
	                      cell.setCellValue(richString);
	                   }
	                }
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }finally {
	                //������Դ
	            }
	         }
	      }
	      try {
	         workbook.write(out);
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	}
}
