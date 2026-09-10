package sag.example.spring_boot_anatom.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@LogLifecycle
@Component("BeanForHisLifeCycleDissection")
@Qualifier("QualifierForBeanForHisLifeCycleDissection")
public class Bean implements ApplicationContextAware, BeanFactoryAware, BeanNameAware
        , InitializingBean, DisposableBean {
    static {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA1 <=Bean Life Cycle=> static в бине.");
    }

    private InjectedBean injectedBean;

    public Bean() {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA1 <=Bean Life Cycle=> Вызов конструктора.");
    }

    public InjectedBean getInjectedBean() {
        return injectedBean;
    }

    @Autowired
    public void setInjectedBean(InjectedBean injectedBean) {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA1 <=Bean Life Cycle=> Autowired через set'тер.");
        this.injectedBean = injectedBean;
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA1 <=Bean Life Cycle=> setBeanFactory через BeanFactoryAware.");
    }

    @Override
    public void setBeanName(@NonNull String name) {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA1 <=Bean Life Cycle=> setBeanName (%s) через BeanNameAware.".formatted(name));
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA1 <=Bean Life Cycle=> setApplicationContext через ApplicationContextAware.");
    }

    @PostConstruct
    void postConstruct() {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA1 <=Bean Life Cycle=> @PostConstruct.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA1 <=Bean Life Cycle=> InitializingBean afterPropertiesSet.");
    }

    @PreDestroy
    void preDestroy() {
        IO.println("-----\uD83E\uDED8\uD83D\uDCA5 <=Bean Life Cycle=> @PreDestroy.");
    }

    @Override
    public void destroy() throws Exception {
        IO.println("-----\uD83E\uDED8 \uD83D\uDCA5 <=Bean Life Cycle=> DisposableBean destroy.");
    }
}
