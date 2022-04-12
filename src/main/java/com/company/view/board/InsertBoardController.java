package com.company.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.mvc_fw_board.board.BoardDAO;
import com.company.mvc_fw_board.board.BoardDO;
import com.company.view.controller.Controller;

public class InsertBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("게시글 등록 처리됨!!");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		BoardDO boardDO = new BoardDO();
		boardDO.setTitle(title);
		boardDO.setWriter(writer);
		boardDO.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.insertBoard(boardDO);
		
		//요청 개념 => board 테이블에 insert된 것을 전체 게시글 목록에 적용하여 보여 달라!
		return "getBoardList.do";   
	}

}
