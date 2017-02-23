// Timer:
// 1) a facility for threads to schedule tasks for future execution in a background thread
// 2) tasks may be scheduled for one-time execution, or for repeated execution at regular intervals
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class Task extends TimerTask {
    @Override
    public void run() {
        System.out.println("Do Task");
    }
}

class PeriodTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("Do PeriodTask");
    }
}

public class UtilTimer {

    public static void main(String[] args) {
        Timer timer = new Timer();
        // 1) schedule(TimerTask task, long delay):
        timer.schedule(new Task(), 500);
        // 2) schedule(TimerTask task, long delay, long period)
        timer.schedule(new PeriodTask(), 500, 500);
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 3) Timeout newTimeout(TimerTask task, long delay, TimeUnit unit):
        //    schedules the specified TimerTask for one-time execution after the specified delay
        System.out.println("Timer canceled 3 seconds later");
        timer.cancel();
    }
}
