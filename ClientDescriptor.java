package ac.il.Shenkar;

public class ClientDescriptor implements StringConsumer, StringProducer {
	
	private String nickname;
	private boolean flag=true;
	private StringConsumer consumer;
	
	
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
	public synchronized void consume(String str) {
		// TODO Auto-generated method stub
		if (flag){
			nickname=str;
			consumer.consume(nickname+" connect to the chat");
			consumer.infrom();
			flag=false;
		}
		else{
			System.out.println(str);
			if(str.charAt(0)=='-'){
				consumer.consume("-"+nickname);
			}
			else{
				consumer.consume(nickname+": "+str);
			}
		}
	
	}

	@Override
	public String infrom() {
		// TODO Auto-generated method stub
		return nickname;
	}

}
