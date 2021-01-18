package datamatrix.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import com.google.zxing.multi.GenericMultipleBarcodeReader;

import java.awt.image.BufferedImage;

public class App  {
	
    public static void main( String[] args ) throws Exception{
    	
    	
    	
    	InputStream fis = new FileInputStream("C:/datamatrix/grid/matrix_22_white.png");
    	BufferedImage bufferedImage = ImageIO.read(fis);
    	BufferedImageLuminanceSource bils = new BufferedImageLuminanceSource(bufferedImage);
    	HybridBinarizer hb = new HybridBinarizer(bils);
    	BitMatrix fromImage = hb.getBlackMatrix();
    	
    	
    	LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
    	BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
    	DataMatrixReader dataMatrixreader = new DataMatrixReader();
    	
    	
    	Hashtable<DecodeHintType, Object> decodeHints = new Hashtable<DecodeHintType, Object>();
        decodeHints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
    	
    	GenericMultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(dataMatrixreader);
    	Result[] results = multiReader.decodeMultiple(bitmap, decodeHints);
    	
    	for(Result r: results) {
    		System.out.println(r.getText());
    	}
    	
    	
    	
    	/*
    	Detector detector = new Detector(fromImage);
    	DetectorResult detectorResult = detector.detect();
    	BitMatrix fromImagebitMatrix = detectorResult.getBits();
    	Decoder decoder = new Decoder();
		DecoderResult decoderResult = decoder.decode(fromImagebitMatrix);
		
		String outText = decoderResult.getText();
		System.out.println(outText);
		*/
    	
    	
    }
    
    
}
