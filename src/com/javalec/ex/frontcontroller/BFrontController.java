package com.javalec.ex.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.command.BCommand;
import com.javalec.ex.command.BContentCommand;
import com.javalec.ex.command.BDeleteCommand;
import com.javalec.ex.command.BListCommand;
import com.javalec.ex.command.BModifyCommand;
import com.javalec.ex.command.BReplyCommand;
import com.javalec.ex.command.BReplyViewCommand;
import com.javalec.ex.command.BWriteCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGET");
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request,response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("actionDo");
		
		request.setCharacterEncoding("utf-8");
		
		
		String viewPage=null; //어떤 뷰를 보여줄건지
		BCommand command = null; //어떤 로직을 수행할지
		
		String uri =request.getRequestURI();
		String conPath = request.getContextPath();
		
		String com = uri.substring(conPath.length());
		System.out.println(com);
		if(com.equals("/write_view.do")) {
			viewPage = "/board/write_view.jsp";
		} else if(com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request,response);
			viewPage = "list.do";
		} else if (com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request,response);
			viewPage = "/board/list.jsp";
		} else if (com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request,response);
			viewPage = "/board/content_view.jsp";
		} else if (com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request,response);
			viewPage = "list.do";
		} else if (com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request,response);
			viewPage = "list.do";
		} else if (com.equals("/reply_view.do")) {
			command = new BReplyViewCommand();
			command.execute(request,response);
			viewPage = "/board/reply_view.jsp";
		} else if (com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request,response);
			viewPage = "list.do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response); //화면전환
		
	}

}
