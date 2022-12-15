package de.pauhull.minigame_api.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Paul
 * on 03.03.2019
 *
 * @author pauhull
 */
public class EventCanceller {

    public static void cancelEvent(Class<? extends Event> eventClass, JavaPlugin plugin, Condition condition) {
        Listener emptyListener = new Listener() {};
        EventExecutor eventExecutor = (listener, event) -> {
            if(event instanceof Cancellable) {
                ((Cancellable) event).setCancelled(condition.isCancelled(event));
            }
        };
        Bukkit.getPluginManager().registerEvent(eventClass, emptyListener, EventPriority.NORMAL, eventExecutor, plugin);
    }

    public static void cancelEvent(Class<? extends Event> eventClass, JavaPlugin plugin) {
        cancelEvent(eventClass, plugin, event -> true);
    }

    public interface Condition {
        boolean isCancelled(Event event);
    }

}
