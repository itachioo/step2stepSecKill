package entity;
import java.util.Date;

public class SuccessKilled {
    private Byte state;
    private Date createTime;
    private Long seckillId;
    private Long userPhone;
    //????
    private Seckill seckill;


    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SucessKilled{" +
                "state=" + state +
                ", createTime=" + createTime +
                ", seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", seckill=" + seckill +
                '}';
    }
}
