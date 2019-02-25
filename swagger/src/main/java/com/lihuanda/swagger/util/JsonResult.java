package com.lihuanda.swagger.util;
import com.baomidou.mybatisplus.plugins.Page;
import java.io.Serializable;

public class  JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Long PAGESIZR = 10L;
    private Object data;
    private String message;
    private int code = 200;
    private Long pageNum = null;
    private Long total = null;
    private Long pageSize;

    protected JsonResult() {
        this.pageSize = PAGESIZR;
        this.code = ResultCode.SUCCESS.getValue();
    }

    protected  JsonResult(ResultCode code) {
        this.pageSize = PAGESIZR;
        this.code = code.getValue();
    }

    protected  JsonResult(T data) {
        this.pageSize = PAGESIZR;
        this.code = ResultCode.SUCCESS.getValue();
        this.data = data;
        if (data instanceof Page) {
            Page page = (Page)data;
            this.total = page.getTotal();
            this.pageSize = (long)page.getSize();
            this.pageNum = (long)page.getCurrent();
            this.data = page.getRecords();
        }

    }

    protected  JsonResult(ResultCode code, T data) {
        this.pageSize = PAGESIZR;
        this.code = code.getValue();
        this.data = data;
        if (data instanceof Page) {
            Page page = (Page)data;
            this.total = page.getTotal();
            this.pageSize = (long)page.getSize();
            this.pageNum = (long)page.getCurrent();
            this.data = page.getRecords();
        }

    }

    protected  JsonResult(Throwable e) {
        this.pageSize = PAGESIZR;
        this.code = ResultCode.EXPECTATION_ERROR.getValue();
        this.message = e.getMessage();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
        if (data instanceof Page) {
            Page page = (Page)data;
            this.total = page.getTotal();
            this.pageSize = (long)page.getSize();
            this.pageNum = (long)page.getCurrent();
            this.data = page.getRecords();
        }

    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public static  JsonResult success() {
         JsonResult result = new JsonResult(ResultCode.SUCCESS);
        return result;
    }

    public static <T>  JsonResult<T> success(T data) {
         JsonResult<T> result = new JsonResult(ResultCode.SUCCESS, data);
        return result;
    }

    public static <T>  JsonResult<T> success(T data, long total) {
         JsonResult<T> result = new JsonResult(ResultCode.SUCCESS, data);
        if (total > 0L) {
            result.total = total;
        }

        result.pageNum = 1L;
        return result;
    }

    public static <T>  JsonResult<T> success(T data, long page, long total) {
         JsonResult<T> result = new JsonResult(ResultCode.SUCCESS, data);
        if (total > 0L) {
            result.total = total;
        }

        if (page > -1L) {
            result.pageNum = page;
        }

        return result;
    }

    public static  JsonResult<?> failure() {
         JsonResult result = new JsonResult(ResultCode.EXPECTATION_FAILED);
        return result;
    }

    public static  JsonResult failure(String message) {
         JsonResult result = new JsonResult(ResultCode.EXPECTATION_FAILED);
        result.setMessage(message);
        return result;
    }

    public static  JsonResult failure(int code, String message) {
         JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static  JsonResult error() {
         JsonResult result = new JsonResult(ResultCode.EXPECTATION_ERROR);
        return result;
    }

    public static  JsonResult error(String message) {
         JsonResult result = new JsonResult(ResultCode.EXPECTATION_ERROR);
        result.setMessage(message);
        return result;
    }

    public static  JsonResult error(int code, String message) {
         JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static  JsonResult error(Exception e) {
         JsonResult result = new JsonResult(e);
        return result;
    }

    public static  JsonResult forbidden(String message) {
         JsonResult result = new JsonResult(ResultCode.FORBIDDEN);
        result.setMessage(message);
        return result;
    }

    public static  JsonResult noLogin() {
         JsonResult result = new JsonResult(ResultCode.UNAUTHORIZED);
        return result;
    }

    public static  JsonResult unAuthorized() {
         JsonResult result = new JsonResult(ResultCode.UNAUTHORIZED);
        return result;
    }
}
