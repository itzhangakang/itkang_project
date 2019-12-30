package com.itkang.itkang_utils.utis.ExcelUtils;

import com.itkang.itkang_utils.utis.ExcelUtils.poi.excel.ShopDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FUN Test
 *
 * @author xuxueli 2017-09-08 22:41:19
 */

public class Test {

    public static void main(String[] args) {


        // Mock数据，Java对象列表
        List<ShopDTO> shopDTOList = new ArrayList<ShopDTO>();
        // 模拟创建100条数据
        for (int i = 0; i < 100; i++) {
            ShopDTO shop = new ShopDTO(true, "商户" + i, (short) i, 1000 + i, 10000 + i, (float) (1000 + i), (double) (10000 + i), new Date());
            shopDTOList.add(shop);
        }


        // List<Employee3> shopDTOList = new ArrayList<Employee3>();
        // 模拟创建100条数据
        for (int i = 0; i < 100; i++) {
            //Employee3 shop = new Employee3(100L, "A" + i, "男", new Date(), "1", "1", 2L, "w", "QQ");
            //shopDTOList.add(shop);
        }


        String filePath = "C:\\Users\\linxi\\Desktop\\测试.xls";


        // Excel导出：Object 转换为 Excel


        //ExcelExportUtil.exportToFile(filePath, shopDTOList);

        /**
         * Excel导入：Excel 转换为 Object
         *
         * C:\Users\linxi\Desktop
         */

        /*List<Object> list = ExcelImportUtil.importExcel(filePath, Employee3.class);
        //System.out.println(list);
        List<Employee3> shopDTOList = new ArrayList<Employee3>();
        for (Object o : list) {
            shopDTOList.add((Employee3) o);
        }*/
        System.err.println(shopDTOList);

    }

}
