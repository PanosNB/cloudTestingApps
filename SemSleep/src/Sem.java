

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Semaphore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sem
 */
@WebServlet("/")
public class Sem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Semaphore sem = new Semaphore(4);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			sem.acquire();
			Thread.sleep(100);
			response.getOutputStream().print(ManagementFactory.getRuntimeMXBean().getStartTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			sem.release();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
