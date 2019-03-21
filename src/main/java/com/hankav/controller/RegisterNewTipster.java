package com.hankav.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tika.Tika;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hankav.dao.GetAllTipstersOfUser;
import com.hankav.dao.GetSportByProperty;
import com.hankav.dao.HibSessionFactory;
import com.hankav.model.Stats;
import com.hankav.model.Tipster;
import com.hankav.model.User;

/**
 * Servlet implementation class RegisterNewTipster
 */

public class RegisterNewTipster extends HttpServlet {
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

		String tipstername = null;
		String sportselect = null;

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
		uploadPath = "/var/lib/etresources/images/tipsters/";
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
						if (ite.getFieldName().equalsIgnoreCase("tipstername")) {
							tipstername = ite.getString();
						} else if (ite.getFieldName().equalsIgnoreCase("sportselect")) {
							sportselect = ite.getString();
						}
					}
				}

				for (FileItem ite : formItems) {
					if (!(ite.isFormField())) {

						Tika t = new Tika();
						String mimetype = t.detect(ite.getInputStream());
						if (mimetype.trim().equals("image/jpeg") || mimetype.trim().equals("image/png")) {
							String fileName = null;
							fileName = tipstername;

							String ext = "jpg";
							/*
							 * if (mimetype.trim().equalsIgnoreCase("image/jpeg")) { ext = "jpg";
							 * System.out.println("i am jepeg"); } else if
							 * (mimetype.trim().equalsIgnoreCase("image/png")) { ext = "png";
							 * System.out.println("i am portable net graph"); }
							 */
							String filePath = "/var/lib/etresources/images/tipsters/" + fileName + "." + ext;

							File storeFile = new File(filePath);

							// saves the file on disk
							ite.write(storeFile);
							System.out.println("message" + "Upload has been done successfully!");

						}
					}

				}

				SessionFactory factory = HibSessionFactory.getFactory();
				Session session = factory.openSession();
				Transaction tx = session.beginTransaction();

				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.eq("username", request.getSession().getAttribute("username")));
				User user = (User) criteria.uniqueResult();

				Tipster tipster1 = new Tipster();
				tipster1.setTipster_name(tipstername);
				tipster1.setTipster_category("free");
				tipster1.setPaymentemail(request.getSession().getAttribute("email").toString());
				Stats stat1 = new Stats();
				tipster1.setTipster_stats(stat1);
				stat1.setStats_tipster(tipster1);
				tipster1.setUser(user);
				user.getTipster_profiles().add(tipster1);
				tipster1.setTipster_sport(new GetSportByProperty().byName(sportselect));
				session.save(stat1);
				session.save(tipster1);
				session.update(user);

				tx.commit();

				Map<String, String> map = new GetAllTipstersOfUser()
						.byUserName((String) request.getSession().getAttribute("username"));
				request.getSession().setAttribute("num_of_tipsters", "not_empty");
				request.getSession().setAttribute("tipstersmap", map);
				response.sendRedirect("https://www.eagletip.com/paidtips");
				session.close();
			}

		} catch (Exception exception) {

		}

	}

}
