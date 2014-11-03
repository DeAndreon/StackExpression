package core;

public class Node<T> {
	
	
	private T value;
	private Node<T> next; //riferimento al nodo precedente;
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	
	public Node(T value, Node<T> next){
		this.next=next;
		this.value=value;
		
	}
}