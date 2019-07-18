package service.impl;

import dao.SeckillDao;
import dao.SuccessKilledDao;
import dto.Exposer;
import dto.SeckillExecution;
import entity.Seckill;
import entity.SuccessKilled;
import enums.SeckillStateEnum;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import service.SeckillService;

import java.util.Date;
import java.util.List;


@Service
public class SeckillServiceImpl implements SeckillService {
    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //加混淆字符串
    private final String salt = "fdsfklf23293654**&%%$^^&&^%&*()^^&";
    @Autowired   //注入service依赖
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill==null){
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date(); //系统当前时间
        if(startTime.getTime()>nowTime.getTime() || endTime.getTime()<nowTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if(md5==null||!md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite"); //秒杀数据被重写
        }
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId,nowTime);
            if(updateCount<=0) {
                throw new SeckillCloseException("seckill is closed");
            }
            else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);
                if(insertCount<=0) {
                    throw new RepeatKillException("seckill repeated");
                }
                else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId,SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException e1) {
            throw e1;
        }
        catch (RepeatKillException e2) {
            throw e2;
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            //将编译期异常转化为运行期异常 ????
            throw new SeckillException("seckill inner error :"+ e.getMessage());
        }
    }
}
