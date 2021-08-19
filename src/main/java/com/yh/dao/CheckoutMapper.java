package com.yh.dao;

import com.yh.pojo.Checkout;

public interface CheckoutMapper {

    /**
     * 增加一条退房记录
     * @param checkout
     * @return
     */
    int addCheckout(Checkout checkout);
}
