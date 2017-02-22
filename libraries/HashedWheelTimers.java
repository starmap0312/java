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

import io.netty.util.HashedWheelTimer;

public class HashedWheelTimers {

    public static void main(String[] args) {
    }
}
