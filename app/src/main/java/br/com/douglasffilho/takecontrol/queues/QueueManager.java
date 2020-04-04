package br.com.douglasffilho.takecontrol.queues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class QueueManager {

    private static Map<Queues, List<Function<Object, Void>>> queueStream = new ConcurrentHashMap<>();

    public static void publish(final Queues queue, final Object message) {
        final List<Function<Object, Void>> consumers = queueStream.getOrDefault(queue, new ArrayList<>());

        if (consumers != null) {
            consumers.forEach((consumer) -> consumer.apply(message));
        }
    }

    public static void subscribe(final Queues queue, final Function<Object, Void> consumer) {
        final List<Function<Object, Void>> consumers = queueStream.getOrDefault(queue, new ArrayList<>());
        if (consumers != null) {
            consumers.add(consumer);
        }

        queueStream.put(queue, consumers);
    }

}
