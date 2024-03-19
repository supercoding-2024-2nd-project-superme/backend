package com.backend.superme.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -290797752L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final EnumPath<com.backend.superme.constant.user.GenderEnum> gender = createEnum("gender", com.backend.superme.constant.user.GenderEnum.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kakaoLogin = createString("kakaoLogin");

    public final StringPath naverLogin = createString("naverLogin");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath profile = createString("profile");

    public final StringPath role = createString("role");

    public final DatePath<java.sql.Date> signupDate = createDate("signupDate", java.sql.Date.class);

    public final EnumPath<com.backend.superme.constant.user.StatusEnum> status = createEnum("status", com.backend.superme.constant.user.StatusEnum.class);

    public final StringPath username = createString("username");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

