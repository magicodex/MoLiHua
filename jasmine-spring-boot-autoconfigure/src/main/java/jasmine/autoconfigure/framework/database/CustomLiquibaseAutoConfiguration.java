package jasmine.autoconfigure.framework.database;

import jasmine.framework.database.liquibase.BeanTaskChange;
import jasmine.framework.database.liquibase.CustomSpringLiquibase;
import liquibase.change.DatabaseChange;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author mh.z
 */
@ConditionalOnClass({SpringLiquibase.class, DatabaseChange.class})
@ConditionalOnProperty(
        prefix = "spring.liquibase",
        name = {"enabled"},
        matchIfMissing = true
)
@AutoConfigureBefore(LiquibaseAutoConfiguration.class)
@EnableConfigurationProperties({LiquibaseProperties.class, InitDataProperties.class})
@Configuration
public class CustomLiquibaseAutoConfiguration {

    @ConditionalOnMissingBean(CustomSpringLiquibase.class)
    @Bean
    public CustomSpringLiquibase customSpringLiquibase(LiquibaseProperties properties,
                                                       InitDataProperties initDataProperties,
                                                       DataSource dataSource) {
        CustomSpringLiquibase liquibase = new CustomSpringLiquibase();
        liquibase.setDefaultUserId(initDataProperties.getDefaultUserId());
        liquibase.setDefaultTenantId(initDataProperties.getDefaultTenantId());
        liquibase.setDataSource(dataSource);

        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setClearCheckSums(properties.isClearChecksums());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setLiquibaseSchema(properties.getLiquibaseSchema());
        liquibase.setLiquibaseTablespace(properties.getLiquibaseTablespace());
        liquibase.setDatabaseChangeLogTable(properties.getDatabaseChangeLogTable());
        liquibase.setDatabaseChangeLogLockTable(properties.getDatabaseChangeLogLockTable());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        liquibase.setTestRollbackOnUpdate(properties.isTestRollbackOnUpdate());
        liquibase.setTag(properties.getTag());

        return liquibase;
    }

    @ConditionalOnMissingBean(BeanTaskChange.class)
    @Bean
    public BeanTaskChange beanTaskChange() {
        return new BeanTaskChange();
    }

}
