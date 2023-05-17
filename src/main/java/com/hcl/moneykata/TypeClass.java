package com.hcl.moneykata;

interface Money<T> {
    T add(T money);

    T split(T money, int n);
}
