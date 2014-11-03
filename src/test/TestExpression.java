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
			/*ignoro spazi*/
	        if(tokens[i]==' ')
		         continue;
	        /*controllo se il carattere iesimo � un numero altrimenti vedo se � un segno tra quelli ammessi*/
		    if(tokens[i]>='0' && tokens[i]<='9'){
			     StringBuilder sbuf = new StringBuilder();
			     while(i+k<tokens.length && tokens[i+k] >= '0' && tokens[i+k] <='9'){
                //cifre consecutive
				     sbuf.append(tokens[i+k]);
				     k++;
			     }
			     num.push(Integer.parseInt(sbuf.toString()));
			     //System.out.println("num: "+num.top());
	    	}else
		     	if(tokens[i]=='+'||tokens[i]=='-'||tokens[i]=='*'||tokens[i]=='/'||tokens[i]==')'||tokens[i]=='('){
		     		
		     		ops.push(tokens[i]);
		     		//System.out.println(tokens[i]);
		     		/*quando incontro o una divisione o una moltiplicazione la svolgo e  svuoto anche lo stack
		     		 * delle operazioni in quanto sicuramente prima(almeno credo) non ci stanno parentesi(perch�
		     		 * appena incontro una ) svolgo tutto fino ad una ( ma solo
		     		 * operatori di precedenza minore
		     		 */
		     		if(tokens[i]=='*'||tokens[i]=='/'){ 
		     			if(tokens[i+1] >= '0' && tokens[i+1] <='9'){
		     				/*prendo il successivo al segno e lo metto in testa allo stack*/
		     				num.push(Integer.valueOf(Character.toString((tokens[i+1]))));
		     				
		     				/* finch� non si svuota lo stack delle operazioni e l'operazione non � una parentesi di chiusura
		     				 * (perch� altrimenti la sua eliminazione comporta una rottura nell'espressione tra le parentesi)*/
			     			while(!ops.isEmpty() && ops.top()!='('){
			     				/*svolgi le operazioni e poi elimina l'operazione dallo stack*/
			     				doOperation(ops.top(),num);
			     			    ops.pop();
			     	        }
			     		i++;
		     			}
		     			
		     			
		     			//System.out.println(num.top());
		     			
				    	
		            }  
		     		/*se trova una parentesi ) si svuota lo stack delle operazioni sistemando al top dello stack num il 
		     		 * valore uscente da tutte le operazioni tra le parentesi eliminando la ( finale
		     		 */
		     		if(tokens[i]==')'){
		     			// System.out.println(ops.pop());
					     while(ops.top()!='('){
	     				     /*svolgi le operazioni e poi elimina l'operazione dallo stack*/
	     				     doOperation(ops.top(),num);
	     				     //System.out.println("WEILAAAAAAAAA"+ops.top());
	     			         ops.pop();
					
					     
				         }
					//System.out.println("WEILAAAAAAAAA"+ops.top());
				    ops.pop(); /*elimina la '('*/;
	               }
		     	}
	 
	    if(k!=0){
		     i=i+k;
		     k=0;
		  //   System.out.println("Numero"+i);
	    }else{
    	     i++;
    	    // System.out.println("Segno:"+i);
	    }
	   
	    
	}
	/*svuota lo stack delle operazioni in quanto si � assunto che moltiplicazioni e operaizoni tra parentesi
	 *  siano state risolte 
	 */
	while(!ops.isEmpty()) {
		doOperation(ops.top(),num);
	    ops.pop();
		
	}
	System.out.println("Totale: "+num.pop());	
		
		
}
    private static void doOperation(char c, MyStack<Integer> num){
    	int top=0;
    	
    	
				
			 switch(c){
			    
			    case '*':{
			    
			    	//num.push(num.pop()*Integer.valueOf(Character.toString((tokens[i+1]))));
			    	num.push(num.pop()*num.pop());
			    	
			    	//System.out.println("PROVA:" +num.top());
			    	
			        break;
			    }
			  
			    case '/':{
			    	top= num.pop();
			    	num.push((int)(num.pop()/top));
			    	//System.out.println(num.top());
			        break;
			    }

				case '+':{
					//System.out.println(num.top());
					//System.out.println("size: "+num.size());
					num.push(num.pop()+num.pop());
				    break;	
				    	
				    }
				  
				  case '-':{
					  top= num.pop();
					  
					  num.push(num.pop()-top);
				    	//System.out.println(num.top());
				    	
				    break;
				  }
		}
		
    	
    	
    }
}
