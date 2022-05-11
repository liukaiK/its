package cn.com.goodlan.its.core.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 接口返回封装类
 *
 * @author liukai
 */
@Getter
@Setter
public class Result<T> {

    private static final Integer SUCCESSFUL_CODE = 0;

    private static final String SUCCESSFUL_MESSAGE = "成功";

    /**
     * 处理结果code
     */
    private final Integer code;

    /**
     * 处理结果描述信息
     */
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * 时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time;

    /**
     * 内部使用，用于构造成功的结果
     */
    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = LocalDateTime.now();
    }

    /**
     * 内部使用，用于构造成功的结果
     */
    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    /**
     * 快速创建成功结果并返回结果数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESSAGE, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<T>(code, message);
    }

}
