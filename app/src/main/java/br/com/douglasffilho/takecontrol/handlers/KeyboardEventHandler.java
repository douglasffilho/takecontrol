package br.com.douglasffilho.takecontrol.handlers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import br.com.douglasffilho.takecontrol.queues.QueueManager;
import br.com.douglasffilho.takecontrol.queues.Queues;
import br.com.douglasffilho.takecontrol.services.AccessibilityHandleService;

public class KeyboardEventHandler {
    private static AccessibilityHandleService accessibilityHandleService;

    private static Map<String, Function<Void, Void>> movements;

    static {
        movements = new ConcurrentHashMap<>();
        movements.put("UP", (empty) -> {
            accessibilityHandleService.moveUp();
            return null;
        });
        movements.put("DOWN", (empty) -> {
            accessibilityHandleService.moveDown();
            return null;
        });
        movements.put("LEFT", (empty) -> {
            accessibilityHandleService.moveLeft();
            return null;
        });
        movements.put("RIGHT", (empty) -> {
            accessibilityHandleService.moveRight();
            return null;
        });
        movements.put("JUMP", (empty) -> {
            accessibilityHandleService.jump();
            return null;
        });
        movements.put("GRANADE", (empty) -> {
            accessibilityHandleService.granade();
            return null;
        });
        movements.put("AIM", (empty) -> {
            accessibilityHandleService.aim();
            return null;
        });
        movements.put("SQUAT", (empty) -> {
            accessibilityHandleService.squat();
            return null;
        });
    }

    private static void reproduceEvent(final String event) {
        movements.getOrDefault(event, (empty) -> null).apply(null);
    }

    public static void registerListener() {
        QueueManager.subscribe(Queues.KEYBOARD_EVENT_LISTENER, (message) -> {
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
