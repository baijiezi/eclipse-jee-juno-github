package foundation.exception.selfException;
/**
* @功能 自定义异常 ObjAlreadyExistException
*
*/
public class ObjAlreadyExistException extends Exception 
{
   private static final long serialVersionUID = 1L;
   
   public ObjAlreadyExistException() 
   {
      super();
   }
   
   public ObjAlreadyExistException(String message) 
   {
      super(message);
   }
   
   public ObjAlreadyExistException(String message, Throwable cause) 
   {
      super(message, cause);
   }
   
   public ObjAlreadyExistException(Throwable cause)
   {
      super(cause);
   }
}
