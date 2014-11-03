package core;

import java.util.EmptyStackException;

public interface Stack<T> {

	public void push(T value);
	public T pop() throws EmptyStackException;
	public T top() throws EmptyStackException;
	public boolean isEmpty();
	public int size();
	
}
