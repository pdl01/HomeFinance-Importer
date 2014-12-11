
package com.hfw.homefinance.importer;

import java.util.List;

/**
 *
 * @author pldorrell
 */
public interface TransactionDataImporter {
    
    public List<FileAccount> loadFromData(String _data);
        public List<FileAccount> loadFromLinedData(List<String> _data);

    public List<FileAccount> loadFromFile(String _filename);
}
