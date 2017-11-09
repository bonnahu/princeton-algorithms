
public class LinkedStackOfStrings {
	
	private class Node {
		String item;
		Node next;
		public Node(String _item){
			this.item= _item;
		}
	}
	
	private Node first;
	
	public void push(String newItem){
		Node newNode= new Node(newItem);
		if (first==null){
			first = newNode;
		}else{
			newNode.next=first;
			first=newNode;
		}
	}
	
	public Node pop(){
		Node retNode=null;
		if (first!=null){
			Node nxtNode=first.next;
			retNode=first;
			first=nxtNode;
		}
		return retNode;
	}
}
