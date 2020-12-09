package com.aqumon.zzx.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class VersionTwoTest {

    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        // 过滤商品单价大于1000的商品
        List<Sku> result = CartService.filterSkus(
                cartSkuList, new SkuPredicate() {
                    @Override
                    public boolean test(Sku sku) {
                        return sku.getSkuPrice() > 2000;
                    }
                });

        System.out.println(JSON.toJSONString(
                result, true));
    }

}
