package org.lanqiao.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.lanqiao.FaceRecognize;



public class AddPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//�����ϴ���Ƭ
	try {
		//ͳһ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("textml charset=utf-8");
		// ͨ��PrintWrite���󣬽������Ľ��������ǰ̨AJAX
		PrintWriter out = response.getWriter();
		// ���ñ����ͣ������ļ����͵��ֶ�
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// �����ϴ�·��
		String uploadFilePath = "H:\\images";
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items;
			String filePath = "";
			items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				// ��ȡÿһ����Ƭ
				FileItem item = iter.next();
				if (!item.isFormField()) {
					// ��ȡ�ļ���
					String fileName = item.getName();
					if (fileName != null && !fileName.equals("")) {
						File saveFile = new File(uploadFilePath, fileName);
						filePath = saveFile.getPath();
						// ����ͼ
						item.write(saveFile);
						System.out.println("�ϴ��ɹ�!");
						// ����ʶ��
						Object result = FaceRecognize.faceRecognize(filePath);
						out.println(result);
						return;

					}

				}

			}

		}

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
