public class ResponsiveUI implements Runnable{
	private int time;
	private static boolean inputting = true;
	public static int count = -1;
	private int taskNum;
	private boolean finished = false;
	public ResponsiveUI(int time){
		this.time = time;
		count++;
		taskNum = count;
	}
	
	public void run(){
		try{
		
			Thread.sleep(time);
			finished = true;
			//System.out.println("Finished Task: " + count);
		}
		catch(InterruptedException ie){
			System.out.println(ie.toString());
		}
	}
	
	public int getTaskNum(){
		return taskNum;
	}
	
	public boolean getFinished(){
		return finished;
	}
	
	public static void main(String[] args){
		ResponsiveUI[] uiArray = new ResponsiveUI[10];
		boolean[] finishedTasks = new boolean[10];
		boolean taskFinished = false;
		boolean allFinished = false;
		for(int c = 0; c <10; c++){
			uiArray[c] = null;
			finishedTasks[c] = false;
		}
		for(int c = 0;c < 10;c++){
			taskFinished = false;
			System.out.println("Enter the duration (in ms) of task: " + c);
			String str = System.console().readLine();
			
			int duration = Integer.parseInt(str);
			
			uiArray[c] =  new ResponsiveUI(duration);
			Thread t = new Thread(uiArray[c]);
			t.start();
			String result = "Finished tasks:";
			for(int m = 0;m < c + 1;m++){
				if(uiArray[m].getFinished() && !finishedTasks[m]){
					taskFinished = true;
					finishedTasks[m] = true;
					result = result + " " + m;
				}
			}
			if(taskFinished) 
				System.out.println(result);
			
		}
		
		while(!allFinished){
			taskFinished = false;
			String result = "Finished tasks:";
			allFinished = true;
			for(int c = 0; c < 10; c++){
				if(!uiArray[c].getFinished()){
					allFinished = false;
				}else if(uiArray[c].getFinished() && !finishedTasks[c]){
					taskFinished = true;
					finishedTasks[c] = true;
					result = result + " " + c;
				}
			}
			if(taskFinished) 
				System.out.println(result);
		}
		
		
	}
	
	
}
