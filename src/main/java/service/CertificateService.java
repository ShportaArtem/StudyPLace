package service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.CertificateRep;
import db.utils.DBUtils;
import model.Certificate;
import model.User;
import model.Course;
import model.Question;
import db.repository.UserRep;
import db.repository.CourseRep;

public class CertificateService {
	
	private static final Logger LOG = Logger.getLogger(CertificateService.class);

	private DBManager dbManager;
	private CertificateRep certificateRep;
	
	public CertificateService(DBManager dbManager, CertificateRep certificateRep) {
		super();
		this.dbManager = dbManager;
		this.certificateRep = certificateRep;
	}
	
	public Certificate findCertificateById(int id) throws AppException {
		Certificate user = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			user = certificateRep.findCertificateById(con, id);
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			DBUtils.close(con);
		}
		return user;
	}
	
	public String createCertificateImage(int id) throws AppException, IOException {
		
		String filePathCertificate = "resources\\data\\certificatePics\\Certificate"+id+".jpg";
		
		File f = new File(filePathCertificate);
		if(!f.exists() && f.isDirectory()) {
			return filePathCertificate;
		}
		
		Connection con=null;
		String filePathBlank = "resources\\data\\certificatePics\\Certificate.jpg";
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    LocalDate localDate = LocalDate.now();
		
		UserRep userRep = null;
		User user = null;
		CertificateRep certificateRep = null;
		Certificate certificate = null;
		CourseRep courseRep = null;
		Course course = null;
		
		try {
			certificate = certificateRep.findCertificateById(con, id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int usId = certificate.getUserId();
		
		try {
			user = userRep.findUserById(con, usId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int csId = certificate.getCourseId();
		
		try {
			course = courseRep.findCourseById(con, csId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String usName = user.getName() + " " + user.getSurname();
		String mark = Double.toString(certificate.getMark());
		String courseName = course.getName();
		String date = dtf.format(localDate);
		
		File file = new File(filePathBlank);
		BufferedImage image = ImageIO.read(file);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.BOLD, 22));
		g.drawString(usName, 220, 260);
		g.drawString(mark, 295, 361);
		g.drawString(courseName, 220, 500);
		g.drawString(date, 180, 581);
		g.drawString("Лучшим в мире сайтом - StudyPlace", 220, 665);
		ImageIO.write(image, "jpg", new File(file.getParentFile(), "Certificate"+id+".jpg"));
		
		return filePathCertificate;
		
	}
	
	public Certificate getCertByCourseAndUser(User user, Course course) throws DBException
	{
		Certificate value = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			value = certificateRep.findCertificateByUserCourseId(con, user.getId(), course.getId());
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			DBUtils.close(con);
		}
		return value;
	}
	
	public List<Certificate> getCertsByUser(User user) throws DBException
	{
		List<Certificate> values = null;
		Connection con=null;
		try {
			con = dbManager.getConnection();
			values = certificateRep.findCertificatesByUserID(con, user.getId());
			con.commit();
		} catch (SQLException ex) {
			DBUtils.rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_QUESTIONS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_QUESTIONS, ex);
		} finally {
			DBUtils.close(con);
		}
		return values;
	}
	
	public Certificate createCertificate(int courseId, int userId, double mark) throws DBException
	{
		mark /= 100;
		Certificate cert = new Certificate();
		cert.setCourseId(courseId);
		cert.setUserId(userId);
		cert.setMark(mark);
		Connection con=null;
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(true);
			cert = certificateRep.insertCertificate(con, cert);
		} catch (SQLException ex ) {
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			DBUtils.close(con);
		}
		return cert;
	}
}
