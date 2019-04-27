package org.springframework.samples.drink_safe.WebSocket;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

public class CustomConfigurator extends ServerEndpointRegistration.Configurator implements ApplicationContextAware {

    private static volatile BeanFactory context;

    @Override
    public <T> T getEndpointInstance(Class<T> WebSocketServer) throws InstantiationException {
        return context.getBean(WebSocketServer);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomConfigurator.context = applicationContext;
    }
}