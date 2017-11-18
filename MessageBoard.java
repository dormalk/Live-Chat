package ac.il.Shenkar;
import java.util.LinkedList;


public class MessageBoard implements StringProducer,StringConsumer {
	
	private LinkedList<StringConsumer> consumer=null;
	
	MessageBoard(){
		consumer=new LinkedList<StringConsumer>();
	}
	
	@Override
	public synchronized void consume(String str) {
		// TODO Auto-generated method stub
		if (str.charAt(0)=='-'){
			for(int i=0;i<consumer.size();i++){
				if(consumer.get(i).infrom().equals(str.substring(1,str.length()))){
					System.out.println(consumer.get(i).infrom()+"Deleted");
					str=consumer.get(i).infrom()+" left the chat";
					consumer.remove(i);
					update();
					break;
				}
			}
		}
		for(int i=0;i<consumer.size();i++){
			consumer.get(i).consume(str);
		}

	}

	@Override
	public void addConsumer(StringConsumer sc) {
		// TODO Auto-generated method stub
		consumer.add(sc);

	}

	@Override
	public void removeConsumer(StringConsumer sc) {
		// TODO Auto-generated method stub
		
	}
	public void update(){
		String str="#";
		for(int i=0;i<consumer.size();i++){

			str+=consumer.get(i).infrom()+"\n";
		}
		consume(str);
	}

	@Override
	public String infrom() {
		// TODO Auto-generated method stub
		update();
		return null;
	}
	
}
