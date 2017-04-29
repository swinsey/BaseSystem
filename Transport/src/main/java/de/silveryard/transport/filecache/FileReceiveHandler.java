package de.silveryard.transport.filecache;

import de.silveryard.transport.Parameter;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by CHofm on 14.01.2017.
 */
@FunctionalInterface
public interface FileReceiveHandler {
    /**
     * Called when a file is received
     * @param sourceID Sender GUID
     * @param uuid File GUID
     * @param commandHash Command Hash
     * @param params List of parameters
     * @param path Path to the file
     */
    void handle(String sourceID, String uuid, String commandHash, List<Parameter> params, Path path);
}
