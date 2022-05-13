package tech.huangyt.concurrency;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tong
 */
public class ApplicationContext {

    // 在context中保存configuration实例
    private ApplicationConfiguration configuration;
    // 在context中保存runtimeInfo实例
    private RuntimeInfo runtimeInfo;
    // 线程上下文
    private final ConcurrentHashMap<Thread, ActionContext> contexts = new ConcurrentHashMap<>();
    // ...其他实例

    public ActionContext getActionContext() {
        ActionContext actionContext = contexts.get(Thread.currentThread());
        if (actionContext == null) {
            actionContext = new ActionContext();
            contexts.put(Thread.currentThread(), actionContext);
        }
        return actionContext;
    }

    // 采用Holder实现单例
    private static class Holder {
        private static ApplicationContext instance = new ApplicationContext();
    }

    public static ApplicationContext getContext() {
        return Holder.instance;
    }

    public void setConfiguration(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    public ApplicationConfiguration getConfiguration() {
        return configuration;
    }

    public void setRuntimeInfo(RuntimeInfo runtimeInfo) {
        this.runtimeInfo = runtimeInfo;
    }

    public RuntimeInfo getRuntimeInfo() {
        return runtimeInfo;
    }
}
