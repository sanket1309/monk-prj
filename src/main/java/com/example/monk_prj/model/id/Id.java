package com.example.monk_prj.model.id;

import com.example.monk_prj.enums.IdPrefix;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Data
public abstract class Id {
    public static int SUFFIX_LENGTH = 20;
    public static int LENGTH = 24;
    protected String id;
    abstract IdPrefix getIdPrefix();

    Id(){
        assignId();
    }
    public String assignId(){
        id = generateWholeId();
        return id;
    }
    public String generateWholeId(){
        return getIdPrefix().getPrefix() + "-" + generateId();
    }

    private String generateId(){
        return UUID.randomUUID().toString().substring(0, SUFFIX_LENGTH);
    }
    public static boolean equals(Id id1, Id id2){
        if(id1 == null || id2 == null) return false;
        return StringUtils.equals(id1.getId(), id2.getId());
    }
    public static void validateId(Id id){
        if(Objects.isNull(id)){
            log.error("id object is null");
            throw new RuntimeException();
        }
        if(StringUtils.isBlank(id.getId())){
            log.error("id is blank");
            throw new RuntimeException();
        }

        if(id.getId().length() != LENGTH){
            log.error("id length is incorrect");
            throw new RuntimeException();
        }
    }
}
