package com.hcl.wallet;

import java.util.ArrayList;
import java.util.List;

public interface IMoneyOps<T> {
    T zero();

    T add(T t1, T t2);

    T subtract(T t1, T t2);

    T divide(T t1, int n);
}

class IntMoneyOps implements IMoneyOps<Integer> {
    @Override
    public Integer zero() {
        return 0;
    }

    @Override
    public Integer add(Integer t1, Integer t2) {
        return t1 + t2;
    }

    public Integer subtract(Integer t1, Integer t2) {
        return t1 - t2;
    }

    public Integer divide(Integer t1, int n) {
        return t1 / n;
    }
}

