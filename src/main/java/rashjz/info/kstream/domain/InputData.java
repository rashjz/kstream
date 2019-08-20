package rashjz.info.kstream.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.function.Consumer;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputData implements Serializable {
//{ "key":"test-key", "value":"val"}

    private  String key;
    private  String value;
    private Long timeStamp;



    public static class InputDataBuilder {
        private  String key;
        private  String value;
        private Long timeStamp;

        InputDataBuilder with(Consumer<InputDataBuilder> builderFunction) {
            builderFunction.accept(this);
            return this;
        }


        InputData createPerson() {
            return new InputData(key, value, timeStamp);
        }
    }

    public static void main(String[] args) {
        InputData data = new InputDataBuilder()
                .with(dataBuilder -> {
                    dataBuilder.key = "Mr.";
                    dataBuilder.value = "John";
                    dataBuilder.timeStamp = System.currentTimeMillis();
                })
                .createPerson();
        System.out.println(data);
    }
}