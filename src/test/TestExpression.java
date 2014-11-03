package test;

import java.util.Scanner;

import core.MyStack;

public class TestExpression {

	
	public static void main (String args[]){
		System.out.println("Inserire l'espressione");
		Scanner in = new Scanner(System.in);
		String expression=in.nextLine();
		System.out.println(expression);
		int i =0;
		int k = 0;
		char[] tokens= expression.toCharArray();
		/*	
		for(int j=0; j < expression.length(); j++){
			System.out.println(tokens[j]);
		}*/
		MyStack<Integer> num = new MyStack<>();
		MyStack<Character> ops = new MyStack<>();
	
		while(i<tokens.length){
			//System.out.println(tokens[i]);
			
	        if(tokens[i]==' ')
		         continue;
		    if(tokens[i]>='0' && tokens[i]<='9'){
			     StringBuilder sbuf = new StringBuilder();
			     while(i+k<tokens.length && tokens[i+k] >= '0' && tokens[i+k] <='9'){
                //cifre consecutive
				     sbuf.append(tokens[i+k]);
				     k++;
			     }
			     num.push(Integer.parseInt(sbuf.toString()));
			     System.out.println("num: "+num.top());
	    	}else
		     	if(tokens[i]=='+'||tokens[i]=='-'||tokens[i]=='*'||tokens[i]=='/'){
		     		
		     		ops.push(tokens[i]);
		     		if(tokens[i]=='*'||tokens[i]=='/')
		     		{ 
		                i++;
		     			num.push(Integer.valueOf(Character.toString((tokens[i]))));
		     			//System.out.println(num.top());
		     			while(!ops.isEmpty()){
		     				
		     				 switch(ops.top()){
							    
							    case '*':{
							    	
							    	//num.push(num.pop()*Integer.valueOf(Character.toString((tokens[i+1]))));
							    	num.push(num.pop()*num.pop());
							    	
							    	System.out.println("PROVA:" +num.top());
							    	
							        break;
							    }
							  
							    case '/':{
							    	num.push(num.pop()/num.pop());
							    	System.out.println(num.top());
							        break;
							    }

								case '+':{
									System.out.println(num.top());
									System.out.println("size: "+num.size());
									num.push(num.pop()+num.pop());
								    break;	
								    	
								    }
								  
								  case '-':{
									  num.push(num.pop()-num.pop());
								    	System.out.println(num.top());
								    	
								    break;
								  }
						}
		     		    ops.pop();	
		     				
		     	    }
						
			}
		}        
	    
	    if(k!=0){
		     i=i+k;
		     k=0;
		     System.out.println(i);
	    }else{
    	     i++;
    	     System.out.println(i);
	    }
	    
	}   
	    System.out.println("totale: "+num.pop());
	}

	
	
    private static boolean hasPrecedence(char op1, char op2){
    	
    	if( (op1=='*' || op1=='/' ) && (op2=='+'||op2=='-') )
    		return true;
    	else
    		return false;
    }
}
