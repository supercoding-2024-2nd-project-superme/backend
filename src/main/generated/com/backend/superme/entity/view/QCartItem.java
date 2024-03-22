package com.backend.superme.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartItem is a Querydsl query type for CartItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartItem extends EntityPathBase<CartItem> {

    private static final long serialVersionUID = -1547593997L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartItem cartItem = new QCartItem("cartItem");

    public final DateTimePath<java.util.Date> addedAt = createDateTime("addedAt", java.util.Date.class);

    public final com.backend.superme.entity.ItemImgEntity.QAdminItemImageEntity adminItemImage;

    public final QCart cart;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public final StringPath ItemName = createString("ItemName");

    public final StringPath orderedColor = createString("orderedColor");

    public final NumberPath<Integer> orderedQty = createNumber("orderedQty", Integer.class);

    public final StringPath orderedSize = createString("orderedSize");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final com.backend.superme.entity.user.QUserEntity user;

    public QCartItem(String variable) {
        this(CartItem.class, forVariable(variable), INITS);
    }

    public QCartItem(Path<? extends CartItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartItem(PathMetadata metadata, PathInits inits) {
        this(CartItem.class, metadata, inits);
    }

    public QCartItem(Class<? extends CartItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.adminItemImage = inits.isInitialized("adminItemImage") ? new com.backend.superme.entity.ItemImgEntity.QAdminItemImageEntity(forProperty("adminItemImage"), inits.get("adminItemImage")) : null;
        this.cart = inits.isInitialized("cart") ? new QCart(forProperty("cart"), inits.get("cart")) : null;
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.superme.entity.user.QUserEntity(forProperty("user")) : null;
    }

}

