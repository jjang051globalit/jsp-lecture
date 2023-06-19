package com.jjang051.controller.member;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.jjang051.model.MemberDao;
import com.jjang051.model.MemberDto;
import com.jjang051.utils.ScriptWriter;
//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/member/joinProcess")
public class JoinProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinProcessController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		int fileSize = 1024 * 1024 * 10;
		String savePath = "C:\\Users\\y\\Desktop\\upload";
		File currentDir = new File(savePath);

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setRepository(currentDir); // 경로 설정
		diskFileItemFactory.setSizeThreshold(fileSize); // 파일 업로드 사이즈

		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		try {
			List items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {

				} else {
					// 여기에 파일 들어온다.여기에서 파일 관련된 것들 즉 이름바꾸기 등등을 처리한다.
					System.out.println("fieldName===" + fileItem.getFieldName());
					String originalName = fileItem.getName(); // 업로드된 파일 이름
					System.out.println("originalName===" + originalName);
					String extension = originalName.substring(originalName.lastIndexOf("."));
					UUID uuid = UUID.randomUUID();
					System.out.println(uuid);
					String fileName = uuid + extension;

					File uploadPath = new File(currentDir + "\\" + getToday());
					if (!uploadPath.exists()) {
						uploadPath.mkdir();
					}
					fileItem.write(currentDir);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		int zonecode = Integer.parseInt(request.getParameter("zonecode"));
		String userAddress = request.getParameter("userAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = new MemberDto();
		memberDto.setId(userId);
		memberDto.setPassword(userPw);
		memberDto.setName(userName);
		memberDto.setZonecode(zonecode);
		memberDto.setAddress(userAddress);
		memberDto.setExtraAddress(extraAddress);
		memberDto.setDetailAddress(detailAddress);
		memberDto.setEmail(userEmail);

		System.out.println(memberDto);

		int result = memberDao.insertMember(memberDto);
		if (result > 0) {
			ScriptWriter.alertAndNext(response, "회원가입 되었습니다.", "../member/login");
		} else {
			ScriptWriter.alertAndBack(response, "알 수 없는 오류가 발생 되었습니다. 다시 시도해 주세요");
		}
	}

	private String getToday() {
		// 오늘 날짜 돌려받기
		return new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
	}
}
