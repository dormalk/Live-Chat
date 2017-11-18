package ac.il.Shenkar;

import java.io.*;
import java.net.*;


public class ServerApplication
{
	public static void main(String args[])
	{
		ServerSocket server = null;
		MessageBoard mb = new MessageBoard();
		try
		  {
		   server = new ServerSocket(2032,5);
		  }
		  catch(IOException e)
		  {
			  System.out.println(e.getMessage());
		  }
		
		  Socket socket = null;
		  ClientDescriptor client = null;
		  ConnectionProxy connection = null;
		  while(true)
		  {
			   try
			   { 
			    socket = server.accept();
			    connection =new ConnectionProxy(socket);
			    client = new ClientDescriptor();
			    connection.addConsumer(client);
			    client.addConsumer(mb);
			    mb.addConsumer(connection);
			    connection.start();
				mb.update();
			   }
			   catch(IOException e)
			   {
			   }
		  }
	}
}
 
