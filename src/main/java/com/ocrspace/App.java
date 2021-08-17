package com.ocrspace;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ocrspace.api.OCRSpaceAPIService;

public class App 
{
    public static void main( String[] args ) {
    	OCRSpaceAPIService apiService = new OCRSpaceAPIService();
		
		InputStream is;
		String apiKey = "";
		boolean isOverlayRequired = false;
		String language = "eng";
		String fileType = "PDF";
		
		List<String> filePathList = new ArrayList<>();		
		filePathList.add("bibiFILE.pdf");
		
		for (String filePath : filePathList) {
			System.out.println("----->>> NEW FILE PROCESS START - " + filePath);
			File file = new File(filePath);
			apiService.sendPostViaFile(apiKey, isOverlayRequired, language, file, fileType);
		}

    }
}
