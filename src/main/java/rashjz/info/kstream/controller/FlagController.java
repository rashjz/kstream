package rashjz.info.kstream.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rashjz.info.kstream.runner.AppStreamRunner;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FlagController {
    private final AppStreamRunner appStreamRunner;

    @GetMapping("/{topic}/{bootstrapServers}")
    public void startTopic(@PathVariable String topic, @PathVariable String bootstrapServers) {
        log.info("topic  bootstrapServers {} {} {}", topic, bootstrapServers, appStreamRunner);
        appStreamRunner.register(topic, bootstrapServers);
    }

    @GetMapping("/info")
    public String info() {
        return "app is running";
    }
}
