package com.hcl.wallet;

record Currency(String name, String symbol) {

    public static Currency usd = new Currency("US Dollar", "$");
    public static Currency euro = new Currency("Euro", "€");

}
