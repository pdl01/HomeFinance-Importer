package com.hfw.homefinance.importer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pldorrell
 */
public class QFXImporter implements TransactionDataImporter {
    static SimpleDateFormat input_format = new SimpleDateFormat("yyyyMMddHHmmss");
    static String STMTTRN_BEGIN = "<STMTTRN>";
        static String STMTTRN_END = "</STMTTRN>";
    static String TAG_TRNTYPE = "<TRNTYPE>";
    static String TAG_NAME = "<NAME>";
    static String TAG_DTPOSTED = "<DTPOSTED>";
    static String TAG_AMOUNT = "<TRNAMT>";
    static String TAG_CHECKNUM = "<CHECKNUM>";
    static String TAG_FTID = "<FITID>";

    @Override
    public List<FileAccount> loadFromLinedData(List<String> _data) {
        
        List<FileAccount> accounts = new ArrayList<FileAccount>();
        FileAccount account = new FileAccount();
        
        List<FileTransaction> txns = new ArrayList<FileTransaction>();
        FileTransaction txn = null;
        boolean inTxn = false;

        for (String line : _data) {
            System.out.println(line);
            if (line.equals(STMTTRN_BEGIN)) {
                inTxn = true;
                txn = new FileTransaction();
            } else if (inTxn && line.startsWith(TAG_NAME)) {
                txn.setPayee(line.substring(TAG_NAME.length()));
            } else if (inTxn && line.startsWith(TAG_TRNTYPE)) {
                txn.setType(line.substring(TAG_TRNTYPE.length()));
            } else if (inTxn && line.startsWith(TAG_FTID)) {
                txn.setTxnNumber(line.substring(TAG_FTID.length()));
            } else if (inTxn && line.startsWith(TAG_DTPOSTED)) {
                String dateInFile = line.substring(TAG_DTPOSTED.length());
                try {
                    txn.setTxnDate(input_format.parse(dateInFile));
                } catch (ParseException ex) {
                    txn.setTxnDate(new Date());    
                }
            } else if (inTxn && line.startsWith(TAG_AMOUNT)) {
                txn.setAmount(Double.parseDouble(line.substring(TAG_AMOUNT.length())));
            } else if (inTxn && line.startsWith(TAG_CHECKNUM)) {
                txn.setCheckNumber(line.substring(TAG_CHECKNUM.length()));
            } else if (inTxn && line.startsWith(STMTTRN_END)) {
                txns.add(txn);
                inTxn = false;
            }

        }
        account.setTransactions(txns);
        accounts.add(account);
        return accounts;
    }

    @Override
    public List<FileAccount> loadFromFile(String _filename) {
        File file = new File(_filename);
        if (file.canRead()) {

            byte[] encoded;
            try {
                //encoded = Files.readAllBytes(Paths.get(file.getPath()));
                List<String> lines = Files.readAllLines(Paths.get(file.getPath()), Charset.defaultCharset());

                return this.loadFromLinedData(lines);
            } catch (IOException ex) {
                return null;
            }
            //String x = new String(encoded, Charset.defaultCharset());

        }
        return null;
    }

    @Override
    public List<FileAccount> loadFromData(String _data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
