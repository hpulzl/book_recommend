package com.lzl.exception;

/**
 * @Author: li_zhilei
 * @Date: create in 14:53 17/5/31.
 * @description:
 */
public class RecommendException extends RuntimeException{
    private Integer	code;

    /**
     *
     */
    private static final long serialVersionUID = -5429277883934622666L;

    public RecommendException() {
    }

    public RecommendException(String message) {
        super(message);
    }

    public RecommendException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public RecommendException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecommendException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
