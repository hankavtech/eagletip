package com.hankav.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tika.Tika;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.hankav.dao.HibSessionFactory;
import com.hankav.dao.RegistrationEmailSender;
import com.hankav.model.User;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = null;
		String email = null;
		String password = null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			// if not, we stop here
			PrintWriter writer = response.getWriter();
			writer.println("Error: Form must has enctype=multipart/form-data.");
			writer.flush();
			return;
		}

		// configures upload settings
		DiskFileItemFactory factory1 = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory1.setSizeThreshold(MEMORY_THRESHOLD);
		ServletFileUpload upload = new ServletFileUpload(factory1);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file
		// this path is relative to application's directory
		String uploadPath = null;
		uploadPath = "/var/lib/etresources/images/users/";
		System.out.println(uploadPath);

		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields

				for (FileItem ite : formItems) {
					if (ite.isFormField()) {
						if (ite.getFieldName().equalsIgnoreCase("username")) {
							username = ite.getString();
						} else if (ite.getFieldName().equalsIgnoreCase("password")) {
							password = ite.getString();
						} else if (ite.getFieldName().equalsIgnoreCase("email")) {
							email = ite.getString();
						}
					}
				}

				for (FileItem ite : formItems) {
					if (!(ite.isFormField())) {

						Tika t = new Tika();
						String mimetype = t.detect(ite.getInputStream());
						if (mimetype.trim().equals("image/jpeg") || mimetype.trim().equals("image/png")) {
							String fileName = null;
							fileName = username;

							String ext = "jpg";
							/*
							 * if (mimetype.trim().equalsIgnoreCase("image/jpeg")) { ext = "jpg";
							 * System.out.println("i am jepeg"); } else if
							 * (mimetype.trim().equalsIgnoreCase("image/png")) { ext = "png";
							 * System.out.println("i am portable net graph"); }
							 */
							/* String filePath = uploadPath + File.separator + fileName + "." + ext; */
							String filePath = "/var/lib/etresources/images/users/" + fileName + "." + ext;

							File storeFile = new File(filePath);

							// saves the file on disk
							ite.write(storeFile);
							System.out.println("message" + "Upload has been done successfully!");

						}
					}

				}

				SessionFactory factory = HibSessionFactory.getFactory();
				Session session = factory.openSession();
				session.beginTransaction();
				/*
				 * username = request.getParameter("username"); email =
				 * request.getParameter("email"); password = request.getParameter("password");
				 */
				String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
				User user1 = new User();
				user1.setUsername(username);
				user1.setPassword(hashpw);
				user1.setEmail(email);
				final String uuid = UUID.randomUUID().toString().replace("-", "");
				user1.setRuuid(uuid);
				session.save(user1);

				RegistrationEmailSender sender = new RegistrationEmailSender();
				sender.sendemail(email, uuid);

				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("odds", user1.getUser_preferences().getOddsformat());
				request.getSession().setAttribute("timezone", user1.getUser_preferences().getTimezone());
				request.getSession().setAttribute("currency", user1.getUser_preferences().getCurrency());
				request.getSession().setAttribute("num_of_tipsters", "s_empty");
				session.getTransaction().commit();

				session.close();
				response.sendRedirect("https://www.eagletip.com/paidtips");
			}

		} catch (Exception exception) {

		}

	}

}
