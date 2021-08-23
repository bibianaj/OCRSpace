package com.ocrspace.model.OCRResult;

import java.util.List;

import lombok.Data;

@Data
public class LineObj {

	private List<WordObj> wordList;
	private double maxHeight;
	private double minTop;
}
