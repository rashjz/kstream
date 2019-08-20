package rashjz.info.kstream.providers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rashjz.info.kstream.runner.AppStreamRunner;


@Slf4j
@Component
public class KafkaConsumerProviderSetup {
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String INPUT_TOPIC = "inputTopic";

    private final AppStreamRunner listener;

    public KafkaConsumerProviderSetup(AppStreamRunner listener) {
        this.listener=listener;
       setupConsumer();
    }

     private void setupConsumer() {
        log.debug("setting up the new consumer");
        listener.register(INPUT_TOPIC, BOOTSTRAP_SERVERS);
    }

    private void closeConsumer() {
        listener.close();
    }

}
