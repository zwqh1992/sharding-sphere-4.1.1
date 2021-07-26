package cn.zwqh.shardingspheredemo8.encryptor;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shardingsphere.encrypt.strategy.spi.QueryAssistedEncryptor;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * @description: Sha256RandomEncryptor
 * @author: wangc
 * @create: 2021-07-26 15:00
 **/
@Getter
@Setter
public class Sha256RandomEncryptor implements QueryAssistedEncryptor {

    private Properties properties = new Properties();

    @Override
    public String queryAssistedEncrypt(String plaintext) {
        if (null == plaintext) {
            return null;
        }
        // 原始字符串
        return DigestUtils.sha256Hex(String.valueOf(plaintext));
    }

    @Override
    public void init() {

    }

    @Override
    public String encrypt(Object plaintext) {
        if (null == plaintext) {
            return null;
        }
        // 原始字符串+变动因子（如时间戳）
        plaintext = plaintext.toString() + LocalDateTime.now().toString();
        return DigestUtils.sha256Hex(String.valueOf(plaintext));
    }

    @Override
    public Object decrypt(String ciphertext) {
        return ciphertext;
    }

    @Override
    public String getType() {
        return "SHA256_RANDOM";
    }
}
