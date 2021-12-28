package com.cloud.elasticsearch.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author Administrator
 * @description
 */
@Document(indexName = "sys_user", type = "user")
@Data
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -595647652352120839L;

    @Id
    private String id;

    private String userName;

    private String password;

    private Integer sex;

}
