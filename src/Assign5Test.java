import java.util.StringTokenizer;
import junit.framework.TestCase;
import java.io.*;

/**
 * testing framework for typed jam
 *
 **/
public class Assign5Test extends TestCase {

  public Assign5Test (String name) {
    super(name);
  }
  
 
  private void eagerCheck(String name, String answer, String program) {
    Interpreter interp = new Interpreter(new StringReader(program));
    assertEquals("by-value-value " + name, answer, interp.eagerEval().toString());
  }

  private void lazyCheck(String name, String answer, String program) {
    Interpreter interp = new Interpreter(new StringReader(program));
    assertEquals("by-value-name " + name, answer, interp.lazyEval().toString());
  }

  private void allCheck(String name, String answer, String program) {
    eagerCheck(name, answer, program);
    lazyCheck(name, answer, program);
  }

  

  public void testMathOp() {
    try {
      String output = "18";
      String input = "2 * 3 + 12";
      allCheck("mathOp", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("mathOp threw " + e);
    }
  } //end of func
  

  public void testParseException() {
    try {
      String output = "haha";
      String input = " 1 +";
      allCheck("parseException", output, input );

         fail("parseException did not throw ParseException exception");
      } catch (ParseException e) {   
         //e.printStackTrace();
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("parseException threw " + e);
    }
  } //end of func
  

  public void testAppend() {
    try {
      String output = "(1 2 3 1 2 3)";
      String input = "let append:  (list int, list int -> list int) :=       map x: list int, y: list int to         if x = null: int then y else cons(first(x), append(rest(x), y));     s: list int := cons(1,cons(2,cons(3,null: int))); in append(s,s)";
      allCheck("append", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("append threw " + e);
    }
  } //end of func
  

  public void testNull() {
    try {
      String output = "()";
      String input = "null: list (int, bool, list ref int -> unit)";
      allCheck("null", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("null threw " + e);
    }
  } //end of func
  

  public void testEmptyBlock() {
    try {
      String output = "0";
      String input = "{ }";
      allCheck("emptyBlock", output, input );

         fail("emptyBlock did not throw ParseException exception");
      } catch (ParseException e) {   
         //e.printStackTrace();
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("emptyBlock threw " + e);
    }
  } //end of func
  

  public void testBlock() {
    try {
      String output = "1";
      String input = "{3; 2; 1}";
      allCheck("block", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("block threw " + e);
    }
  } //end of func
  

  public void testDupVar() {
    try {
      String output = "ha!";
      String input = "let x: int :=3; x:int :=4; in x";
      allCheck("dupVar", output, input );

         fail("dupVar did not throw SyntaxException exception");
      } catch (SyntaxException e) {   
         //e.printStackTrace();
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("dupVar threw " + e);
    }
  } //end of func
  

  public void testRefApp() {
    try {
      String output = "(ref 17)";
      String input = "let x: ref int := ref 10; in {x <- 17; x}";
      allCheck("refApp", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("refApp threw " + e);
    }
  } //end of func
  

  public void testBangApp() {
    try {
      String output = "true";
      String input = "let x: ref int := ref 10; y:ref ref int := ref x; in x=!y";
      allCheck("bangApp", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("bangApp threw " + e);
    }
  } //end of func
  

  public void testAssign() {
    try {
      String output = "true";
      String input = "let x: int :=5; y: bool :=true; in x !=y";
      allCheck("assign", output, input );

         fail("assign did not throw TypeException exception");
      } catch (TypeException e) {   
         //e.printStackTrace();
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("assign threw " + e);
    }
  } //end of func
  

  public void testBadAssign() {
    try {
      String output = "0";
      String input = "let x: int := 10; in x <- 5";
      allCheck("badAssign", output, input );

         fail("badAssign did not throw TypeException exception");
      } catch (TypeException e) {   
         //e.printStackTrace();
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("badAssign threw " + e);
    }
  } //end of func
  

  public void testBadIf() {
    try {
      String output = "oops";
      String input = "let x: int :=5; y: bool :=true; func:(->int) := map to 101; f:(->bool) := map to true; in if y then true else 100";
      allCheck("badIf", output, input );

         fail("badIf did not throw TypeException exception");
      } catch (TypeException e) {   
         //e.printStackTrace();
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("badIf threw " + e);
    }
  } //end of func
  
  public void testBadCons() {
      try {
        String output = "wow";
        String input = "let x:list int := cons(10,cons(10,null:bool)); func:(->int) := map to 101; in func()";
        allCheck("badCons", output, input );

           fail("badCons did not throw TypeException exception");
        } catch (TypeException e) {   
           //e.printStackTrace();
        
      } catch (Exception e) {
        e.printStackTrace();
        fail("badCons threw " + e);
    }
  } //end of func
  
  public void testBadClosure1() {
      try {
        String output = "gyjj";
        String input = "let x:list int := cons(10,cons(10,null:int)); func:(->int) := map to true; in func()";
        allCheck("badClosure1", output, input );

           fail("badClosure1 did not throw TypeException exception");
        } catch (TypeException e) {   
           //e.printStackTrace();
        
      } catch (Exception e) {
        e.printStackTrace();
        fail("badClosure1 threw " + e);
    }
  } //end of func
  
  public void testBadClosure2() {
      try {
        String output = "gyjj";
        String input = "let x:list int := cons(10,cons(10,null:int)); func:(int,int->int) := map a:int,b:int to a*b; in func(1,2,3)";
        allCheck("badClosure2", output, input );

           fail("badClosure2 did not throw TypeException exception");
        } catch (TypeException e) {   
           //e.printStackTrace();
        
      } catch (Exception e) {
        e.printStackTrace();
        fail("badClosure2 threw " + e);
    }
  } //end of func
  
  public void testEvalException() {
      try {
        String output = "jygg";
        String input = "first(null : int)";
        allCheck("evalException", output, input );

           fail("evalException did not throw EvalException exception");
        } catch (EvalException e) {   
           //e.printStackTrace();
        
      } catch (Exception e) {
        e.printStackTrace();
        fail("evalException threw " + e);
    }
  } //end of func

  public void testTypes() {
    try {
        allCheck("assign", "6", "2+4" );
        allCheck("assign", "8", "2*4" );
        allCheck("assign", "-2", "2-4" );
        allCheck("assign", "0", "2/4" );
        allCheck("assign", "false", "let x:(->int):=map to 101; y:(->int):=map to 101; in x=y" );
        allCheck("assign", "true", "let x:(->int):=map to 101; y:(->int):=map to 101; in x!=y" );
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("assign threw " + e);
    }
  } //end of func
  
  public void testBadPrim() {
      try {
        String output = "en";
        String input = "null? = null?)";
        allCheck("badPrim", output, input );

           fail("badPrim did not throw ParseException exception");
        } catch (ParseException e) {   
           //e.printStackTrace();
        
      } catch (Exception e) {
        e.printStackTrace();
        fail("badPrim threw " + e);
    }
  } //end of func
  
  
}





