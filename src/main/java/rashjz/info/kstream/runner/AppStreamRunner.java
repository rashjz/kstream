package rashjz.info.kstream.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.stereotype.Service;
import rashjz.info.kstream.config.MappingConsumingStrategy;
import rashjz.info.kstream.providers.KafkaPropertiesProvider;
import rashjz.info.kstream.providers.KafkaStreamsProvider;
import rashjz.info.kstream.recorvery.KafkaUncaughtExceptionHandler;

import java.util.Optional;
import java.util.Properties;

import static org.apache.kafka.streams.KafkaStreams.State.REBALANCING;
import static org.apache.kafka.streams.KafkaStreams.State.RUNNING;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppStreamRunner {

     private final KafkaPropertiesProvider propertiesProvider;
    private final KafkaStreamsProvider kafkaStreamsProvider;
    private final MappingConsumingStrategy consumingStrategy;
    private KafkaStreams telemetryStream;

    public void register(final String topic,
                         final String bootstrapServers) {
        try {
            Properties properties = propertiesProvider.get(bootstrapServers);
            log.info("creating stream for topic {}", topic);
            StreamsBuilder builder = getStreamBuilder(topic);

            KafkaStreams stream = kafkaStreamsProvider.get(builder.build(), properties);
            KafkaUncaughtExceptionHandler handler = new KafkaUncaughtExceptionHandler();

            stream.setUncaughtExceptionHandler(handler);
            stream.setStateListener((n, o) -> {
                if (o.equals(REBALANCING) && n.equals(RUNNING)) {
                    handler.resetInvocationCounter();
                }
            });

            stream.start();
        } catch (KafkaException e) {
            log.warn("Exception while trying to setup kafka telemetry engine stream. " +
                    "Engine telemetry stream will not be setup.", e);
        }
    }

    private StreamsBuilder getStreamBuilder(final String topic) {

        StreamsBuilder builder = new StreamsBuilder();

        builder.<Long, String>stream(topic)
                .mapValues(consumingStrategy::getInputMessage)
                .filter((key, val) -> val.isPresent())
                .mapValues(Optional::get)
                .foreach((aLong, inputData) -> log.info("received value {} {}", aLong, inputData));

        return builder;
    }

    public void close() {
        if (telemetryStream != null) {
            telemetryStream.close();
            telemetryStream.cleanUp();
        }
    }


}
