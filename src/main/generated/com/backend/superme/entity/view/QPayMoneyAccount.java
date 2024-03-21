package com.backend.superme.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayMoneyAccount is a Querydsl query type for PayMoneyAccount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayMoneyAccount extends EntityPathBase<PayMoneyAccount> {

    private static final long serialVersionUID = -1583038091L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayMoneyAccount payMoneyAccount = new QPayMoneyAccount("payMoneyAccount");

    public final StringPath active = createString("active");

    public final NumberPath<java.math.BigDecimal> balance = createNumber("balance", java.math.BigDecimal.class);

    public final StringPath bank = createString("bank");

    public final StringPath cardNumber = createString("cardNumber");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath cvv = createString("cvv");

    public final DateTimePath<String> expiryDate = createDateTime("expiryDate", String.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.superme.entity.user.QUserEntity user;

    public QPayMoneyAccount(String variable) {
        this(PayMoneyAccount.class, forVariable(variable), INITS);
    }

    public QPayMoneyAccount(Path<? extends PayMoneyAccount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayMoneyAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayMoneyAccount(PathMetadata metadata, PathInits inits) {
        this(PayMoneyAccount.class, metadata, inits);
    }

    public QPayMoneyAccount(Class<? extends PayMoneyAccount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.backend.superme.entity.user.QUserEntity(forProperty("user")) : null;
    }

}

