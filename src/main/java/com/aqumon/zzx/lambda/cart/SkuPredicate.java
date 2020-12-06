package com.aqumon.zzx.lambda.cart;

/**
 * Sku选择谓词接口
 */
@FunctionalInterface
public interface SkuPredicate {

    /**
     * 选择判断标准
     * @param sku
     * @return
     */
    boolean test(Sku sku);

//    boolean test1(Sku sku);
}
