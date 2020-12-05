package model;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1231468054964602727L;
	public static final String USER_PICTURES =  "\\src\\main\\resources\\data\\mainPics\\";
	public static final String EMPTY_PICTURE =  "\\src\\main\\resources\\data\\mainPics\\empty.png";
	
	private int id;
	
	private boolean isLocked;
	
	private String messanger;
	
	private String website;
	
	private String email;
	
	private String picture;
	
	private String about;

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getMessanger() {
		return messanger;
	}

	public void setMessanger(String messanger) {
		this.messanger = messanger;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPicture64() {
		System.out.println(picture);
		if (picture != null)
		{
			System.out.println("111111111-  " + picture);
			byte[] fileContent = null;
			try {
				fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(new File(picture));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			return encodedString;
		}
		else
		{
			System.out.println("22222222-  " + picture);
			byte[] fileContent = null;
			try {
				fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(new File(EMPTY_PICTURE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			return encodedString;
		}
	}
	
	public String getPicture()
	{
		return picture;
	}

	public void setPicture(String path) {
		if (path == "" || path == null)
			return;
		if (path != null && !(new File(path).isFile()))
		{
			path = "C:\\" + path;
			String dir = System.getProperty("user.dir") + USER_PICTURES;
			int index = new File(dir).list().length;
			File fileToCopy = new File(path);
			File newFile = new File(dir + '\\' + index + ".jpg");
	        try
	        {
	            boolean created = newFile.createNewFile();
	            if(created)
	                System.out.println("File has been created");
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        }
	        try {
				FileUtils.copyFile(fileToCopy, newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
			this.picture = dir + index + ".jpg";
		}
		else
		{
			System.out.println(path);
			if (new File(path).isFile())
				this.picture = path;
			else 
				this.picture = null;
		}
			
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String choosePicture()
	{
		return "";
	}
}
