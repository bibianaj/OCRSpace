package com.ocrspace.model.OCRResult;

import java.util.List;

import lombok.Data;

@Data
public class PageObj {
	private List<LineObj> lineList;
	private List<WordObj> wordList;
	private String fullText;
}
