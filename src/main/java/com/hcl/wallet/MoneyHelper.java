package com.hcl.wallet;

import java.util.ArrayList;
import java.util.List;

interface IMoneyHelper<T> {
    List<T> splitNWays(T t, int n);

    T addAll(List<T> monies);
}

public class MoneyHelper<T> implements IMoneyHelper<T> {
    IMoneyOps<T> moneyOps;

    public MoneyHelper(IMoneyOps<T> moneyOps) {
        this.moneyOps = moneyOps;
    }

    public List<T> splitNWays(T t, int n) {
        var result = new ArrayList<T>();
        for (int i = 0; i < n; i++) result.add(moneyOps.divide(t, n));
        var total = addAll(result);
        var remainder = moneyOps.subtract(t, total);
        result.set(0, moneyOps.add(result.get(0), remainder));
        return result;
    }

    public T addAll(List<T> monies) {
        var result = moneyOps.zero();
        for (T t : monies) result = moneyOps.add(result, t);
        return result;
    }
}
