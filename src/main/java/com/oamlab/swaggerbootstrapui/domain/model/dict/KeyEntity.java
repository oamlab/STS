package com.oamlab.swaggerbootstrapui.domain.model.dict;

public class KeyEntity {


    private Integer  id;
    private String  keyId;
    private String  cryptKey;

    // 密文
    private String  cipherText;

    // 明文
    private String  plainText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }



    public String getCryptKey() {
        return cryptKey;
    }

    public void setCryptKey(String cryptKey) {
        this.cryptKey = cryptKey;
    }


    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }



    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }


    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", keyId='" + keyId + '\'' +
                ", cryptkey='" + cryptKey + '\'' +
                ", cipherText='" + cipherText + '\'' +
                ", plainText='" + plainText + '\'' +
                '}';
    }

}
