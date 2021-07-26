package cn.zwqh.shardingspheredemo8.encryptor;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shardingsphere.encrypt.strategy.spi.Encryptor;

import java.util.Properties;

/**
 * @description: Sha256Encryptor
 * @author: wangc
 * @create: 2021-07-26 14:50
 **/
@Getter
@Setter
public class Sha256Encryptor implements Encryptor {

    private Properties properties = new Properties();

    @Override
    public void init() {

    }

    @Override
    public String encrypt(Object plaintext) {
        if (null == plaintext) {
            return null;
        }
        return DigestUtils.sha256Hex(String.valueOf(plaintext));
    }

    @Override
    public Object decrypt(String ciphertext) {
        return ciphertext;
    }

    @Override
    public String getType() {
        return "SHA256";
    }
}
