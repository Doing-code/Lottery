package cn.forbearance.lottery.domain.award.service.factory;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.award.service.goods.IDistributionGoods;
import cn.forbearance.lottery.domain.award.service.goods.impl.CouponGoods;
import cn.forbearance.lottery.domain.award.service.goods.impl.DescGoods;
import cn.forbearance.lottery.domain.award.service.goods.impl.PhysicalGoods;
import cn.forbearance.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 发奖奖品配置
 *
 * @author cristina
 */
public class GoodsConfig {

    /**
     * 奖品发送策略
     */
    protected static Map<Integer, IDistributionGoods> goodsGroup = new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init() {
        goodsGroup.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsGroup.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsGroup.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsGroup.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }

}
