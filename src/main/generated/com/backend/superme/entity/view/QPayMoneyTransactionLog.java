package com.backend.superme.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayMoneyTransactionLog is a Querydsl query type for PayMoneyTransactionLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayMoneyTransactionLog extends EntityPathBase<PayMoneyTransactionLog> {

    private static final long serialVersionUID = -1354527938L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayMoneyTransactionLog payMoneyTransactionLog = new QPayMoneyTransactionLog("payMoneyTransactionLog");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> processTime = createDateTime("processTime", java.util.Date.class);

    public final com.backend.superme.entity.user.QUserEntity receiver;

    public final QPayMoneyAccount receiverPayMoneyAccount;

    public final com.backend.superme.entity.user.QUserEntity sender;

    public final QPayMoneyAccount senderPayMoneyAccount;

    public final DateTimePath<java.util.Date> sendTime = createDateTime("sendTime", java.util.Date.class);

    public QPayMoneyTransactionLog(String variable) {
        this(PayMoneyTransactionLog.class, forVariable(variable), INITS);
    }

    public QPayMoneyTransactionLog(Path<? extends PayMoneyTransactionLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayMoneyTransactionLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayMoneyTransactionLog(PathMetadata metadata, PathInits inits) {
        this(PayMoneyTransactionLog.class, metadata, inits);
    }

    public QPayMoneyTransactionLog(Class<? extends PayMoneyTransactionLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.receiver = inits.isInitialized("receiver") ? new com.backend.superme.entity.user.QUserEntity(forProperty("receiver")) : null;
        this.receiverPayMoneyAccount = inits.isInitialized("receiverPayMoneyAccount") ? new QPayMoneyAccount(forProperty("receiverPayMoneyAccount"), inits.get("receiverPayMoneyAccount")) : null;
        this.sender = inits.isInitialized("sender") ? new com.backend.superme.entity.user.QUserEntity(forProperty("sender")) : null;
        this.senderPayMoneyAccount = inits.isInitialized("senderPayMoneyAccount") ? new QPayMoneyAccount(forProperty("senderPayMoneyAccount"), inits.get("senderPayMoneyAccount")) : null;
    }

}

