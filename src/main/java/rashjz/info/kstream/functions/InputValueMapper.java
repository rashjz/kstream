package rashjz.info.kstream.functions;

import org.apache.kafka.streams.kstream.ValueMapper;

import java.util.Optional;

@FunctionalInterface
public interface InputValueMapper extends ValueMapper<String, Optional<String>> {

}