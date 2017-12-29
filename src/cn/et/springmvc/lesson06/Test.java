package cn.et.springmvc.lesson06;

public class Test {

	/**
	 * 异步：多个线程同时执行，无法判断，谁先执行
	 * 同步：一次只允许一个线程执行
	 * @throws InterruptedException 
	 */
	
	public static void main(String[] args) throws InterruptedException {
		
		MyThread m=new MyThread();
		m.start();
		m.join();
		System.out.println("main method");
	}
	
	static class MyThread extends Thread{
		@Override
		public void run() {
			System.out.println("线程执行run方法");
		}
	}
}
