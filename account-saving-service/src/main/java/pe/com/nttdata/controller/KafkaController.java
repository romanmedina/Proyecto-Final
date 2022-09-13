package pe.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.nttdata.model.AccountSaving;
import pe.com.nttdata.producer.KafkaJsonProducer;
import pe.com.nttdata.producer.KafkaStringProducer;
import pe.com.nttdata.service.IAccountSavingService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/accounts/kafka")
public class KafkaController {

    private final KafkaStringProducer kafkaStringProducer;

    private final KafkaJsonProducer kafkaJsonProducer;
    
    @Autowired
    private IAccountSavingService accountServiceKafka;
    
    @Autowired
    KafkaController(KafkaStringProducer kafkaStringProducer, KafkaJsonProducer kafkaJsonProducer) {
        this.kafkaStringProducer = kafkaStringProducer;
        this.kafkaJsonProducer = kafkaJsonProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.kafkaStringProducer.sendMessage(message);
    }
    
    @PostMapping(value = "/account_saving/{id}")
    public void sendMessageToKafkaJsonTopic(@PathVariable Integer id) {
    	Flux<AccountSaving> accSaving= accountServiceKafka.findById(id);
    	AccountSaving accSav = accSaving.blockFirst();
        this.kafkaJsonProducer.sendMessage(accSav);
    }
    
    
}
