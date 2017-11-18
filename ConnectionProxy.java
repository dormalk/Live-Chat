package ac.il.Shenkar;


import java.net.*;
import java.io.*;
public class ConnectionProxy extends Thread implements StringProducer,StringConsumer{
	
	private Socket socket;
	private InputStream is=null;
	private OutputStream os=null;
	private DataInputStream dis=null;
	private DataOutputStream dos=null;
	private StringConsumer consumer=null;
	private StringProducer producer=null;
	
	ConnectionProxy(Socket socket){
		try{
			this.socket=socket;
			is=socket.getInputStream();
			os=socket.getOutputStream();
			dis=new DataInputStream(is);
			dos=new DataOutputStream(os);
		}
		catch(IOException e){
			//...
		}
		 //--------------------//
	}
	
	@Override
	public synchronized void consume(String str) {
		try{
			dos.writeUTF(str);
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void addConsumer(StringConsumer sc) {
		// TODO Auto-generated method stub
		consumer=sc;	 
	}

	@Override
	public void removeConsumer(StringConsumer sc) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run(){
		try{
			while(true) {
				String str=dis.readUTF();
				consumer.consume(str);
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String infrom() {
		// TODO Auto-generated method stub
		return consumer.infrom();
	}
}
