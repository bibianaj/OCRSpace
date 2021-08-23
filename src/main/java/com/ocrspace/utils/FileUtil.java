package com.ocrspace.utils;

import java.io.File;

public class FileUtil {

	public static String getFileExtension(File file) {
		String extension = "";
		String fileName = file.getName();
		
		int i = fileName.lastIndexOf(".");
		if (i > 0) {
			extension = fileName.substring(i+1);
		}
		return extension.toUpperCase().trim();
	}
}
