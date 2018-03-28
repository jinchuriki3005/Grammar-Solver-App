//Max Tran, CSE 143, April 28, 2016
//TA: Rebecca Yuen, Section: BL
import java.util.*;

/*this programs allows the client to choose an input
of grammar rule in Backus-Naur Form
and produce element of grammar randomly*/
public class GrammarSolver {
   private Map<String, String[]> grammarBook;
   
   /*Create a grammar solver using BNF rules
   throws an IllegalArgumentException if the list of the rules
   is null or has size of 0 or grammar contains more than 1 rule
   for the same non-terminal
   */
   public GrammarSolver(List<String> rules){
      if(rules == null || rules.size() == 0){
         throw new IllegalArgumentException();
      }
      grammarBook = new TreeMap<String, String[]>();
      for(String rule: rules){
         String[] nonTerminal = rule.split("::=");
         if(grammarBook.containsKey(nonTerminal[0])){
            throw new IllegalArgumentException();
         }         
         String[] grammarParts = nonTerminal[1].split("[|]");
         grammarBook.put(nonTerminal[0], grammarParts);
      }
   }
   
   /* return whether the symbol is non-terminal in the grammar
   throws IllegalArgumentException if the string symbol is null 
   or has size of 0 */
   public boolean contains(String symbol){
      exceptionArgument(symbol);
      return grammarBook.keySet().contains(symbol);
   }
   
   //return all nonterminal symbols in the grammar in a sorted order
   public Set<String> getSymbols(){
      return grammarBook.keySet();
   }
   
   /*If the input string is null or length of 0, 
   throw an IllegalArgumentException, 
   else return a string that is made up of randomly produce 
   occurrences of each symbol in the grammar*/
   public String generate(String symbol){
      exceptionArgument(symbol);
      String result = "";
      if(grammarBook.containsKey(symbol)){
      	String[] rules = grammarBook.get(symbol);
			Random r = new Random();
			int probability = r.nextInt(rules.length);
			String[] randomGrammarRule = rules[probability].trim().split("[ \t]+");
			for (String rule : randomGrammarRule) {
				result += " " +generate(rule);
			}
			return result.trim();
      } else {
         return symbol;
      }
   }
   
   /*throw IllegalArgumentException if the string symbol is null or has
   length of 0 */
   private void exceptionArgument(String symbol){
      if(symbol == null || symbol.length() == 0){
         throw new IllegalArgumentException("ERROR: Null or size = 0");
      }
   }


}