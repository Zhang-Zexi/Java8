package com.aqumon.zzx.lombok;

import lombok.val;

import java.util.ArrayList;

/**
 * @val注解
 * 弱语言变量
 */
public class ValTest {

    public ValTest() {
        val field = "zzx";

        val list = new ArrayList<String>();
        list.add("zzx");
    }

}
