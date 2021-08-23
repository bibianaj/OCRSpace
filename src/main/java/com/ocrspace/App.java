package com.ocrspace;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ocrspace.api.OCRSpaceAPIService;
import com.ocrspace.utils.FileUtil;

public class App {
	
	public static void processOCR(String apiKey, boolean isOverlayRequired, String language, String filePath) {
		OCRSpaceAPIService apiService = new OCRSpaceAPIService();
		File file = new File(filePath);
		String fileType = FileUtil.getFileExtension(file);
		String apiResult = apiService.sendPostViaFile(apiKey, isOverlayRequired, language, file, fileType);
		
	}
	
    public static void main( String[] args ) {    	
		
		String apiKey = "";
		boolean isOverlayRequired = false;
		String language = "eng";
		
		List<String> filePathList = new ArrayList<>();		
		filePathList.add("C:\\Users\\eunvi\\Desktop\\SK_Jeong Eunbi\\APU_transacript_Jeong Eunbi.pdf");
		
		for (String filePath : filePathList) {
			System.out.println("----->>> NEW FILE PROCESS START - " + filePath);			
			processOCR(apiKey, isOverlayRequired, language, filePath);
		}

    }
}
