package com.infe.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 파일 유틸
 * @author juhani
 *
 */
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	public boolean filenameCheck(String sourceFile){
		String[] str = {".jpg",".gif",".txt",".JPG",".GIF",".TXT",".png",".PNG"};
		int j=0;
		for(int i=0;i<8;i++){
			if(sourceFile.lastIndexOf((str[i]))==sourceFile.length()-4){
				j++;
			}
		}
		if(j>=1){
			return true;
		}else{
			return false;
		}
		
	}
	/**
	 * 파일을 카피한다.
	 * @param sourceFile
	 * @param destFile
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile) throws IOException {
		
		File lastDestFile = null;
		if (destFile.isDirectory()) {
			lastDestFile = new File(destFile, sourceFile.getName());
		} else {
			lastDestFile = destFile;
		}
		if (!lastDestFile.exists()) {
			lastDestFile.createNewFile();
		}
		
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		
		try {
			FileInputStream in = new FileInputStream(sourceFile);        
			FileOutputStream out = new FileOutputStream(lastDestFile);      
			
			inputChannel = in.getChannel();        
			outputChannel = out.getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		
		} finally {
			if (inputChannel != null) {
				inputChannel.close();
			}
			if (outputChannel != null) {
				outputChannel.close();
			}
		}

	}
	
    public static boolean exists(String s) {
    	boolean flag = false;
    	File file = null;
    	try {
    		file = new File(s);
	        flag = file.exists();
	        file = null;
	    } catch (Exception e) {
	    	flag = false;
	    } finally {
	    	file = null;
	    }
        return flag;
    }		
    
    public static void MakeDir(String s) {
    	File	dirfile		= null;
    	dirfile	=	new File(s);
    	if (!dirfile.isDirectory()) {
    		dirfile.mkdirs();
    	}
    }	
    
    public static boolean deleteFile(String s) {
        File file = new File(s);
        if (exists(s))
        {
        	if (file.delete())
        	{
	        	file = null;
	        	return true;
        	}
        	else
        	{
	        	file = null;
	        	return true;
        	}
        }
        else
        {
        	file = null;
        	return false;
        }
    }		
    
	public static String getExtension(String fileStr){
		fileStr = CubeUtils.nvl(fileStr);
		if (fileStr.indexOf(".") == -1) return "";
		else  return fileStr.substring(fileStr.lastIndexOf(".")+1,fileStr.length());
	}    
	
	public static String getNonExtension(String fileStr){
		fileStr = CubeUtils.nvl(fileStr);
		if (fileStr.indexOf(".") == -1) return "";
		else return fileStr.substring(0,fileStr.lastIndexOf("."));
	}    
	
	
	public static String getChgTailname(String fileStr, String tailname){
		fileStr = CubeUtils.nvl(fileStr);
		return getNonExtension(fileStr) + tailname + "." + getExtension(fileStr);
	}    	
	
	public static void deleteDir(String path) {
		  deleteDir(new File(path));
	}

	/**
	 * 파일을 복사한다.
	 * @param source			소스파일명
	 * @param target			타겟파일명
	 * @return boolean 			결과
	 */	    
	@SuppressWarnings("resource")
	public static boolean copyFile(String source, String target) throws IOException {
	    FileChannel inChannel = new FileInputStream(source).getChannel();
	    FileChannel outChannel = new FileOutputStream(target).getChannel();
	    boolean copy_chk = true;
	    try {
	        int maxCount = (64 * 1024 * 1024) - (32 * 1024);
	        long size = inChannel.size();
	        long position = 0;
	        while (position < size) {
	            position += inChannel.transferTo(position, maxCount, outChannel);
	        }
	    } catch (IOException e) {
	    	copy_chk = false;
	        throw e;
	    } finally {
	        if (inChannel != null)
	            inChannel.close();
	        if (outChannel != null)
	            outChannel.close();
	    }
	    return copy_chk;
	}		 

	public static void deleteDir(File file) {  
	  if (!file.exists())
	   return;
	
	  File[] files = file.listFiles();
	  for (int i = 0; i < files.length; i++) {
	   if (files[i].isDirectory()) {
	    deleteDir(files[i]);
	   } else {
	    files[i].delete();
	   }
	  }
	  file.delete();
	} 
	 
	public static void ClearDir(String path) {
		ClearDir(new File(path));
	}	
	
	
	public static void ClearDir(File file) {  
		  if (!file.exists())
		   return;
		  File[] files = file.listFiles();
		  if (files.length == 0)  file.delete();
	} 	
	
	
	
	  public static String FileRenamePolicy(String targetpath, String tname) {             
	    if (!exists(targetpath+tname)) return tname;        //가 중복되지 않으면 리턴
	    String newName = "";
	    
	    String body = null;
	    String ext = null;

	    int dot = tname.lastIndexOf(".");
	    if (dot != -1) {                              //확장자가 있을때
	      body = tname.substring(0, dot);
	      ext = tname.substring(dot);
	    } else {                                     //확장자가 없을때
	      body = tname;
	      ext = "";
	    }

	    int count = 0;
	    //중복된 파일이 있을때
	    //파일이름뒤에 a숫자.확장자 이렇게 들어가게 되는데 숫자는 9999까지 된다.
	    while (exists(targetpath+tname) && count < 9999) {  
	      count++;
	      newName = body + count + ext;
	    }
	    return newName;
	  }	 
	 
		/**
		 * 파일 이동 시킴
		 * @param sourcepath			대상 파일
		 * @param targetpath			목표 파일
		 * @return isSuccess		성공여부
		 */	
		public static boolean MoveFile(String sourcepath, String targetpath) { 
			boolean isSuccess = false;
			try {
				if (exists(sourcepath))
				{
					copyFile(sourcepath, targetpath);
					deleteFile(sourcepath);
					isSuccess = true;
				}						

			} catch (Exception e) {
				logger.error("MoveFile : " + e.toString());
			}
			return isSuccess;
		}
		
		/**
		 * 파일 이동 시킴
		 * @param sourcepath			대상 파일
		 * @param targetpath			목표 파일
		 * @return isSuccess		성공여부
		 */	
		public static boolean MoveFileAndremoveDri(String sourcepath, String targetpath) { 
			boolean isSuccess = false;
			try {
				if (exists(sourcepath))
				{
					copyFile(sourcepath, targetpath);
					deleteFile(sourcepath);
					isSuccess = true;
				}						

			} catch (Exception e) {
				logger.error("MoveFile : " + e.toString());
			}
			return isSuccess;
		}
	 
		/**
		 * WIZWIG 등록한 파일을 이동시킴
		 * @param FileData			등록하는 파일들
		 * @param PA_SEQ			등록하는 컨텐츠 일련번호
		 * @param Common_type		등록하는 곳에대한 값
		 * @return isSuccess		성공여부
		 */	
		public static boolean ContentFileToMove(String Content_Text, String FileData, String targetpath, String realpath) { 
			boolean isSuccess = false;
			try {
				if (!"".equals(FileData)) {
					String File_Del1[] = FileData.split("@");
					for (int fi=0; fi<File_Del1.length; fi++) {
						if (Content_Text.indexOf(File_Del1[fi]) == -1)
						{
							deleteFile(targetpath + File_Del1[fi]);

						}
						else
						{
							if (exists(targetpath + File_Del1[fi]))
							{
								copyFile(targetpath + File_Del1[fi], realpath + File_Del1[fi]);
								deleteFile(targetpath + File_Del1[fi]);
							}						
						}
					}
				}			
			} catch (Exception e) {
				logger.error("ContentFileToMove : " + e.toString());
			}
			return isSuccess;
		}		
			
		
		/**
		 * WIZWIG 삭제한 파일을 삭제시킴
		 * @param FileData			등록하는 파일들
		 * @param PA_SEQ			등록하는 컨텐츠 일련번호
		 * @param Common_type		등록하는 곳에대한 값
		 * @return isSuccess		성공여부
		 */	
		public static boolean ContentFileToDel(String FileData, String targetpath) { 
			boolean isSuccess = false;
			try {
				if (!"".equals(FileData)) {
					String File_Del1[] = FileData.split("@");
					for (int fi=0; fi<File_Del1.length; fi++) {
						if (exists(targetpath + File_Del1[fi]))
						{
							deleteFile(targetpath + File_Del1[fi]);
						}
					}
				}			
			} catch (Exception e) {
				logger.error("ContentFileToMove : " + e.toString());
			}
			return isSuccess;
		}	
		
		public static boolean ContentFileClear(String content_text, String targetpath) { 
			boolean isSuccess = false;
	    	File	dirfile		= null;
	    	try {
		    	dirfile	=	new File(targetpath);	
		    	String contents_file[] = dirfile.list();
		    	for(int i = 0; i < contents_file.length; i++){
		    		if (content_text.indexOf(contents_file[i]) == -1)
		    		{
		    			deleteFile(targetpath + contents_file[i]);
		    		}
		    	}
		    } catch (Exception e) {
		    	isSuccess = false;
		    } finally {
		    	dirfile = null;
		    }	    	
			return isSuccess;
			
		}		
	
}
