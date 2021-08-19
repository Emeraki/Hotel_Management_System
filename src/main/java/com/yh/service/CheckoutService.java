package com.yh.service;

import com.yh.pojo.Checkout;

public interface CheckoutService {

    /**
     * 增加一条退房记录
     * @param checkout
     * @return
     */
    int addCheckout(Checkout checkout);
}
