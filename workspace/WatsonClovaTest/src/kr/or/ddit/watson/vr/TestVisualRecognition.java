package kr.or.ddit.watson.vr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
/**
 * 사진인식
 * @author PC-16
 *
 */
public class TestVisualRecognition {
	
	public void test(){
		
	   IamOptions options = new IamOptions.Builder().apiKey("-rFi4ujk3EP7Y-Z79haiF_-O_PL_8rkQKfwJb6dMoRx2").build();
	   
	   VisualRecognition service = new VisualRecognition("2018-03-19", options);
	   
	   InputStream imagesStream;
	   
	   try {
		   
		   //imagesStream = new FileInputStream("src/basic/watson/actrees.jpg");
		   //imagesStream = new FileInputStream("src/kr/or/ddit/watson/visualRecognition/test.jpg");
		   imagesStream = new FileInputStream(new File(getClass().getResource("cc.jpg").getPath()));
		   
		   ClassifyOptions classifyOptions
		   	= new ClassifyOptions
		   	.Builder()
		   	.imagesFile(imagesStream)
		   	.imagesFilename("cc.jpg")
		   	.build();
		   
		   ClassifiedImages result = service.classify(classifyOptions).execute();
		   System.out.println(result);
		   
	   }catch(FileNotFoundException e) {
		   e.printStackTrace();
	   }
	   
	}
	
	public static void main(String[] args) {
		TestVisualRecognition obj = new TestVisualRecognition();
		obj.test();
	}
}
