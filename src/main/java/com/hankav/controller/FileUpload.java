package com.hankav.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tika.Tika;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/uploadFile")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "upload";

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// checks if the request actually contains upload file
		if (!ServletFileUpload.isMultipartContent(request)) {
			// if not, we stop here
			PrintWriter writer = response.getWriter();
			writer.println("Error: Form must has enctype=multipart/form-data.");
			writer.flush();
			return;
		}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		/*
		 * factory.setRepository(new
		 * File(getServletContext().getContextPath()+"/"+getServletContext().
		 * getInitParameter("UPLOAD_DIRECTORY")));
		 */

		ServletFileUpload upload = new ServletFileUpload(factory);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file
		// this path is relative to application's directory
		String uploadPath = null;
		String usertype = (String) request.getSession().getAttribute("user_type");
		if (usertype.equalsIgnoreCase("tipster")) {
			uploadPath = "/var/lib/etresources/images/tipsters/";
			System.out.println(uploadPath);
		} else if (usertype.equalsIgnoreCase("user")) {
			uploadPath = "/var/lib/etresources/images/users/";
		}
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
				for (FileItem item : formItems) {
					Tika t = new Tika();
					String mimetype = t.detect(item.getInputStream());

					if (!item.isFormField()) {
						if (mimetype.trim().equals("image/jpeg") || mimetype.trim().equals("image/png")) {
							String fileName = null;
							if (usertype.equalsIgnoreCase("tipster")) {
								fileName = (String) request.getSession().getAttribute("tipster_name");
							} else if (usertype.equalsIgnoreCase("user")) {
								fileName = (String) request.getSession().getAttribute("username");
							}
							String ext = "jpg";
							/*
							 * if (mimetype.trim().equalsIgnoreCase("image/jpeg")) { ext = "jpg";
							 * System.out.println("i am jepeg"); } else if
							 * (mimetype.trim().equalsIgnoreCase("image/png")) { ext = "png";
							 * System.out.println("i am portable net graph"); }
							 */
							/*
							 * if (usertype.equalsIgnoreCase("user")) { ext = "jpg"; }
							 */
							String filePath = uploadPath + fileName + "." + ext;

							File storeFile = new File(filePath);

							// saves the file on disk
							item.write(storeFile);
							System.out.println("message" + "Upload has been done successfully!");

						}
					}
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message", "file format or size not valid");
		}
		// redirects client to message page
		if (usertype.equalsIgnoreCase("tipster")) {
			response.sendRedirect("https://www.eagletip.com/tipsterpersonal/profile");
			return;
		} else {
			response.sendRedirect("https://www.eagletip.com/Profile");
		}
	}

}
