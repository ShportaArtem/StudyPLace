package modelView;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Base64;

import org.codehaus.plexus.util.FileUtils;

public class CourseView implements Serializable{

	private static final long serialVersionUID = -1842141320487532709L;
	
	public static final String COURSES_PICTURES =  "\\src\\main\\resources\\data\\coursesPics\\";
	public static final String EMPTY_PICTURE =  "\\src\\main\\resources\\data\\coursesPics\\empty.png";
	
	private int id;
	
	private String description;
	
	private String name;
	
	private String password;
	
	private int teacherId;	
	
	private String teacherName;
	
	private int countFollowers;
	
	private String picture;
	
	private Double price;

	
	public String getTeacherName() {
		return teacherName;
	}
	
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public int getCountFollowers() {
		return countFollowers;
	}
	
	public void setCountFollowers(int countFollowers) {
		this.countFollowers = countFollowers;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public CharSequence  getShortDescription() {
		int len = description.length();
		CharSequence  shortD;
		if(len<60) {
			shortD = description.subSequence(0,len);
		}
		else {
			shortD = description.subSequence(0,49);
		}
		return shortD;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getPicture() {
		return picture;
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

	public void setPicture(String path) {
		if (path == "" || path == null)
			return;
		if (path != null && !(new File(path).isFile()))
		{
			path = "C:\\" + path;
			String dir = System.getProperty("user.dir") + COURSES_PICTURES;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
