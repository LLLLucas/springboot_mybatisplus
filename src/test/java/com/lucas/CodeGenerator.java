package com.lucas;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeGenerator {
    public static void main(String[] args) {
        //构建一个代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //策略配置

        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String property = System.getProperty("user.dir");//获取当前目录
        globalConfig.setOutputDir(property + "/src/main/java");
        globalConfig.setAuthor("lucas");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);//是否要覆盖生成的代码
        globalConfig.setServiceName("%sService");//去service的i前缀
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setSwagger2(true);//配置swagger

        autoGenerator.setGlobalConfig(globalConfig);
        //2、设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("lu1991101");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);

        //3、 包的配置
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setModuleName("blog");//这个代表在com.lucas创建blog文件夹然后在生成...
        packageConfig.setParent("com.lucas");
        packageConfig.setEntity("pojo");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user2");//设置要映射的表名称,就是将要生成的实体 user2
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//自动生成lombok
        strategy.setLogicDeleteFieldName("deleted");

        //自动填充配置
        TableFill tableFill = new TableFill("create_time", FieldFill.INSERT);
        TableFill tableFill2 = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> TableFills = new ArrayList<>();
        TableFills.add(tableFill);
        TableFills.add(tableFill2);
        strategy.setTableFillList(TableFills);
        strategy.setRestControllerStyle(true);//restController风格
        autoGenerator.setStrategy(strategy);
        autoGenerator.execute();

    }

}
