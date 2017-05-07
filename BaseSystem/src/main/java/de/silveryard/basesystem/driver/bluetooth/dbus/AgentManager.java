package de.silveryard.basesystem.driver.bluetooth.dbus;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;
import org.freedesktop.dbus.Path;

/**
 * Created based on documentation: https://github.com/hmallat/bluez5-5.18/blob/master/doc/agent-api.txt
 * Created by silveryard on 02.05.17.
 */
@DBusInterfaceName("org.bluez.AgentManager1")
public interface AgentManager extends DBusInterface {
    /**
     * This registers an agent handler.
     *
     * The object path defines the path of the agent
     * that will be called when user input is needed.
     *
     * Every application can register its own agent and
     * for all actions triggered by that application its
     * agent is used.
     *
     * It is not required by an application to register
     * an agent. If an application does chooses to not
     * register an agent, the default agent is used. This
     * is on most cases a good idea. Only application
     * like a pairing wizard should register their own
     * agent.
     *
     * An application can only register one agent. Multiple
     * agents per application is not supported.
     *
     * The capability parameter can have the values
     * "DisplayOnly", "DisplayYesNo", "KeyboardOnly",
     * "NoInputNoOutput" and "KeyboardDisplay" which
     * reflects the input and output capabilities of the
     * agent.
     *
     * If an empty string is used it will fallback to
     * "DisplayYesNo".
     *
     * Possible errors: org.bluez.Error.InvalidArguments
     * org.bluez.Error.AlreadyExists
     *
     * @param agent
     * @param capability
     */
    void RegisterAgent(Path agent, String capability);

    /**
     * This unregisters the agent that has been previously
     * registered. The object path parameter must match the
     * same value that has been used on registration.
     *
     * Possible errors: org.bluez.Error.DoesNotExist
     *
     * @param agent
     */
    void UnregisterAgent(Path agent);

    /**
     * This requests is to make the application agent
     * the default agent. The application is required
     * to register an agent.
     *
     * Special permission might be required to become
     * the default agent.
     *
     * Possible errors: org.bluez.Error.DoesNotExist
     *
     * @param agent
     */
    void RequestDefaultAgent(Path agent);
}
