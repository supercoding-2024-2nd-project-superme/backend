package com.backend.superme.config.global;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

//    public BusinessException(ErrorCode errorCode) {
//    super(errorCode);
//        this.errorCode = errorCode;
//    }

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        // todo: 에러 코드 정의 필요
        return errorCode;
    }
    public static class InsufficientStockException extends BusinessException {
        public InsufficientStockException() {
            super(ErrorCode.INSUFFICIENT_STOCK);
        }

        public InsufficientStockException(String message) {
            super(ErrorCode.INSUFFICIENT_STOCK, message);
        }

        public InsufficientStockException(String message, Throwable cause) {
            super(ErrorCode.INSUFFICIENT_STOCK, message, cause);
        }
    }

    public static class InsufficientBalanceException extends BusinessException {
        public InsufficientBalanceException() {
            super(ErrorCode.INSUFFICIENT_BALANCE);
        }

        public InsufficientBalanceException(String message) {
            super(ErrorCode.INSUFFICIENT_BALANCE, message);
        }

        public InsufficientBalanceException(String message, Throwable cause) {
            super(ErrorCode.INSUFFICIENT_BALANCE, message, cause);
        }
    }

    public static class UserNotFoundException extends BusinessException {
        public UserNotFoundException() {
            super(ErrorCode.USER_NOT_FOUND);
        }

        public UserNotFoundException(String message) {
            super(ErrorCode.USER_NOT_FOUND, message);
        }

        public UserNotFoundException(String message, Throwable cause) {
            super(ErrorCode.USER_NOT_FOUND, message, cause);
        }
    }

    public static class ItemNotFoundException extends BusinessException {
        public ItemNotFoundException() {
            super(ErrorCode.ITEM_NOT_FOUND);
        }

        public ItemNotFoundException(String message) {
            super(ErrorCode.ITEM_NOT_FOUND, message);
        }

        public ItemNotFoundException(String message, Throwable cause) {
            super(ErrorCode.ITEM_NOT_FOUND, message, cause);
        }
    }



}

