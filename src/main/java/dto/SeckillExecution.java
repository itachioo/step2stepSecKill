package dto;

import entity.SuccessKilled;
import enums.SeckillStateEnum;

public class SeckillExecution {
    private long seckillId;
    private int state; //秒杀结果状态
    private String stateInfo; //状态的明文标识
    private SuccessKilled successKilled; //成功时，返回秒杀成功对象

    /**
     * 秒杀成功
     * @param seckillId
     * @param stateEnum
     * @param successKilled
     */
    public SeckillExecution(long seckillId,  SeckillStateEnum stateEnum,  SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getInfo();
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败
     * @param seckillId
     * @param state
     * @param stateInfo
     */
    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }


    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
