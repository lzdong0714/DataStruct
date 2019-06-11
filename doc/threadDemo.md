{
	并发：多个CPU同时执行多个任务
	并发：一个CPU（时间片）同时执行多个任务
	java.exe 至少有三个线程：main()主线程，GC()垃圾回收线程，异常处理线程。
	多线程响应，对图像化界面更有意义.
	1 main()函数是一个线程，是被JVM启动时点用，线程的名字叫main
	2 实现一个线程，必须创建 Thread 实例， Override run()方法，并且调用start()方法。
	3 在JVN启动后，实际哟多个线程，但是至少有一个是非守护线程
	4 当你调用一个线程的start()方法时候，此时至少有两个线程，
	  一个是调用的线程，一个是start()的线程
	5 线程的生命周期: new ,runnable,running,block,terminated

	1 创建线程从 Thread-0 开始创建，通过static EnumNUmber++
	2 Thread(ThreadGroup,Runnable，)
		——>init()——>start0——>run(Runnable target){ if(target != null) target.run();}
	3 如果构造线程没有输入 ThreadGroup,那么就会是设定为该线程父线程的 ThreadGroup,
	4 public Thread(ThreadGroup,Runnable,String,long stackSize)
		stackSize: 线程栈的大小,与平台有关。
	
	5 JVM构成：
	  5-1： 方法区，属于线程栈的一部分。


	  5-3： 虚拟机栈（）：线程私有的
	
	  5-4: 本地方法区 (jre NIO)
	
	  5-5 : 程序计数器
	
	   5-2： 堆内存 (heap memory)


	  C++ 层： 执行引擎 本地库接口 本地方法
}

{
	cmd jps
}
{
	   /**
     * 用win+R 输入jconsole 可以连接java面板查询线程
          * 用win+R 输入cmd ，输入jps可以查看启动进程
          * 输入jstack 查看线程堆
               *
          ​     */
}

### Thread 中wait 和sleep的区别

1 sleep is the method of Thread ,but the wait is the method of Object

2 sleep will not release the object monitor (Lock),but the wait can release the LOCK

这个是一个重要的区别，参看chapter9 的DifferenceOfWaiAndSleep

3 use slepp not depend on the monitorm but wait need object

4 use sleep is not need wake up ,but wait need 











