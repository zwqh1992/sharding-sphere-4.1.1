//package cn.zwqh.shardingspheredemo7.config;
//
//import org.apache.shardingsphere.shardingjdbc.orchestration.api.yaml.YamlOrchestrationEncryptDataSourceFactory;
//import org.apache.shardingsphere.shardingjdbc.orchestration.api.yaml.YamlOrchestrationMasterSlaveDataSourceFactory;
//import org.apache.shardingsphere.shardingjdbc.orchestration.api.yaml.YamlOrchestrationShardingDataSourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.io.File;
//import java.io.IOException;
//import java.sql.SQLException;
//
//
///**
// * @description: 数据源配置
// * @author: wangc
// * @create: 2021-07-20 14:21
// **/
//@Configuration
//public class DataSourceConfig {
//
//    private static ShardingType shardingType = ShardingType.SHARDING_DATABASES_AND_TABLES;
////    private static ShardingType shardingType = ShardingType.MASTER_SLAVE;
////    private static ShardingType shardingType = ShardingType.ENCRYPT;
//
//    private static boolean loadConfigFromRegCenter = false;
//    // private static boolean loadConfigFromRegCenter = true;
//
//    private static RegistryCenterType registryCenterType = RegistryCenterType.ZOOKEEPER;
//
//    @Bean
//    public DataSource dataSource() throws IOException, SQLException {
//        String yamlFilePath;
//        switch (shardingType) {
//            case SHARDING_DATABASES_AND_TABLES:
//                yamlFilePath = String.format("/META-INF/%s/%s/sharding-databases-tables.yaml", registryCenterType.name().toLowerCase(), loadConfigFromRegCenter ? "cloud" : "local");
//                return YamlOrchestrationShardingDataSourceFactory.createDataSource(getFile(yamlFilePath));
//            case MASTER_SLAVE:
//                yamlFilePath = String.format("/META-INF/%s/%s/master-slave.yaml", registryCenterType.name().toLowerCase(), loadConfigFromRegCenter ? "cloud" : "local");
//                return YamlOrchestrationMasterSlaveDataSourceFactory.createDataSource(getFile(yamlFilePath));
//            case ENCRYPT:
//                yamlFilePath = String.format("/META-INF/%s/%s/encrypt.yaml", registryCenterType.name().toLowerCase(), loadConfigFromRegCenter ? "cloud" : "local");
//                return YamlOrchestrationEncryptDataSourceFactory.createDataSource(getFile(yamlFilePath));
//            default:
//                throw new UnsupportedOperationException(shardingType.name());
//        }
//    }
//
//    private File getFile(final String fileName) {
//        return new File(this.getClass().getResource(fileName).getFile());
//    }
//}
