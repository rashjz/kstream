package rashjz.info.kstream.providers;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.*;

@Service
@RequiredArgsConstructor
public class KafkaPropertiesProvider {
    private static final String APP_ENGINE_STREAM_NAME = "app-engine-stream";
    private static final String APP_ENGINE_CONSUMER_NAME = "app-engine-stream";
    private static final String POLLING_STRATEGY = "earliest";

    public Properties get(final String bootstrapServers) {
        Properties properties = new Properties();
        properties.putAll(ImmutableMap
                .builder()
                .put(APPLICATION_ID_CONFIG, APP_ENGINE_STREAM_NAME)
                .put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
                .put(DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class)
                .put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass().getName())
                .put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName())
                .put(AUTO_OFFSET_RESET_CONFIG, POLLING_STRATEGY)
                .put(GROUP_ID_CONFIG, APP_ENGINE_CONSUMER_NAME)
                .build()
        );

        return properties;
    }
}
