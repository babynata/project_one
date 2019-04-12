package project_one.callback;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CallBackServiceFactory<T> implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    private String beanName;

    public static CallBackService<?> callBackService;

    public CallBackServiceFactory(String beanName) {
        this.beanName = beanName;
    }

    public CallBackService<T> getCallBackService() {
        return (CallBackService<T>) this.applicationContext.getBean(beanName);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static class CallBackServiceFactoryHolder {

        static CallBackServiceFactory<?> instance=null;

        public static CallBackService<?> getInstance(String beanName){
            return (CallBackService<?>) (instance = new CallBackServiceFactory<Object>(beanName));
        }

    }

    public static void main(String[] args){

    }

}
