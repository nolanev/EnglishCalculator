
import java.util.Arrays;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class CalculatorImpl implements Calculator {

 //@Override
  public static final DualHashBidiMap<String, Integer> stringToIntMap=new DualHashBidiMap<String, Integer>();
  //@Override
  public void initialiseHashes(){
	  /*
	* initalises hashtable
	*/  
	stringToIntMap.put("twenty", 20);
	stringToIntMap.put("thirty", 30);
	stringToIntMap.put("forty", 40);
	stringToIntMap.put("fifty", 50);
	stringToIntMap.put("sixty", 60);
	stringToIntMap.put("seventy", 70);
	stringToIntMap.put("eighty", 80);
	stringToIntMap.put("ninety", 90);
	stringToIntMap.put("zero", 0);
	stringToIntMap.put("one", 1);
	stringToIntMap.put("two", 2);
	stringToIntMap.put("three",3);
	stringToIntMap.put("four",4);
	stringToIntMap.put("five",5);
	stringToIntMap.put("six",6);
	stringToIntMap.put("seven",7);
	stringToIntMap.put("eight",8);
	stringToIntMap.put("nine",9);
	stringToIntMap.put("ten",10);
	stringToIntMap.put("eleven",11);
	stringToIntMap.put("twelve",12);
	stringToIntMap.put("thirteen",13);
	stringToIntMap.put("fourteen",14);
	stringToIntMap.put("fifteen",15);
	stringToIntMap.put("sixteen",16);
	stringToIntMap.put("seventeen",17);
	stringToIntMap.put("eighteen",18);
	stringToIntMap.put("nineteen",19);
  }
  
  //@Override
  public String calculate(String request) {
	/*
	* finds operation to be preformed and calls corresponding function returning the result as a string
	* @param request the request
    * @return the response
    * @throws IllegalArgumentException if no operator is found
	*/  
	if(request==null){
		 throw new IllegalArgumentException("Argument cannot be null");
	}
	initialiseHashes();
	String [] requestArray=request.split(" ");
	String result="";
	boolean valid=false;
	
	for (int i =0; i<requestArray.length; i++){// loop until operator is found
		if (requestArray[i].equals("plus")){
			valid=true;
			result= intToString(addition(requestArray, i));
			break;
		}
		else if (requestArray[i].equals("minus")){
			valid=true;
			result= intToString(subtraction(requestArray,i));
			break;
		}
		else if (requestArray[i].equals("multiplied")){
			valid=true;
			result=intToString(multiplication(requestArray, i));
			break;
			
		}
		else if (requestArray[i].equals("divided")){
			valid=true;
			int[] divisionResult= division(requestArray, i);
			if (divisionResult[1]>0){
				result= intToString(divisionResult[0]) +" with a remainder of " +intToString(divisionResult[1]);
			}
			else{
				result= intToString(divisionResult[0]);
			}
			break;
		}
		
	}
	if (!valid){ //if no operator is found
		throw new IllegalArgumentException("invalid sum");
	}
	return result;
	
  }
  
  //@Override
  public int addition(String[] requestArray, int  operator_index){
	/*
	* spilts requestArray around the operation to find the values to be operated on and preforms addition
	* @param requestArray array of strings that make up the request
    * @return result of addition
    * @throws IllegalArgumentException when arguments > 100 
	*/
	String[] valueA=  Arrays.copyOfRange(requestArray, 0, operator_index); //before operator
	String[] valueB=  Arrays.copyOfRange(requestArray, operator_index+1, requestArray.length); //after operator
	int a= StringToInt(valueA);
	int b= StringToInt(valueB);
	if((a>100)||(b>100)){
		 throw new IllegalArgumentException("Arguments must be < 100 for addition");
	}
	return a+b;
}
  
  //@Override
  public int subtraction(String[] requestArray,int  operator_index){

	/*
	* spilts requestArray around the operation to find the values to be operated on
	* @param requestArray array of strings that make up the request
    * @return result of subtraction
    * @throws IllegalArgumentException when arguments > 100 
	*/
	String[] valueA=  Arrays.copyOfRange(requestArray, 0, operator_index);
	String[] valueB=  Arrays.copyOfRange(requestArray, operator_index+1, requestArray.length);
	int a= StringToInt(valueA);
	int b= StringToInt(valueB);
	if((a>100)||(b>100)){
		 throw new IllegalArgumentException("Arguments must be < 100 for subtraction");
	}	
	return a-b;
}
  
  //@Override
  public int multiplication(String[] requestArray,int  operator_index){
	  /*
	* spilts requestArray around the operation to find the values to be operated on
	* @param requestArray array of strings that make up the request
    * @return result of subtraction
    * @throws IllegalArgumentException when arguments > 100
	*/
	String[] valueA=  Arrays.copyOfRange(requestArray, 0, operator_index);
	String[] valueB=  Arrays.copyOfRange(requestArray, operator_index+2, requestArray.length);
	int a= StringToInt(valueA);
	int b= StringToInt(valueB);
	if((a>100)||(b>100)){
		 throw new IllegalArgumentException("Arguments must be < 100 for multiplication");
	}	
	return a*b;
}
  
  //@Override
  public int[] division(String[] requestArray,int  operator_index){
	/*
	* spilts requestArray around the operation to find the values to be operated on
	* @param requestArray array of strings that make up the request
    * @return integer array containing the result and remainder of the division
    * @throws IllegalArgumentException when arguments > 10000 
	*/
	String[] valueA=  Arrays.copyOfRange(requestArray, 0, operator_index);
	String[] valueB=  Arrays.copyOfRange(requestArray, operator_index+2, requestArray.length);
	int a= StringToInt(valueA);
	int b= StringToInt(valueB);
	
	if((a>10000)||(b>10000)){
		 throw new IllegalArgumentException("Arguments must be < 10,000 for division");
	}
	int[] result= {a/b, a%b};
	return result;
}
  
  //@Override
  public int StringToInt(String[] requestArray){
	/*
	* iterates backwards through the request array using values as key to the stringToIntMap
	* corresponding values are added to the returned result
	* @param requestArray array of strings that make up the request
    * @return integer equivalent of input string
    * @throws IllegalArgumentException on malformed inputs
	*/  
	int result=0;
	int i= requestArray.length-1;
	while (i > -1) { //interates through request array from right to left
		
		if (stringToIntMap.containsKey(requestArray[i])){	//values 0-99		
			result+= stringToIntMap.get(requestArray[i]);
        }
		else if (requestArray[i].equals("hundred")){	//values 100-999
			
			result+=(100*stringToIntMap.get(requestArray[i-1]));
			i--; 
		}
		else if  (requestArray[i].equals("thousand")){	//values 1000-10000
			result+=(1000*stringToIntMap.get(requestArray[i-1]));
			i--;
		}
		else if (!requestArray[i].equals("and")){
			throw new IllegalArgumentException("Invalid sum");
		}
		
		i--;
		
	}

	return result;
  }
  
  //@Override
  public String intToString(int intResult){
	/*
	* find number of thousand, hundreds and tens in input value
	* uses these as keys to the reversed hashmap
	* adds values to the output string
	* @param integer to be converted
    * @return string equivalent of input
	*/
	String stringResult="";
	if (intResult<0){
		stringResult+="minus ";
		intResult*=-1;
	}
    int thousand=intResult/1000;
	intResult-=thousand*1000;
	int hundred=intResult/100;
	intResult-=hundred*100;
	int ten=intResult/10;
	intResult-=ten*10;
		
	if (thousand>0){ // values 1000-10000
		
		stringResult+=stringToIntMap.inverseBidiMap().get(thousand) + " thousand";
		
		if((hundred==0)&&(intResult>0)){
			stringResult+=" and ";
		}
		else if (hundred!=0){
			stringResult+=" ";
		}
		
	}
	if (hundred>0){ //100-999
		stringResult+=stringToIntMap.inverseBidiMap().get(hundred) + " hundred";
		if(intResult>0){
			stringResult+=" and ";
		}
	}
	
	if (ten>1){ //20-99
		stringResult+=  stringToIntMap.inverseBidiMap().get(ten*10);
		if(intResult!=0){
			stringResult+= " " +stringToIntMap.inverseBidiMap().get(intResult);
		}
	}
	else if (ten==1){	//10-19	
		stringResult+= stringToIntMap.inverseBidiMap().get(10+intResult);
		intResult=0;
	}
	
	else if(intResult!=0){ //0-9
		stringResult+= stringToIntMap.inverseBidiMap().get(intResult);
	}
	

	
	return stringResult;
	
		
  }
    
}
