package cn.cz.czauth.exception;

public class RunLogicException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3695156115567584160L;
    private String webPage;
    private int errorCode;
    private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public RunLogicException(int errorCode, String msg, String webPage){
		super(msg);
		this.errorCode = errorCode;
		this.webPage = webPage;
	}
	public RunLogicException(int errorCode, String msg){
//		super(msg);
		this.message = msg;
		this.errorCode = errorCode;
	}
	public String getWebPage(){
		return webPage;
	}


	public static void main(String[] args) {
		if(1==1)
			throw new RunLogicException(405,"出错误了");

		System.out.println("222");

	}


	public static String ss(){
		return "fg";
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	@Override
	public String toString() {
		return message;
	}
}
