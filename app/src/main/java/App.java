import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class App{

	public static void main(String[] args){
		
		String uniqueID = UUID.randomUUID().toString();
		Runnable broadcast = new Runnable() {
			public void run() {
				Broadcaster.run(); //Broadcaster receives information about other replicas
			}
		};
		
		Runnable receive = new Runnable() {
			public void run() {
				Receiver.run(); //Receiver receives the propagated changes
			}
		};
		
		new Thread(broadcast).start();
		new Thread(receive).start();
		
		System.out.println("My ID is: " + uniqueID);
		
		try{
			TimeUnit.SECONDS.sleep(15); //gives time for all the replicas to register
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		
		for(int i = 0; i<100; i++){
			try{
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			Broadcaster.broadcast(uniqueID); //broadcasts changes to all replicas
		}
	}
}