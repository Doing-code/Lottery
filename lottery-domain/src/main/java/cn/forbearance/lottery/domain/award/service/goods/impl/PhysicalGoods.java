package cn.forbearance.lottery.domain.award.service.goods.impl;

import cn.forbearance.lottery.common.Constants;
import cn.forbearance.lottery.domain.award.model.req.GoodsReq;
import cn.forbearance.lottery.domain.award.model.res.DistributionRes;
import cn.forbearance.lottery.domain.award.service.goods.DistributionBase;
import cn.forbearance.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * 实物类奖品
 *
 * @author cristina
 */
@Component
public class PhysicalGoods extends DistributionBase implements IDistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        log.info("模拟调用实物发奖 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.GrantState.COMPLETE.getCode());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.PhysicalGoods.getCode();
    }
}
