package com.ocrspace.model.OCRResult;

import lombok.Data;

@Data
public class WordObj {
	
	private String wordText;
	private double left;
	private double top;
	private double height;
	private double width;
	
}
