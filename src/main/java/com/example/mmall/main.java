package com.example.mmall;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        AutoGenerator autoGenerator =new AutoGenerator();

        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("151567");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/aaa?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        autoGenerator.setDataSource(dataSourceConfig);
        GlobalConfig globalConfig=new GlobalConfig();
        globalConfig.setOpen(true);
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java") ;
        globalConfig.setAuthor("坚强");
        globalConfig.setServiceName("%sService");
        autoGenerator.setGlobalConfig(globalConfig);
        PackageConfig packageConfig =new PackageConfig();
    packageConfig.setParent("com.example.mmall");
    packageConfig.setEntity("entity");
    packageConfig.setController("controller");
    packageConfig.setMapper("mapper");
    packageConfig.setService("service");
    autoGenerator.setPackageInfo(packageConfig);
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        List<TableFill> list=new ArrayList<>();
        TableFill tableFill1=new TableFill("create_time", FieldFill.INSERT);
        TableFill tableFill2=new TableFill("update_time",FieldFill.INSERT_UPDATE);
        list.add(tableFill1);
        list.add(tableFill2);



        strategyConfig.setTableFillList(list);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.execute();
    }
}
