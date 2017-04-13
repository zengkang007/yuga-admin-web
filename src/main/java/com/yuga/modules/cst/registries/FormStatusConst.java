/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.yuga.modules.cst.registries;

/**
 * Created by zengk on 2017/1/8.
 */
public class FormStatusConst {
    final public static  int BID_DENY = -1;
    final public static int AUDITING = 0;  //审核中
    final public static int BID_ACCEPT = 1;
    final public static int BID_OUT = 2;
    final public static int CONSULTANT_BIDDED = 3;  //顾问已经被拍卖
    final public static int CONSULTANT_BOOKED = 4;  //顾问已经被预定
}
