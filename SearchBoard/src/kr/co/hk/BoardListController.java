package kr.co.hk;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String searchWord = request.getParameter("searchWord");
		String searchType = request.getParameter("searchType");
		String tmp_page = request.getParameter("page");
		int page = Utils.stringToint_one(tmp_page);
		
		if(searchWord == null) {
			searchWord = "";
		}
		if(searchType == null) {
			searchType = "0";
		}
		
		System.out.println("searchWord : " + searchWord);
		System.out.println("searchType : " + searchType);
		System.out.println("page : " + page);
		
		List<SBoardVO> list = BoardDAO.getBoardList(searchWord, searchType, page);
		int totalPageCnt = BoardDAO.getTotalPageCnt(searchWord, searchType);
		double tmp = Math.ceil((double)totalPageCnt / BoardDAO.viewCnt);
		int pageCnt = (int)tmp;
				
		request.setAttribute("list", list);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("searchType", searchType);
		request.setAttribute("target", "boardList");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
