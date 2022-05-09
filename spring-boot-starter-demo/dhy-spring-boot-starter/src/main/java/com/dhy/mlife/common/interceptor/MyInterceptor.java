package com.dhy.mlife.common.interceptor;

import com.dhy.mlife.common.context.AppContext;
import com.dhy.mlife.common.context.AppContextHolder;
import com.dhy.mlife.common.core.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

public class MyInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        appContextPreHandle(request);

        log.info("拦截器前置动作");
        return true;
    }

    /**
     * 公共报文处理
     * @param request
     */
    private void appContextPreHandle(HttpServletRequest request) {
        //请求上下文信息
        AppContext appContext = new AppContext();
        //从 query string 中获取参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key,value)->{
            log.info("from url:"+key+","+value[0]);
            try {
                setFieldValue(appContext, key, value);
            }catch (NoSuchFieldException e){
                log.warn(e.getMessage());
            }catch (IllegalAccessException e){
                log.warn(e.getMessage());
            }catch (Exception e){
                throw new BusinessException("","参数错误："+e.getMessage());
            }

        });

        AppContextHolder.set(appContext);
    }

    private void setFieldValue(AppContext appContext, String key, String[] value) throws NoSuchFieldException, IllegalAccessException {
        Field field = appContext.getClass().getDeclaredField(key);
        field.setAccessible(true);
        if (field.getType()==String.class) {
            field.set(appContext,value[0]);
        }else if (field.getType()==double.class||field.getType()==Double.class) {
            field.set(appContext, Double.parseDouble(value[0]));
        }else if (field.getType()==long.class||field.getType()==Long.class) {
            field.set(appContext,Long.parseLong(value[0]));
        }else if (field.getType()==boolean.class||field.getType()==Boolean.class) {
            field.set(appContext, Boolean.parseBoolean(value[0]));
        }else if (field.getType()==int.class||field.getType()==Integer.class) {
            field.set(appContext, Integer.parseInt(value[0]));
        }else if (field.getType()==float.class||field.getType()==Float.class) {
            field.set(appContext, Float.parseFloat(value[0]));
        }else if (field.getType()==short.class||field.getType()==Short.class) {
            field.set(appContext, Short.parseShort(value[0]));
        }else if (field.getType()==byte.class||field.getType()==Byte.class) {
            field.set(appContext, Byte.parseByte(value[0]));
        }else if (field.getType()==char.class||field.getType()==Character.class) {
            field.set(appContext, Character.valueOf(value[0].charAt(0)));
        }else{
            log.error("不支持的数据类型："+field.getName()+"->"+field.getType());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AppContextHolder.remove();
        log.info("拦截器后置动作");
    }


}
