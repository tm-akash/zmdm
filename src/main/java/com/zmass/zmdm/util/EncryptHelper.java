/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zmass.zmdm.util;

/**
 *
 * @author ZMF
 */



import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;


public class EncryptHelper {


    byte[] skey = {0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11
            , 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11, 0x11
    };

    public byte[] encryptData(byte[] data) throws InvalidCipherTextException {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new AESEngine(), new PKCS7Padding());
        cipher.init(true, new KeyParameter(skey));
        int outputSize = cipher.getOutputSize(data.length);
        byte[] tempOP = new byte[outputSize];
        int processLen = cipher.processBytes(data, 0, data.length, tempOP, 0);
        int outputLen = cipher.doFinal(tempOP, processLen);
        byte[] result = new byte[processLen + outputLen];
        System.arraycopy(tempOP, 0, result, 0, result.length);
        return result;

    }


    public byte[] decryptData(byte[] data) throws InvalidCipherTextException {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new AESEngine(), new PKCS7Padding());
        cipher.init(false, new KeyParameter(skey));
        int outputSize = cipher.getOutputSize(data.length);
        //System.out.println("outputSize " + outputSize);
        byte[] tempOP = new byte[outputSize];
        int processLen = cipher.processBytes(data, 0, data.length, tempOP, 0);
        int outputLen = cipher.doFinal(tempOP, processLen);
        byte[] result = new byte[processLen + outputLen];
        System.arraycopy(tempOP, 0, result, 0, result.length);
        return result;

    }


    public static void main(String[] args) throws Exception {

        String data = "210000|1234567890|32436546578679|000000001000";
        //Processing code(DE3)|Terminal id(DE41)|Merchant AC(DE 103)|Amount(DE4)|DE2
        EncryptHelper helper = new EncryptHelper();
        byte[] enc = helper.encryptData(data.getBytes());

        System.out.println(Base64Coder.encodeBuftoString(enc));


        System.out.println(new String(helper.decryptData(enc)));

    }
}
