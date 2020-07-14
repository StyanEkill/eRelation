package model;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Criptografia {

    public String encrypt(String toEncrypy) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey deskey = keyGenerator.generateKey();

        //instancia de cipher
        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        //iniciando a criptografia
        desCipher.init(Cipher.ENCRYPT_MODE, deskey);

        //encriptgrafando a mensagem
        byte[] encryptedMessage = desCipher.doFinal(toEncrypy.getBytes());
        System.out.println(new String(encryptedMessage));
        //codificando
        String encode64 = Base64.getEncoder().encodeToString(encryptedMessage);

        return encode64;

    }

    public String decrypt(byte[] toDecrypt) throws Exception {


        //decodificando
        byte[] dec64 = Base64.getDecoder().decode(toDecrypt);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey deskey = keyGenerator.generateKey();

        //instancia de cipher
        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");


        //iniciando a descriptografia
        desCipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] desencryptMessage = desCipher.doFinal(dec64);

        return new String( desencryptMessage);


    }



}