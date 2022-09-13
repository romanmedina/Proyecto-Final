package pe.com.nttdata.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pe.com.nttdata.entity.AccountSaving;

@Component
public class KafkaJsonConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaJsonConsumer.class);

    @KafkaListener(topics = "PROJECT-FINAL-JSON", groupId = "group_id")
    public void consume(AccountSaving message) {
        logger.info("Consuming Message {}", message);
    }
}
