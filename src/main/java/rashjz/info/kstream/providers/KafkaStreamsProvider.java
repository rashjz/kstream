package rashjz.info.kstream.providers;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaStreamsProvider {

    public KafkaStreams get(final Topology topology, final Properties properties) {
        return new KafkaStreams(topology, properties);
    }

}
