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
			     				doOperation(ops.pop(),num,ops);

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

	     				     doOperation(ops.pop(),num,ops);
	     				     //System.out.println("WEILAAAAAAAAA"+ops.top()
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

		doOperation(ops.pop(),num,ops);
		
	}
	System.out.println("Totale: "+num.pop());	
		
		
}
		
	
    private static void doOperation(char c, MyStack<Integer> num, MyStack<Character> ops){

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
					
					top= num.pop();
					  /*controllo se lo stack dei segni non � vuota(entro nel caso in cui il numero � il primo della stringa che � senza segno perch�
					   * positivo
					   */
					  if(!ops.isEmpty())	  
					  {  
						  /*controllo se il segno � positivo*/
						  if(ops.top()=='+')
						  /*se lo � allora vedo prima che segno ha l'operazione di sottrazoipnme che andr� a fare*/
							  /* se il risoltato � positivo allora metto il ris in coda allo stack e non tocco lo stack dei segni
							   * perch� alla successiva iterazione verr� fatta la somma col numero successivo
							   * */
							  num.push(num.pop()+top);
							  
						  else
							  if(ops.top()=='-')
						  {
							 // System.out.println("sonoqui");
							  /* qui viene il bello: il ris � < 0 allora salvo il modulo del numero nello stack e rimuovo il segno + dallo stack
							   * delle operazioni e ci metto un meno cos� dopo alla prossima iterazione verr� correttemente eseguita una sottrazione
							   */
							 
							  if((-num.top()+top)>0){
								  num.push(Math.abs(-num.pop()+top));
								  ops.pop();
								  ops.push('+');
								  
							  }
							 /*altrimenti non fai niente, rimango il - in quanto il ris � cmq numero negativo*/
				
						 
					  }else{
						  //caso della parentesi: non faccio pop perch� lo faccio sopra dopo  he la funzione � stata richiamata
						  num.push(num.pop()+top);
					  }
					  }else{
						  /*questo � il caso in cui siamo alla fine ovvero sono rimasti solo due numeri e quindi il segno del secondo numero non c'�
						   * Allora qui non mi interessa il segno del risultato e salvo il risultato nello stack
						   */
						  num.push(num.pop()+top);
					  }
			    	
			        break;	
				    	
				}
				  /*dovrebbe essere risolto finalmente il problema, altro che inversione dello stack!*/
				case '-':{
					  /*salvo il sottraendo che visto lo stack � l'ultimo elemento*/
					  top= num.pop();
					  /*controllo se lo stack dei segni non � vuota(entro nel caso in cui il numero � il primo della stringa che � senza segno perch�
					   * positivo
					   */
					  if(!ops.isEmpty())	  
					  {  
						  /*controllo se il segno � positivo*/
						  if(ops.top()=='+'){
						  /*se lo � allora vedo prima che segno ha l'operazione di sottrazoipnme che andr� a fare*/
						  if(num.top()-top>0){
							  /* se il risoltato � positivo allora metto il ris in coda allo stack e non tocco lo stack dei segni
							   * perch� alla successiva iterazione verr� fatta la somma col numero successivo
							   * */
							  num.push(num.pop()-top);
							  
						  }else
						  {
							 // System.out.println("sonoqui");
							  /* qui viene il bello: il ris � < 0 allora salvo il modulo del numero nello stack e rimuovo il segno + dallo stack
							   * delle operazioni e ci metto un meno cos� dopo alla prossima iterazione verr� correttemente eseguita una sottrazione
							   */
							  num.push(Math.abs(num.pop()-top));
							  ops.pop();
							  ops.push('-');
							  
						  }
					  }else if(ops.top()=='-'){
						  //System.out.println((-num.top())-top);
						  /*siamo nel caso che c'� il segno meno. 
						   * Allora faccio al sottrazione del numero con il meno davanti in modo da avere il risultato corretto e mi salvo il modulo
						   * di tale numero nello stack num.
						   * Mi conservo il meno (non lo tolgo dallo stack delle ops) perch� alla prossima iterazione verr� correttemente eseguita
						   * la sottrazione con numero successivo.
						   */
						  num.push(Math.abs((-num.pop())-top));
						 
					  }else{
						  //caso della parentesi: non faccio pop perch� lo faccio sopra dopo  he la funzione � stata richiamata
						  num.push(num.pop()-top);
					  }
					  }else{
						  /*questo � il caso in cui siamo alla fine ovvero sono rimasti solo due numeri e quindi il segno del secondo numero non c'�
						   * Allora qui non mi interessa il segno del risultato e salvo il risultato nello stack
						   */
						  num.push(num.pop()-top);
					  }
			    	
			        break;
			    }
			  
    }
    }
}
