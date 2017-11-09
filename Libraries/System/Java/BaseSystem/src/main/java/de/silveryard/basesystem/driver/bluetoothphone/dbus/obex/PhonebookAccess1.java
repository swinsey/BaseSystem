package de.silveryard.basesystem.driver.bluetoothphone.dbus.obex;

import org.freedesktop.dbus.*;

import java.util.Map;

/**
 * Created based on documentation: https://github.com/r10r/bluez/blob/master/doc/obex-api.txt
 * Created by silveryard on 28.05.17.
 */
@DBusInterfaceName("org.bluez.obex.PhonebookAccess1")
public interface PhonebookAccess1 extends DBusInterface {
    class TwoTouple<T1, T2> extends Tuple {
        public final T1 t1;
        public final T2 t2;

        public TwoTouple(T1 t1, T2 t2){
            this.t1 = t1;
            this.t2 = t2;
        }
    }

    /**
     * Select the phonebook object for other operations. Should
     * be call before all the other operations.
     *
     * location : Where the phonebook is stored, possible
     * inputs :
     * "int" ( "internal" which is default )
     * "sim" ( "sim1" )
     * "sim2"
     * ...
     *
     * phonebook : Possible inputs :
     * "pb" :	phonebook for the saved contacts
     * "ich":	incoming call history
     * "och":	outgoing call history
     * "mch":	missing call history
     * "cch":	combination of ich och mch
     *
     * Possible errors: org.bluez.obex.Error.InvalidArguments
     * org.bluez.obex.Error.Failed
     *
     * @param location
     * @param phonebook
     */
    void Select(String location, String phonebook);

    /**
     * Return the entire phonebook object from the PSE server
     * in plain string with vcard format, and store it in
     * a local file.
     *
     * If an empty target file is given, a name will be
     * automatically calculated for the temporary file.
     *
     * The returned path represents the newly created transfer,
     * which should be used to find out if the content has been
     * successfully transferred or if the operation fails.
     *
     * The properties of this transfer are also returned along
     * with the object path, to avoid a call to GetProperties.
     *
     * Possible filters: Format, Order, Offset, MaxCount and
     * Fields
     *
     * Possible errors: org.bluez.obex.Error.InvalidArguments
     * org.bluez.obex.Forbidden
     *
     * @param targetFile
     * @param filters
     * @return
     */
    TwoTouple<Path, Map<String, Variant<?>>> PullAll(String targetFile, Map<String, Variant<?>> filters);
}
