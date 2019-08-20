package rashjz.info.kstream.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rashjz.info.kstream.domain.InputData;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MappingConsumingStrategy {

    private final ObjectMapper objectMapper;

    public Optional<InputData> getInputMessage(final String jsonResponse) {
        ObjectReader objectReader = objectMapper.readerFor(InputData.class);

        try {
            return Optional.ofNullable((InputData) objectReader.readValue(jsonResponse))
                    .map(obj -> {
                        obj.setTimeStamp(System.currentTimeMillis());
                        return obj;
                    });
        } catch (IOException e) {
            log.debug("Exception while trying to read value from kafka telemetry. " +
                    "Skipping the message.", e);
        }

        return Optional.empty();
    }


}
