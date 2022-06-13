package org.example.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Maps;
import lombok.var;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * 生成 mapper, service, controller, entity 类信息
 * @author huopengbo
 * @version 1.0
 * @date 2020-06-18 11:03
 */
public class GeneratorClass {

    // 开发人员 必改 1
    static String author = "huopengbo";
    // 基础包路径 必改
    static String basePackage = "org.example";
    // 表名称 必改
    static String tableName = "tbl_user";
    // 表前缀
    static String tablePrefix = "tbl";
    // 当前服务名称 必改
    static String serverName = "testl_service";
    // 数据库连接
    static String jdbcUrl = "jdbc:mysql://192.168.3.94:3306/bkex_exchange";
    // 数据库用户名
    static String dbUsername = "root";
    // 数据库密码
    static String dbPassword = "bkex.com";
    // 重新生成时是否覆盖已有, 默认覆盖
    static boolean fileOverride = true;
    // 是否仅覆盖 Entity/Model 实体
    static boolean isOnlyOverrideEntity = true;

    private static final String SPLIT = File.separator;

    public static void main(String[] args) {
        System.out.println("工程根路径: " + System.getProperty("user.dir"));
        System.out.print("自动生成是否需要覆盖已有文件(任意输入执行 3. 如未生成请确认上面路径是否获取正确, 是否为工程根目录, 或调整 log4j 日志为 debug 级别查看. 如路径获取的是非当前工程根路径, 则需要确认当前运行配置的 Working directory 是否正确 !)：\r\n1: 不覆盖\n2. 全部覆盖\n3. 仅覆盖 Entity/Model\r\n\r\n请输入: ");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                fileOverride = false;
                break;
            case 2:
                isOnlyOverrideEntity = false;
                break;
            case 3:
                isOnlyOverrideEntity = true;
                break;
            default:
        }
        generator();
    }

    public static void generator() {
        String javaPath = "/src/main/java/";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + javaPath);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setFileOverride(fileOverride);
        gc.setDateType(DateType.SQL_PACK);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(jdbcUrl);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(dbUsername);
        dsc.setPassword(dbPassword);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(basePackage);
        pc.setController("rest");
        pc.setService("api");
        pc.setServiceImpl("impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> result = Maps.newHashMap();
                result.put("serverName", serverName);
                setMap(result);
                getConfig().getTableInfoList().forEach(v -> {
                    // 自动生成Service 去掉 I 前缀
                    v.setServiceName(v.getServiceName().substring(1));
                    v.setEntityName(v.getEntityName() + "Entity");
                });
            }

            @Override
            public Map<String, Object> prepareObjectMap(Map<String, Object> objectMap) {
                // package 中加入model命名
                @SuppressWarnings("unchecked") var packages = (Map<String, Object>)objectMap.get("package");
                packages.put("Model", String.valueOf(packages.get("Entity")).replace("entity", "model"));
                // model 对象名称
                objectMap.put("model", String.valueOf(objectMap.get("entity")).replace("Entity", ""));
                return super.prepareObjectMap(objectMap);
            }
        };
        cfg.setFileOutConfigList(getFileOutConfigs(projectPath, javaPath));
        mpg.setCfg(cfg);
        // 禁止生成xml
        mpg.setTemplate(new TemplateConfig()
                .disable(TemplateType.XML)
                .disable(TemplateType.SERVICE)
                .disable(TemplateType.ENTITY)
                .disable(TemplateType.MAPPER)
                .disable(TemplateType.CONTROLLER));
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperMapperClass("com.weibi.core.commons.db.extension.mapper.BaseDao");
        strategy.setSuperServiceImplClass("com.weibi.core.commons.db.extension.service.impl.BaseServiceImpl");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setTablePrefix(tablePrefix);
        strategy.setLikeTable(new LikeTable(tableName, SqlLike.LEFT));
        mpg.setStrategy(strategy);
        // 生成
        mpg.execute();
    }

    private static List<FileOutConfig> getFileOutConfigs(String projectPath, String javaPath) {
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 工程跟目录
        String rootPath = projectPath; // .substring(0, projectPath.lastIndexOf(SPLIT))
        // 包转换文件路径
        String projectRootPath = basePackage.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        // 自定义配置会被优先输出
        if (!isOnlyOverrideEntity) {
            focList.add(
                    new FileOutConfig("/templates/mapper.java.vm") {
                        @Override
                        public String outputFile(TableInfo tableInfo) {
                            // 工程跟路径 + 子模块名称 + 包路径
                            return rootPath + SPLIT + serverName + "-db/"
                                    + javaPath + projectRootPath + "/mapper/"
                                    + tableInfo.getMapperName() + StringPool.DOT_JAVA;
                        }
                    });
            focList.add(
                    new FileOutConfig("/templates/service.java.vm") {
                        @Override
                        public String outputFile(TableInfo tableInfo) {
                            // 工程跟路径 + 子模块名称 + 包路径
                            return rootPath + SPLIT + serverName + "-api/"
                                    + javaPath + projectRootPath + "/api/"
                                    + tableInfo.getServiceName() + StringPool.DOT_JAVA;
                        }
                    });
            focList.add(
                    new FileOutConfig("/templates/serviceImpl.java.vm") {
                        @Override
                        public String outputFile(TableInfo tableInfo) {
                            // 工程跟路径 + 子模块名称 + 包路径
                            return rootPath + SPLIT + serverName + "-impl/"
                                    + javaPath + projectRootPath + "/impl/"
                                    + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
                        }
                    });
            focList.add(
                    new FileOutConfig("/templates/controller.java.vm") {
                        @Override
                        public String outputFile(TableInfo tableInfo) {
                            // 工程跟路径 + 子模块名称 + 包路径
                            return rootPath + SPLIT + serverName + "-rest/"
                                    + javaPath + projectRootPath + "/rest/"
                                    + tableInfo.getControllerName() + StringPool.DOT_JAVA;
                        }
                    });
        }
        focList.add(
                new FileOutConfig("/templates/entity.java.vm") {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 工程跟路径 + 子模块名称 + 包路径
                        return rootPath + SPLIT + serverName + "-db/"
                                + javaPath + projectRootPath + "/entity/"
                                + tableInfo.getEntityName() + StringPool.DOT_JAVA;
                    }
                });
        focList.add(
                new FileOutConfig("/templates/model.java.vm") {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 工程跟路径 + 子模块名称 + 包路径
                        return rootPath + SPLIT + serverName + "-api/"
                                + javaPath + projectRootPath + "/model/"
                                + tableInfo.getEntityName().replace("Entity", "") + StringPool.DOT_JAVA;
                    }
                });
        return focList;
    }
}
