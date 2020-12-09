package com.aqumon.zzx.lambda.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzx
 * <p>
 * 购物车服务类
 */
public class CartService {

    // 加入到购物车中的商品信息
    private static List<Sku> cartSkuList =
            new ArrayList<Sku>() {
                {
                    add(new Sku(654032, "无人机",
                            4999.00, 1,
                            4999.00, SkuCategoryEnum.ELECTRONICS));

                    add(new Sku(642934, "VR设备",
                            2299.00, 1,
                            2299.00, SkuCategoryEnum.ELECTRONICS));

                    add(new Sku(645321, "纯色衬衫",
                            409.00, 3,
                            1227.00, SkuCategoryEnum.CLOTHING));

                    add(new Sku(654327, "牛仔裤",
                            528.00, 1,
                            528.00, SkuCategoryEnum.CLOTHING));

                    add(new Sku(675489, "跑步机",
                            2699.00, 1,
                            2699.00, SkuCategoryEnum.SPORTS));

                    add(new Sku(644564, "Java编程思想",
                            79.80, 1,
                            79.80, SkuCategoryEnum.BOOKS));

                    add(new Sku(678678, "Java核心技术",
                            149.00, 1,
                            149.00, SkuCategoryEnum.BOOKS));

                    add(new Sku(697894, "大话数据结构",
                            78.20, 1,
                            78.20, SkuCategoryEnum.BOOKS));

                    add(new Sku(696968, "HeadFirst设计模式",
                            85.10, 1,
                            85.10, SkuCategoryEnum.BOOKS));
                }
            };

    /**
     * 获取商品信息列表
     *
     * @return
     */
    public static List<Sku> getCartSkuList() {
        return cartSkuList;
    }


    /**
     * Version 4.0.0
     * 根据不同的Sku判断标准，对Sku列表进行过滤
     *
     * @param cartSkuList
     * @param predicate   - 不同的Sku判断标准策略
     * @return
     */
    public static List<Sku> filterSkus(
            List<Sku> cartSkuList, SkuPredicate predicate) {

        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList) {
            // 根据不同的Sku判断标准策略，对Sku进行判断
            if (predicate.test(sku)) {
                result.add(sku);
            }
        }
        return result;
    }
}
