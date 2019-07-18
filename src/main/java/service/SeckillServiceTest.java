package service;

import dto.Exposer;
import dto.SeckillExecution;
import entity.Seckill;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.SeckillService;


import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() {
        Seckill seckill = seckillService.getById(1001);
        logger.info("Seckill{}",seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        logger.info("exposer{}", exposer);
    }

    @Test
    public void executeSeckill() {
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()) {
            logger.info("exposer={}",exposer);
            long userphone = 13476191876L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution res = seckillService.executeSeckill(1000,userphone,md5);
                logger.info("result={}",res);
            }catch (RepeatKillException e) {
                logger.info(e.getMessage());
            }catch (SeckillCloseException e1) {
                logger.error(e1.getMessage());
            }
        }
        else {
            logger.warn("exposer={}",exposer);
        }

    }
}