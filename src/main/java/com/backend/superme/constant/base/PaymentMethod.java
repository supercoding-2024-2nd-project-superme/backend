package com.backend.superme.constant.base;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum PaymentMethod {
    PAYPAL, // 페이팔(1회성)
    PAYMONEY // 페아머니
}
