package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

    	//get requestParameter
    	request.setCharacterEncoding("UTF-8");
    	String text = request.getParameter("text");

    	//check input data
    	if(text != null && text.length() != 0) {
    		//get mutterList where save applicationScope
    		ServletContext application = this.getServletContext();
    		List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");

    		//get youser information where sessionScope
    		HttpSession session = request.getSession();
    		User loginUser = (User) session.getAttribute("loginUser");

    		//add mutter to mutterList
    		Mutter mutter = new Mutter(loginUser.getName(), text);
    		PostMutterLogic postMutterLogic = new PostMutterLogic();
    		postMutterLogic.execute(mutter, mutterList);

//    		save mutterList to applicationScope
    		application.setAttribute("mutterList", mutterList);
    	}else {
    		//save errorMessage to requestScope
    		request.setAttribute("errorMsg", "not type mutter");
    	}

    	//foward to mainView
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
    	dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//List Tsubuyaki get applicationScope
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

		//not get TsubuyakiList then new mutterList and save application scope
		if(mutterList == null) {
			mutterList = new ArrayList<Mutter>();
			application.setAttribute("mutterList", mutterList);
		}
		//because checked login
		//get youser imformation is sessionScope
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {//before login
			//redirect
			response.sendRedirect("/docoTsubu/");
		}else {// success login
			//forward
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

}
