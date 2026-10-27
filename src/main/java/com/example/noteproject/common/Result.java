package com.example.noteproject.common;


public class Result<T> {

    private int code;


    private String message;

    /*真正的业务数据，泛型 T 表示这里什么都可能放 */
    private T data;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /*成功时调用：Result.success(数据) */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    /*失败时调用：Result.error("错误原因") */
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
