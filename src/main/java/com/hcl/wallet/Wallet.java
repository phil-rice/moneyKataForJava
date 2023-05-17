package com.hcl.wallet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public record Wallet<T>(Map<Currency, T> monies) {
}

class WalletMoneyOps<T> implements IMoneyOps<Wallet<T>> {
    private final IMoneyOps<T> moneyOps;


    WalletMoneyOps(IMoneyOps<T> moneyOps) {
        this.moneyOps = moneyOps;
    }

    public Wallet<T> zero() {
        return new Wallet<>(Map.of());
    }

    public Wallet<T> add(Wallet<T> t1, Wallet<T> t2) {
        var map1 = t1.monies();
        var map2 = t2.monies();
        var allCurrencies = new HashSet<>(map1.keySet());
        allCurrencies.addAll(map2.keySet());
        var newMap = allCurrencies.stream().map(currency -> {
            var t1Value = map1.getOrDefault(currency, moneyOps.zero());
            var t2Value = map2.getOrDefault(currency, moneyOps.zero());
            return Map.entry(currency, moneyOps.add(t1Value, t2Value));
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new Wallet<>(newMap);
    }
    public Wallet<T> subtract(Wallet<T> t1, Wallet<T> t2) {
        var map1 = t1.monies();
        var map2 = t2.monies();
        var allCurrencies = new HashSet<>(map1.keySet());
        allCurrencies.addAll(map2.keySet());
        var newMap = allCurrencies.stream().map(currency -> {
            var t1Value = map1.getOrDefault(currency, moneyOps.zero());
            var t2Value = map2.getOrDefault(currency, moneyOps.zero());
            return Map.entry(currency, moneyOps.subtract(t1Value, t2Value));
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new Wallet<>(newMap);
    }

    @Override
    public Wallet<T> divide(Wallet<T> t1, int n) {
        var map1 = t1.monies();
        var newMap = map1.entrySet().stream().map(entry -> {
            var currency = entry.getKey();
            var value = entry.getValue();
            return Map.entry(currency, moneyOps.divide(value, n));
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new Wallet<>(newMap);
    }
}
