package com.demo.sm;

import com.demo.sm.core.SM2Utils;
import com.demo.sm.core.SM3Util;
import com.demo.sm.core.SM4Utils;
import com.demo.sm.util.util;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class smTest {
    @Test
    void sm2Test() throws IOException {
        //生成密钥对
        SM2Utils.generateKeyPair();
        String plainText = "3604231995013547588";
        byte[] sourceData = plainText.getBytes();
        //下面的秘钥可以使用generateKeyPair()生成的秘钥内容
        // 国密规范正式私钥
        String prik = "3690655E33D5EA3D9A4AE1A1ADD766FDEA045CDEAA43A9206FB8C430CEFE0D94";
        // 国密规范正式公钥
        String pubk = "04F6E0C3345AE42B51E06BF50B98834988D54EBC7460FE135A48171BC0629EAE205EEDE253A530608178A98F1E19BB737302813BA39ED3FA3C51639D7A20C7391A";

        System.out.println("加密: ");
        String cipherText = SM2Utils.encrypt(util.hexToByte(pubk), sourceData);
        System.out.println(cipherText);
        System.out.println("解密: ");
        plainText = new String(SM2Utils.decrypt(util.hexToByte(prik), util.hexToByte(cipherText)));
        System.out.println(plainText);
    }

    @Test
    void sm3Test(){
        byte[] md = new byte[32];
        byte[] msg1 = "jsxexxa".getBytes();
        SM3Util sm3 = new SM3Util();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        System.out.println(s.toUpperCase());
    }

    @Test
    void sm4Test(){
        String plainText = "360123499543197845";

        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = "JeF8U9wHFOMfs2Y8";
        sm4.hexString = false;

        System.out.println("ECB模式");
        String cipherText = sm4.encryptData_ECB(plainText);
        System.out.println("密文: " + cipherText);

        plainText = sm4.decryptData_ECB(cipherText);
        System.out.println("明文: " + plainText);

        System.out.println("CBC模式");
        sm4.iv = "UISwD9fW6cFh9SNS";
        cipherText = sm4.encryptData_CBC(plainText);
        System.out.println("密文: " + cipherText);
        System.out.println("");

        plainText = sm4.decryptData_CBC(cipherText);
        System.out.println("明文: " + plainText);
    }
}
