

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CPUIntensive
 */
@WebServlet("/CPUIntensive")
public class CPUIntensive extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final long step=0;

	private static volatile long max=10000000;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CPUIntensive() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getOutputStream().println(doCPUStuff(getAndIncrementMax()));

		response.getOutputStream().print(ManagementFactory.getRuntimeMXBean().getStartTime());
	}

	private synchronized long getAndIncrementMax() {
		long m=max;
		max+=step;
		return m;
	}

	private long doCPUStuff(final long n) {
		try{
			int nThreads=2*Runtime.getRuntime().availableProcessors();
			Thread[] threads=new Thread[nThreads];
			final AtomicLong total=new AtomicLong();

			for(int i=0;i<nThreads;i++){
				threads[i]=new Thread(new Runnable(){

					@Override
					public void run() {
						long sum=0;
						double sum2=0;
						for(long i=0;i<n;i++){
							if(i%2==0){
								sum+=i;
								sum2+=i/2.0;
							} else {
								sum+=i+1;
								sum2+=i/3.0;
							}
						}

						total.addAndGet(sum-(long)sum2);
					}

				});
			}

			for(int i=0;i<nThreads;i++){
				threads[i].start();
			}

			for(int i=0;i<nThreads;i++){
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return total.get();
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
