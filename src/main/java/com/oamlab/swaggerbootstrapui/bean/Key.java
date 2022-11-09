package com.oamlab.swaggerbootstrapui.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value="key对象",description="秘钥key")
public class Key {
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("秘钥id")
    private String keyId;

    @ApiModelProperty("秘钥")
    private String key;

    @ApiModelProperty("密文")
    private String cipherText;

    @ApiModelProperty("明文")
    private String plainText;
}