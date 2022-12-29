package com.example.aruoradbtest.aop;

import com.example.aruoradbtest.annotation.DataSource;
import com.example.aruoradbtest.config.datasource.DynamicDataSourceContextHolder;
import com.example.aruoradbtest.enums.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

@Aspect
@Component
@Primary
@Slf4j
public class DataSourceAspect {
    private final Pattern READ_PATTERN;
    private final Pattern WRITER_PATTERN; // by default we use writer

    public DataSourceAspect(@Value("${spring.datasource.reader.pattern}") String readPattern,
                            @Value("${spring.datasource.writer.pattern}") String writerPattern) {
        READ_PATTERN = Pattern.compile(getRegex(readPattern));
        WRITER_PATTERN = Pattern.compile(getRegex(writerPattern));
    }

    private String getRegex(String str) {

        String returnRegex =  str.replaceAll("\\*", ".*")
                .replaceAll(" ", "")
                .replaceAll(",", "|");
        log.info("Return Regex pattern is [{}]",returnRegex);
        return returnRegex;
    }

    @Around("within(@com.example.aruoradbtest.annotation.DataSource *)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        point.getTarget();
        Method method = signature.getMethod();
        DataSource dataSource = method.getAnnotation(DataSource.class);

        log.info("Annotated method Name:  {}",method.getName());

        if (dataSource != null) {
            // In order to have higher granularity,
            // I make method level annotation has higher priority than the class level.
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        } else {
            if (READ_PATTERN.matcher(method.getName()).matches()) {
                //if any method name with read pattern is matched for current thread execution it will set the reader (slave) data source
                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.READER.name());
            } else {
                //if any method name with write pattern is matched for current thread execution it will set the writer (master) data source
                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.WRITER.name());
            }
        }

        try {
            return point.proceed();
        } finally {
            // clear data source after method's execution.
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }
}
