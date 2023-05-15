package cn.forbearance.lottery.domain.award.service.goods;

import cn.forbearance.lottery.domain.award.model.req.GoodsReq;
import cn.forbearance.lottery.domain.award.model.res.DistributionRes;

/**
 * 奖品配送接口，抽象成配送货物接口，把各类奖品模拟成货物、配送代表着发货，包括虚拟奖品和实物奖品
 *
 * @author cristina
 */
public interface IDistributionGoods {

    /**
     * 奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     *
     * @param req 物品信息
     * @return 配送结果
     */
    DistributionRes doDistribution(GoodsReq req);

    /**
     * #
     *
     * @return
     */
    Integer getDistributionGoodsName();
}
