package com.company.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.mvc_fw_board.board.BoardDAO;
import com.company.mvc_fw_board.board.BoardDO;
import com.company.view.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("전체 게시글 목록 보기 처리됨!");
		
		String searchField = "";
		String searchText = "";
		
		if(request.getParameter("searchCondition") != "" && request.getParameter("searchKeyword") != "") {
			searchField = request.getParameter("searchCondition");
			searchText = request.getParameter("searchKeyword");
		}
		BoardDO boardDO = new BoardDO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
		
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);
		
		return "getBoardList";
	}

}
