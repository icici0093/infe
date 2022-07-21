package com.infe.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infe.social.SocialStore;

@Service
public class YoutubeAPI {
		
	@Autowired
	public SocialStore sStore;
	
	public void getYoutube() throws Exception {
		CubeMap param = new CubeMap();
		
		String apikey = "AIzaSyCxvbqnPlFiEJmzqxn8FkcLDq8oE89WF4Y";
		String channelId = "UCHpq8SAVDJ0BHIP2c5I6HbQ";
		  String UPplaylistid ="UUHpq8SAVDJ0BHIP2c5I6HbQ";
		  String apiUrl = "https://www.googleapis.com/youtube/v3/playlistItems?key="+ apikey
		  + "&playlistId="+ UPplaylistid
		  + "&part=snippet&fields=nextPageToken,pageInfo,items(id,snippet(publishedAt,title,description,thumbnails(high(url)),resourceId(videoId)))&order=date&maxResults=50";
//		String apiUrl = "https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&key=" + apikey
//				+"&allThreadsRelatedToChannelId=" + channelId + "&order=time&maxResults=100";
		
		JSONObject json = readJsonFromUrl(apiUrl);
		
		JSONArray jArray = json.getJSONArray("items");
		
		for(int i = 0 ; i < jArray.length(); i++) {
			
			JSONObject item = jArray.getJSONObject(i);
			
			//snippet
			JSONObject snippet = item.getJSONObject("snippet");
			
			JSONObject resourceId = snippet.getJSONObject("resourceId"); 
			String videoId = resourceId.getString("videoId"); 
			String publishedAt = snippet.getString("publishedAt");
			String title = snippet.getString("title");
			String description = snippet.getString("description");
			
			String date = publishedAt.substring(0, publishedAt.indexOf("T")) + " " + publishedAt.substring(publishedAt.indexOf("T")+1, publishedAt.indexOf("Z"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			Date dates;
			
			try {
				dates = sdf.parse(date);
				date = sdf2.format(dates);
			} catch (Exception e) {
				System.out.println("date 파싱 에러");
			}
			
			//유튜브 동영상 정보 출력 
			System.out.println(i);
			System.out.println(title);
			System.out.println(date);
			System.out.println(description);
			System.out.println(videoId);
			
			param.set("mov_id", videoId);
			param.set("mov_nm", title);
			param.set("mov_cn", description);
			param.set("reg_dt", date);
			sStore.setYoutubeMov(param);
			
//			title = Normalizer.normalize(title, Normalizer.Form.NFC);
//	        //이게 내용
//			description = Normalizer.normalize(description, Normalizer.Form.NFC); 
			
		}
	}
	
	public void getYoutubeReply() throws Exception {
		CubeMap param = new CubeMap();

		String apikey = "AIzaSyCxvbqnPlFiEJmzqxn8FkcLDq8oE89WF4Y";
		String channelId = "UCHpq8SAVDJ0BHIP2c5I6HbQ";
//		  String UPplaylistid ="UUHpq8SAVDJ0BHIP2c5I6HbQ";
//		  String apiUrl = "https://www.googleapis.com/youtube/v3/playlistItems?key="+ apikey
//		  + "&playlistId="+ UPplaylistid
//		  + "&part=snippet&fields=nextPageToken,pageInfo,items(id,snippet(publishedAt,title,description,thumbnails(high(url)),resourceId(videoId)))&order=date&maxResults=50";
		String apiUrl = "https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&key=" + apikey
			  +"&allThreadsRelatedToChannelId=" + channelId + "&order=time&maxResults=100";

		JSONObject json = readJsonFromUrl(apiUrl);
	 
		JSONArray jArray = json.getJSONArray("items");
		
		for(int i = 0 ; i < jArray.length(); i++) {

			JSONObject item = jArray.getJSONObject(i);
			
			//snippet
			JSONObject snippet = item.getJSONObject("snippet");
			//snippet > topLevelComment
			JSONObject topLevelComment = snippet.getJSONObject("topLevelComment");
			//snippet > topLevelComment > snippet
			JSONObject snippet2 = topLevelComment.getJSONObject("snippet");
			
			String videoId = snippet.getString("videoId"); 
			String authorDisplayName = snippet2.getString("authorDisplayName");
			String textOriginal = snippet2.getString("textOriginal");
			String publishedAt = snippet2.getString("publishedAt");
			
			String date = publishedAt.substring(0, publishedAt.indexOf("T")) + " " + publishedAt.substring(publishedAt.indexOf("T")+1, publishedAt.indexOf("Z"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			Date dates;
			
			try {
				dates = sdf.parse(date);
				date = sdf2.format(dates);
			} catch (Exception e) {
				System.out.println("date 파싱 에러");
			}
			//유튜브 동영상 정보 출력 
			System.out.println(i);
//			System.out.println(authorDisplayName);
//			System.out.println(date);
//			System.out.println(textOriginal);
//			System.out.println(videoId);
			
			param.set("author_nm", authorDisplayName);
			param.set("reg_dt", date);
			param.set("re_cn", textOriginal);
			param.set("mov_id", videoId);
			sStore.setYoutubeReply(param);
			
//			title = Normalizer.normalize(title, Normalizer.Form.NFC);
//	        //이게 내용
//			description = Normalizer.normalize(description, Normalizer.Form.NFC); 

	  }
  }
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	}
}
