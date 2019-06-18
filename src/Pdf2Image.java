import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;


public class Pdf2Image {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		File file = new File("d:\\DS.pdf");
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(file, "r");

			FileChannel channel = raf.getChannel();
			ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			PDFFile pdffile = new PDFFile(buf);
			// draw the first page to an image
			int num=pdffile.getNumPages();
			System.out.println(num);
			for(int i=1;i<=num;i++)
			{
				PDFPage page = pdffile.getPage(i);
				
				//get the width and height for the doc at the default zoom				
				int width=(int)page.getBBox().getWidth();
				int height=(int)page.getBBox().getHeight();	
				
				Rectangle rect = new Rectangle(0,0,width,height);
				int rotation=page.getRotation();
				Rectangle rect1=rect;
				if(rotation==90 || rotation==270)
					rect1=new Rectangle(0,0,rect.height,rect.width);
				
				//generate the image
				BufferedImage img = (BufferedImage)page.getImage(
							rect.width, rect.height, //width & height
							rect1, // clip rect
							null, // null for the ImageObserver
							true, // fill background with white
							true  // block until drawing is done
					);
				
				ImageIO.write(img, "png", new File("d:\\hi"+i+".png"));
			}
		} 
		catch (FileNotFoundException e1) {
			System.err.println(e1.getLocalizedMessage());
			e1.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}