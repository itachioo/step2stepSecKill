package exception;

/**
 * 重复秒杀异常， 运行期异常
 * mysql只支持运行期异常的回滚操作？？
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }
    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

}
