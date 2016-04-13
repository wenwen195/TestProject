package testEqualPartition;
import java.io.File;
import java.util.ArrayList;

import jxl.*; 
import getNextTimeStamp.*;
public class PartitionTest{
	
        public  ArrayList<String> PartitionTestToWrite(){
		    NextTimeStamp   timer = new NextTimeStamp();     
		    //writeXLS���ڼ�¼�����𰸺ͼ�����֮��ıȽϽ���������Խ��
		    ArrayList<String> writeXLS = new ArrayList<String>();
		    int i;
	        Sheet sheet;
	        Workbook book;
	        Cell cell1,cell2,cell3,cell4;
	        try { 
	            //t.xlsΪҪ��ȡ��excel�ļ���
	            book= Workbook.getWorkbook(new File("partition.xls")); 
	            //��õ�һ�����������(ecxel��sheet�ı�Ŵ�0��ʼ,0,1,2,3,....)
	            sheet=book.getSheet(0); 
	            //��ȡ���Ͻǵĵ�Ԫ��
	            cell1=sheet.getCell(0,0);
	            System.out.println("���⣺"+cell1.getContents()); 
	            i=1;
	            while(true)
	            {
	                //��ȡÿһ�еĵ�Ԫ�� 
	                cell1=sheet.getCell(0,i);//���У��У�
	                cell2=sheet.getCell(1,i);
	                cell3=sheet.getCell(2,i);
	                cell4=sheet.getCell(3,i);
	                 if("".equals(cell1.getContents())==true)    //�����ȡ������Ϊ��
	                  break;
	                 System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()); 
	                //��������������ж�
	                String[] arr = {cell1.getContents(),cell2.getContents(),cell3.getContents()};
	                 System.out.println(arr[0]+"!"+arr[1]+"!"+arr[2]);
	                //�������õõ��ļ�����
	                String[] result = timer.inputNextTime(arr);
	                
	                //�������ļ��еõ��Ľ��
	                String basicAnswer = cell4.getContents();
	             //   System.out.println(basicAnswer);
	               
	                if(i < 6){//ǰ7����������ͨ�����ŷָ�õ�������֤����ı�׼��
	                	 String xlsExpect[]=basicAnswer.split(",");
	                	 if(result.length==3){//��������������
	                		 if(Integer.parseInt(xlsExpect[0])==Integer.parseInt(result[0])&&Integer.parseInt(xlsExpect[1])==Integer.parseInt(result[1])&&Integer.parseInt(xlsExpect[2])==Integer.parseInt(result[2]))
	     	                {
	     	                	System.out.println("correctly match");
	     	                	writeXLS.add("correctly match");
	     	                }
	     	                else{
	     	                	System.out.println("error match");
	     	                	writeXLS.add("error match");
	     	                } 
	                	 }
	                	 else{
	                		 //���������ǡ�error������ʱ��׼���ޡ�error������ƥ�����
	                		 System.out.println("error match  ");
	                		 writeXLS.add("error match");
	                	 }
	                }
	                
	                else{//����7��15���������ǡ�error�����Բ��طָ�
	            
	                	if(result.length==3){//����׼��Ϊ��error"���Ǽ���������
	                		System.out.println("error match");
	                		writeXLS.add("error match");
	                	}else{
	                		System.out.println("correctly match");
	                		writeXLS.add("correctly match");
	                	}
	                }
	          
	                i++;
	            }
	            book.close(); 
	        }
	        catch(Exception e)  { }
			return writeXLS; 
	}
     
}