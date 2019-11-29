package io.smallrye.reactive.messaging.jms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class RawMessageConsumerBean {

    private final List<ReceivedJmsMessage<?>> messages = new CopyOnWriteArrayList<>();

    @Incoming("jms")
    public CompletionStage<Void> consume(ReceivedJmsMessage<?> v) {
        messages.add(v);
        return v.ack();
    }

    List<ReceivedJmsMessage<?>> messages() {
        return new ArrayList<>(messages);
    }

}
