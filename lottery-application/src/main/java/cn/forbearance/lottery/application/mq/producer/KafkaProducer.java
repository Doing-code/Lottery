package cn.forbearance.lottery.application.mq.producer;

import cn.forbearance.lottery.domain.activity.model.vo.InvoiceVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * MQ 消息发送服务
 *
 * @author cristina
 */
@Component
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * MQ主题：中奖发货单
     * <p>
     * 启动zk：bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
     * 启动kafka：bin/kafka-server-start.sh -daemon config/server.properties
     * 创建topic：bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic lottery_invoice
     */
    public static final String TOPIC_INVOICE = "lottery_invoice";

    public static final String TOPIC_GROUP = "lottery";

    @KafkaListener(topics = KafkaProducer.TOPIC_INVOICE, groupId = KafkaProducer.TOPIC_GROUP)
    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVo invoice) {
        String context = JSON.toJSONString(invoice);
        logger.info("发送MQ消息 topic：{} bizId：{} message：{}", TOPIC_INVOICE, invoice.getuId(), context);
        return kafkaTemplate.send(TOPIC_INVOICE, context);
    }
}
