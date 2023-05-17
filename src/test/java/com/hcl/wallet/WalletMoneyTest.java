package com.hcl.wallet;

import java.util.Map;

import static com.hcl.wallet.Currency.euro;
import static com.hcl.wallet.Currency.usd;

public class WalletMoneyTest extends AbstractMoneyTest<Wallet<Integer>> {

    public WalletMoneyTest() {
        super(new WalletMoneyOps(new IntMoneyOps()), i -> new Wallet(Map.of(usd, i, euro, i)));
    }
}
