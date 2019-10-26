package com.itkang.itkang_utils.utis.Test;

import com.itkang.itkang_utils.utis.passwordUtils.HexUtils.AesUtil;
import org.junit.Test;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2019/10/26 11:15
 * @explain
 */
public class test3 {

    @Test
    public void test() throws Exception {
        String aesDecrypt = AesUtil.aesDecrypt("ha0CZ1ogjKxpxHwBgktrPg==", "abcdefgabcdefg12");
        System.out.println(aesDecrypt);
    }
}
