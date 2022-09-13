package pe.com.nttdata.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import pe.com.nttdata.model.AccountSaving;

@Component
public class KafkaJsonProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonProducer.class);
    private final KafkaTemplate<String, AccountSaving> kafkaTemplate;
    
    public KafkaJsonProducer(@Qualifier("kafkaJsonTemplate") KafkaTemplate<String, AccountSaving> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(AccountSaving user) {
        LOGGER.info("Producing message {}", user);
        this.kafkaTemplate.send("PROJECT-FINAL-JSON", user);
    }
}
