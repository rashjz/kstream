package rashjz.info.kstream.recorvery;

import com.google.common.collect.ImmutableList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newScheduledThreadPool;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class KafkaUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final AtomicInteger invocationCounter;
    private final ScheduledExecutorService scheduler;

    private final LinkedHashMap<Long, TimeUnit> waitTimes;

    public KafkaUncaughtExceptionHandler() {
        this.invocationCounter = new AtomicInteger(0);
        this.scheduler = newScheduledThreadPool(1);
        this.waitTimes = new LinkedHashMap<>();
        waitTimes.put(10L, TimeUnit.SECONDS);
    }

    @Override
    public void uncaughtException(final Thread thread,
                                  final Throwable exception) {
        int counter = invocationCounter.getAndIncrement();
        if (waitTimes.size() > counter) {
            ImmutableList<Map.Entry<Long, TimeUnit>> orderedWaitTimes
                    = ImmutableList.copyOf(waitTimes.entrySet());

            Long timeVal = orderedWaitTimes.get(counter).getKey();
            TimeUnit timeUnit = orderedWaitTimes.get(counter).getValue();
            scheduler.schedule(
                    () -> {
                        log.info("Requesting kafka streams reinitialisation. Attempt no: {}, wait" +
                                " time: {} {}", counter + 1, timeVal, timeUnit);
//                        doSomething
                        log.info("do something");
                    }, timeVal, timeUnit
            );
        } else {
            log.error("Retries count exceeded for kafka telemetry consumer. This means that " +
                    "the AppPortal will not update the Engine statuses until the broker is fixed " +
                    "and the app is restarted.");
        }
    }

    public void resetInvocationCounter() {
        invocationCounter.set(0);
    }

}
