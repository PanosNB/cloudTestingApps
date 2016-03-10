

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemIntensive
 */
@WebServlet("/MemIntensive")
public class MemIntensive extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int N=100000;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemIntensive() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().println(doMemStuff());

		response.getOutputStream().print(ManagementFactory.getRuntimeMXBean().getStartTime());
	}

	private double doMemStuff() {
		try{
			Double[] list = new Double[N]; 
			for(int i=0;i<N;i++){
				list[i]=new Double(ThreadLocalRandom.current().nextDouble());
			}

			double sum=0;
			for(int i=0;i<N;i++){
				sum+=list[i];
			}

			return sum;
		} catch (Exception e){
			return -1;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
