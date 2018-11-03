import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
/**
 * The interface for the calculator
 */
public interface Calculator {
  /**
   * Derive an english language result from an english language request.
   * Read the source README.MD for the full description.
   *
   * @param request the request
   * @return the response
   * @throws IllegalArgumentException On any malformed or excessive request
   */
  
  DualHashBidiMap<String, Integer> stringToIntMap=new DualHashBidiMap<String, Integer>();
  void initialiseHashes();
  String calculate (String request) throws IllegalArgumentException; 
  int addition(String[] requestArray,int  operator_index) throws IllegalArgumentException; 
  int subtraction(String[] requestArray,int  operator_index) throws IllegalArgumentException;    
  int multiplication(String[] requestArray,int  operator_index) throws IllegalArgumentException; 
  int[] division(String[] requestArray,int  operator_index) throws IllegalArgumentException;
  int StringToInt(String[] request) throws IllegalArgumentException; ;
  String intToString(int result);
  
  
}
