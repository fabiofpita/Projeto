package criptografia;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public String gerarMD5(String info) {
        return stringHexa(gerarHash(info, "MD5"));
    }

    public MD5() {
    }

    private static String stringHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < bytes.length; ++i) {
            int parteAlta = (bytes[i] >> 4 & 15) << 4;
            int parteBaixa = bytes[i] & 15;
            if (parteAlta == 0) {
                s.append('0');
            }

            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }

        return s.toString();
    }

    private static byte[] gerarHash(String frase, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            md.update(frase.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException var3) {
            return null;
        }
    }
}