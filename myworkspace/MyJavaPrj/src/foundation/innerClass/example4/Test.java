package foundation.innerClass.example4;

public class Test
{
	public static void main(String[] args)
	{
		Callee2 callee2 = new Callee2();
		
		callee2.increment();
		
		Incrementable incrementable = callee2.getCallbackReference();
		incrementable.increment();
	}
}
