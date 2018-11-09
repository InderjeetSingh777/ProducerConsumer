import java.util.*;
class sync{
	
	int bufferSize=5,item=-1;//to declare the buffer size
	Queue<Integer> q = new LinkedList<>();//to followe fifo structure in production-consumption

synchronized public void produce ()
{
	while(q.size()==bufferSize)//if buffer is full
		{
			 System.out.println("Buffer full");
		 	 try{
		 		wait();
		 	 }
		 	 catch(Exception e){
		 		e.printStackTrace();
		 	 }
			 
		}
		++item;//item to be produced
		
		q.add(item);
		System.out.println("producer produced : " +(item-1));
		    if(q.size()==0)
	    	notifyAll();
	
}
synchronized public void consume()
{
	while(q.size()<1)//if buffer is empty
		{
			 System.out.println("Buffer empty");
		 	 
			 try{
			 		wait();
			 	 }
			 	 catch(Exception e){
			 		e.printStackTrace();
			 	 }
		}
		
		
		System.out.println("consumer consumed : " + q.remove());//consumed item
		if(q.size()==bufferSize-1)
		notifyAll();
}
}

class Producer implements Runnable{
	sync pr;
	Thread t;
	Producer(sync pr)
	{
		this.pr=pr;
		t=new Thread(this);//creating thread for producer
		t.start();
	}
	public void run()
	{
		while(true)
		{
				pr.produce();
				try{
					Thread.sleep(1000);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
		}
		
	}
}
class Consumer implements Runnable{
	sync con;
	Thread t;
	Consumer(sync con)
	{
		this.con=con;
		t=new Thread(this);//creating thread for consumer
		t.start();
	}
	public void run()
	{	
		while(true){
			con.consume();
			try{
				Thread.sleep(2500);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		//	Thread.sleep(1000);
		}
	}
}
public class ProducerConsumer {

	
	public static void main(String[] args){
		
		sync obj=new sync();
		new Producer(obj);
		new Consumer(obj);
		

	}

}
