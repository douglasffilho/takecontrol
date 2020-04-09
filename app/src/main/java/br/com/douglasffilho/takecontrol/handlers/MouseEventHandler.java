package br.com.douglasffilho.takecontrol.handlers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import br.com.douglasffilho.takecontrol.queues.QueueManager;
import br.com.douglasffilho.takecontrol.queues.Queues;
import br.com.douglasffilho.takecontrol.services.AccessibilityHandleService;

public class MouseEventHandler {
    private static AccessibilityHandleService accessibilityHandleService;

    private static Map<String, Function<Void, Void>> actions;

    static {
        actions = new ConcurrentHashMap<>();
        actions.put("SHOT", (empty) -> {
            accessibilityHandleService.shot();
            return null;
        });
    }

    private static void reproduceEvent(final String event) {
        actions.getOrDefault(event, (empty) -> null).apply(null);

        final String[] coordinates = event.split(":");

        if (coordinates.length == 2) {
            final float xPercent = Float.parseFloat(coordinates[0]);
            final float yPercent = Float.parseFloat(coordinates[1]);

            accessibilityHandleService.moveTarget(xPercent, yPercent);
        }
    }

    public static void registerListener() {
        QueueManager.subscribe(Queues.MOUSE_EVENT_LISTENER, (message) -> {
            final String event = (String) message;

            reproduceEvent(event);

            return null;
        });
    }

    public static void registerAccessibilityService(
            final AccessibilityHandleService accessibilityService
    ) {
        accessibilityHandleService = accessibilityService;
    }

}
