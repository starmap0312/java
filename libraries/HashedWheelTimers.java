// HashedWheelTimer:
// a Timer optimized for approximated I/O timeout scheduling
// 1) this timer does not execute the scheduled TimerTask on time
// 2) on every tick, it will check if there are any TimerTasks behind the schedule and execute them
// 3) in most network applications, I/O timeout does not need to be accurate
//    therefore, the default tick duration is 100 milliseconds and you will not need to try different configurations in most cases
// 4) two important parameters:
//    tickDuration : the duration between tick (it will go to next slot at each tick)
//    ticksPerWheel: the size of the wheel     (the number of slots in the wheel)
//    1 Round = tickDuration * ticksPerWheel
import io.netty.util.Timer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.HashedWheelTimer;
import java.util.concurrent.TimeUnit;

class NamedTask implements TimerTask {
    @Override
    public void run(Timeout timeout) {
        System.out.println("Do NamedTask");
    }
}
public class HashedWheelTimers {

    public static void main(String[] args) {
        HashedWheelTimer timer = new HashedWheelTimer();
        // 1) newTimeout(TimerTask task, long delay, TimeUnit unit):
        //    schedules the specified TimerTask for one-time execution after the specified delay
        // task1:
        timer.newTimeout(new NamedTask(), 500, TimeUnit.MILLISECONDS); 
        // task2:
        timer.newTimeout(
            new TimerTask() {
                @Override
                public void run(Timeout timeout) {
                    System.out.println("Do Anonymous Task");
                }
            }, 
            1000,
            TimeUnit.MILLISECONDS
        ); 
        // task3:
        timer.newTimeout(
            (x) -> { System.out.println("Do Lambda Task"); },
            1500,
            TimeUnit.MILLISECONDS
        );

        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 2) stop():
        //    releases all resources acquired by Timer and cancels all scheduled tasks not executed yet
        System.out.println("Timer stopped 2 seconds later");
        timer.stop();
    }
}
