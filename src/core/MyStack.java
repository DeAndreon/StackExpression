package core;

import java.util.EmptyStackException;

public class MyStack<T> implements Stack<T>{
    private int size;
    private Node<T> top; //punta all'ultimo elemento inserito
	@Override
	public void push(T value) {
		// TODO Auto-generated method stub
    Node<T> node = new Node<>(value,top);
	top = node;
	size++;
	}

	@Override
	public T pop() throws EmptyStackException {
		// TODO Auto-generated method stub
		T temp;
		
		if(size==0)
			throw new EmptyStackException(); //eccezione controllata, poichè gestita nella classe Test
		else{
	
			temp=top.getValue();
			top=top.getNext();
			size--;
	                
			return temp;
		}
	}

	@Override
	public T top() throws EmptyStackException {
		// TODO Auto-generated method stub
		if(size==0)
			throw new EmptyStackException();
		else
			return top.getValue();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size==0)
			return true;
		else
			return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public MyStack(){
		
		size=0;
	    top=null;
		
	}
	
	
	
	
}
