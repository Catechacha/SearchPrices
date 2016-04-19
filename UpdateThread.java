import java.util.*;
import java.util.concurrent.*;

public class UpdateThread implements Runnable{
	public static CopyOnWriteArrayList<ConcurrentHashMap<String, Product>> p;
	
	public UpdateThread(CopyOnWriteArrayList<ConcurrentHashMap<String, Product>> pp) {
		p=pp;
	}
	
	@Override
	public void run() {
		while(true){
			//creation of 3 callable to update the lists of the 3 sellers 
			ArrayList<Future<ConcurrentHashMap<String, Product>>> runningTasks= new ArrayList<Future<ConcurrentHashMap<String,Product>>>();
			ExecutorService ex= Executors.newFixedThreadPool(3);
			for(int i=2;i<5;i++)
				runningTasks.add(ex.submit(new UpdateList(1500+i)));
			ex.shutdown();
			//process result
			CopyOnWriteArrayList<ConcurrentHashMap<String, Product>> result=new CopyOnWriteArrayList<ConcurrentHashMap<String,Product>>();
			try{
				for (Future<ConcurrentHashMap<String, Product>> t: runningTasks )
					result.add(t.get());
				p=result;//the new list
			}catch (ExecutionException e){
				System.out.println("Some thread has failed");
			} catch (InterruptedException e) {
				System.out.println("Interrupted while waiting");
			}
			try {
				Thread.sleep(24000);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}	
	}
}
