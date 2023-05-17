package com.hcl.wallet;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract public class AbstractMoneyTest<T> {

    final IMoneyOps<T> ops;
    final Function<Integer, T> moneyFn;

    T money(int amount) {
        return moneyFn.apply(amount);
    }

    public AbstractMoneyTest(IMoneyOps<T> ops, Function<Integer, T> moneyFn) {
        this.ops = ops;
        this.moneyFn = moneyFn;
    }

    @Test
    public void testCanAddMoneyTogether() {
        assertEquals(money(5), ops.add(money(2), (money(3))));
    }

    @Test
    public void testCanAddMoneyTogetherWhenOneIsNegative() {
        //test adding minus 2 dollars to minus 3 dollars makes minus 5 dollars
        assertEquals(money(-5), ops.add(money(-2), (money(-3))));
        //test adding minus 2 dollars to 3 dollars makes 1 dollars
    }

    public void testCanSubtractMoney() {
        assertEquals(money(1), ops.subtract(money(3), (money(2))));

        //test sbtracting minus 2 dollars from 3 dollars makes 5 dollars
        assertEquals(money(5), ops.subtract(money(3), (money(-2))));
    }

    public void testCanSubtractMoneyWhenNegative() {
        //test subtracting 2 dollars from minus 3 dollars makes minus 5 dollars
        assertEquals(money(-5), ops.subtract(money(-3), (money(2))));
        //test sbtracting minus 2 dollars from minus 3 dollars makes minus 1 dollars
        assertEquals(money(-1), ops.subtract(money(-3), (money(-2))));
    }


    @Test
    public void testCanSplit10DollarsBetween2people() {
        assertEquals(List.of(money(5), money(5)), new MoneyHelper<T>(ops).splitNWays(money(10), 2));
    }

    @Test
    public void testCanSplit10DollarsBetween3people() {
        assertEquals(List.of(money(4), money(3), money(3)), new MoneyHelper<T>(ops).splitNWays(money(10), 3));
    }

}
