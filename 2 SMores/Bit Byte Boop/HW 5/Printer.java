public class Printer {
    MinHeap<PrintJob> printQ = new MinHeap(10);
    int timeLeft = 0; //this is basically the printer
    //this keeps track of the number of jobs that actually get to the printer
    int jobsCompleted = 0;
    //this keeps track of the total time spent on the printer by all jobs that made it to the printer.
    int waitTime = 0;
    
    String name = "";
    
    public Printer(String name) {
        this.name = name;
    }
    
    public void next() {
        if(!isFree()) {
            //If it is, decrement the time left for the current job
            timeLeft--;
            //Check to see if anyone is waiting
            if(!printQ.isEmpty())
                waitTime++;
        } else { //printer is free
            //Start the next job on the queue
            if(!printQ.isEmpty()) {
                PrintJob job = printQ.remove();
                System.out.println(job);
                timeLeft = job.getTimeRequired();  //reset print clock
                jobsCompleted++; //count the number of jobs sent to the printer
            }
        }
    }
    
    public boolean isFree() {
        return timeLeft<=0;
    }
    
    public String toString() {
        String output = name;
        output += "\nNumber of jobs serviced: " + jobsCompleted;
        output += "\nNumber of jobs still on queue: " + printQ.size();
        output += String.format("\nAverage wait time: %.1f\n", ((double) waitTime / jobsCompleted));
        return output;
    }
}
