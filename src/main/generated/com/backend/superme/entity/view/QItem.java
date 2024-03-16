package com.backend.superme.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -1096054861L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final QCategory category;

    public final StringPath colorOption = createString("colorOption");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemName = createString("itemName");

    public final QItemStock itemStock;

    public final StringPath mainImg = createString("mainImg");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final DateTimePath<java.time.LocalDateTime> registrationDate = createDateTime("registrationDate", java.time.LocalDateTime.class);

    public final com.backend.superme.entity.user.QUserEntity seller;

    public final StringPath sizeOption = createString("sizeOption");

    public final EnumPath<com.backend.superme.constant.item.StockStatus> stockStatus = createEnum("stockStatus", com.backend.superme.constant.item.StockStatus.class);

    public final DateTimePath<java.time.LocalDateTime> terminationDate = createDateTime("terminationDate", java.time.LocalDateTime.class);

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.itemStock = inits.isInitialized("itemStock") ? new QItemStock(forProperty("itemStock"), inits.get("itemStock")) : null;
        this.seller = inits.isInitialized("seller") ? new com.backend.superme.entity.user.QUserEntity(forProperty("seller")) : null;
    }

}
