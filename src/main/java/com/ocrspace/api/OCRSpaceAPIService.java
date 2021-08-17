package com.ocrspace.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.ocrspace.utils.MultipartUtil;

import org.json.JSONObject;


public class OCRSpaceAPIService {
	
	String apiUrl = "https://api.ocr.space/parse/image"; 

	/* Reference ::
	 * https://stackoverflow.com/questions/37463910/uploading-a-file-to-ocr-space-api
	 * */
	public String sendPostViaFile(String apiKey, boolean isOverlayRequired, String language, File file, String fileType) {
		String charset = "UTF-8";
		String requestURL = "https://api.ocr.space/parse/image";

		StringBuffer responseString = new StringBuffer();
		try {	        
		    MultipartUtil multipart = new MultipartUtil(requestURL, charset);

		    multipart.addHeaderField("apiKey", apiKey);
		    multipart.addFormField("apiKey", apiKey);
		    multipart.addFormField("isOverlayRequired", Boolean.toString(isOverlayRequired));
		    multipart.addFormField("isTable", Boolean.toString(true));
		    multipart.addFormField("filetype", fileType);
            multipart.addFilePart("file", file);
            
            List<String> response = multipart.finish();

            for (String line : response) {
                responseString.append(line);
            }
        } catch (IOException ex) {
        	ex.printStackTrace();
        }

		System.out.println(String.valueOf(responseString));
        //return result
        return String.valueOf(responseString);
	}
	
	public String sendPostViaURL(String apiKey, boolean isOverlayRequired, String language, String url, String fileType) throws Exception {
		URL obj = new URL(apiUrl); // OCR API Endpoints
        HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
        
        connection.setRequestMethod("POST");
        
		JSONObject postDataParams = new JSONObject();

        postDataParams.put("apikey", apiKey);//TODO Add your Registered API key
        postDataParams.put("isOverlayRequired", isOverlayRequired);
        postDataParams.put("url", url);
        postDataParams.put("language", language);
        postDataParams.put("filetype", fileType);
        postDataParams.put("detectOrientation", true);
        postDataParams.put("isTable", true);
        
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(getPostDataString(postDataParams));
        wr.flush();
        wr.close();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        System.out.println(String.valueOf(response));

        //return result
        return String.valueOf(response);
        
	}
	
	private String getPostDataString(JSONObject params) throws Exception {

		StringBuilder result = new StringBuilder();
		boolean first = true;

		Iterator<String> itr = params.keys();

		while (itr.hasNext()) {

			String key = itr.next();
			Object value = params.get(key);

			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(key, "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(value.toString(), "UTF-8"));

		}
		return result.toString();
	}

}
