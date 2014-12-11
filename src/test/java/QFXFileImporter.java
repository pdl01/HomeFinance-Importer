/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hfw.homefinance.importer.FileAccount;
import com.hfw.homefinance.importer.FileTransaction;
import com.hfw.homefinance.importer.TransactionDataImporter;
import com.hfw.homefinance.importer.TransactionDataImporterFactory;
import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pldorrell
 */
public class QFXFileImporter {
    
    public QFXFileImporter() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void go1() {
        TransactionDataImporter tdi = TransactionDataImporterFactory.get(new File("/home/pldorrell/Downloads/stmt.qfx"));
        List<FileAccount> txns = tdi.loadFromFile("/home/pldorrell/Downloads/stmt.qfx");
        for (FileTransaction f: txns.get(0).getTransactions()) {
            System.out.println(f.getPayee() + ":" + f.getAmount());
        }
    }
}
