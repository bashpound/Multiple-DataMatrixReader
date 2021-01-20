package datamatrix.scanner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;

public class MatrixGenerator {
	
	public static void WriteSingleStringToImage(String s, String imgName) throws IOException {
		
		DataMatrixWriter writer = new DataMatrixWriter();
		BitMatrix bitMatrix = writer.encode(s, BarcodeFormat.DATA_MATRIX, 50, 50);
		String fileString = String.format("c:/datamatrix/%s.png", imgName);
		File fout = new File(fileString);
		OutputStream fos = new FileOutputStream(fout);
		MatrixToImageWriter.writeToStream(bitMatrix, "png", fos);
		
	}
	
	public static void WriteMatrixToImage(int sizeOfMatrix, String unitString) throws IOException{
		DataMatrixWriter writer = new DataMatrixWriter();
		int imgX = 8000;
		int imgY = 8000;
		int codeSize = 1600;
		int offSetX = imgX/sizeOfMatrix;
		int offSetY = imgY/sizeOfMatrix;
		
		String fileString = unitString;
		File fout = new File(String.format("c:/datamatrix/grid/scientific/%s.png", fileString));
		BufferedImage bufferedImage = new BufferedImage(imgX, imgY, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
		graphics.setBackground(Color.white);
		graphics.fillRect(0, 0, imgX, imgY);
		
		for(int i=0; i<sizeOfMatrix; i++) {
			for(int j=0; j<sizeOfMatrix; j++) {
				
				BitMatrix bitMatrix = writer.encode(unitString+i+""+j, BarcodeFormat.DATA_MATRIX, codeSize, codeSize);
				BufferedImage temp = MatrixToImageWriter.toBufferedImage(bitMatrix);
				graphics.setPaint(Color.white);
				graphics.fillRect(codeSize*i*2+offSetX/2-50, codeSize*2*j+offSetY-50, codeSize+100, codeSize+100);
				graphics.drawImage(temp, codeSize*i*2+offSetX/2, codeSize*2*j+offSetY, null);
				
			}
		}
		
		ImageIO.write(bufferedImage, "png", fout);
		
	}
	

	
	
	
}
