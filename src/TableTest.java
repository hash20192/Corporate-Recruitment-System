import java.io.*;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;



public class TableTest {
public static void main(String[] args) {
File file = null;
XWPFWordExtractor extractor = null ;
try {

   file = new File("c:\\New.docx");
   FileInputStream fis=new FileInputStream(file.getAbsolutePath());
   XWPFDocument document=new  XWPFDocument(fis);
    extractor = new XWPFWordExtractor(document);
   //XWPFWordExtractor extractor = new XWPFWordExtractor(document);
    System.out.println(extractor.getText());  
    
}
catch(Exception exep){exep.printStackTrace();}
  }
}