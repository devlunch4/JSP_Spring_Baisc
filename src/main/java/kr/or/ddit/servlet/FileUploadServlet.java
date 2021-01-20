package kr.or.ddit.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.util.FileUtil;

@MultipartConfig
@WebServlet("/fileupload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);

	// doGet : 파일업로드 테스트할 화면을 응답으로 생성
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/jsp/fileupload.jsp").forward(req, resp);

	}

	// doPost : 파일 업로드 화면에서 서버 파일 업로드 요청
	// --------- 요청 처리후에 파일 업로드 화면으로 이동
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("contentType : {}", req.getContentType());
		logger.debug("userid : {}", req.getParameter("userid"));
		logger.debug("file : {}", req.getParameter("file"));
		Part part = req.getPart("file");

		// 파일이 있는 경우에만 처리
		if (part.getSize() > 0) {
			// 사용자가 업로드한 파일이름.
			String filename = FileUtil.getFileName(part.getHeader("Content-Disposition"));

			// 서버에 저장할때 다른 사용자의 파일과 중복되지 않도록 생성한 파일 이름.
			String realFileName = UUID.randomUUID().toString();
			part.write("d:\\upload\\" + filename);
		}

		String filenamex = FileUtil.getFileNamex(part.getHeader("Content-Disposition"));
		logger.debug("filenamex : {}", filenamex);
		// 복수개의 파일을 업로드 하고 파트 이름을 모를때
		Collection<Part> parts = req.getParts();
		for (Part p : parts) {

		}

		logger.debug("Content-Disposition : {}", part.getHeader("Content-Disposition"));

		// 강사코드
//		InputStreamReader isr = new InputStreamReader(req.getInputStream());
//char[] buff = new char[512];
//		while(isr.read(buff) != -1) {
//			System.out.println(buff);
		// logger.debug(buff,);
//	}

		doGet(req, resp);

	}
}
