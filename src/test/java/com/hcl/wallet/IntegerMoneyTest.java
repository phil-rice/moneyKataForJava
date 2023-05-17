package com.hcl.wallet;

import java.util.function.Function;

public class IntegerMoneyTest extends AbstractMoneyTest<Integer> {
    public IntegerMoneyTest() {
        super(new IntMoneyOps(), i ->i);
    }
}
