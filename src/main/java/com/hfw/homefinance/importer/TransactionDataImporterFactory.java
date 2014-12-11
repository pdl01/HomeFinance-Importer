/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfw.homefinance.importer;

import java.io.File;

/**
 *
 * @author pldorrell
 */
public class TransactionDataImporterFactory {

    public static TransactionDataImporter get(String type) {
        if (type == "QFX") {
            return new QFXImporter();
        } else if (type == "QIF") {
            return new QIFImporter();
        } else {
            return null;
        }
    }

    public static TransactionDataImporter get(File file) {
        if (file.getName().endsWith(".qfx") || file.getName().endsWith(".QFX")) {
            return TransactionDataImporterFactory.get("QFX");
        } else if (file.getName().endsWith(".qif") || file.getName().endsWith(".qif")) {
            return TransactionDataImporterFactory.get("QIF");
        } else {
            return null;
        }
    }
}
