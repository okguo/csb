package com.csb.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压csbAdmin-x.x.x.jar到resources目录
 * Created by administrator on 2016/12/18.
 */
public class CSBAdminUtil implements InitializingBean, ServletContextAware {

    private static Logger _log = LoggerFactory.getLogger(CSBAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
//        _log.debug("===== 开始解压csb-ui =====");
//        String version = PropertiesFileUtil.getInstance().get("csb-ui.version");
//        _log.debug("csb-ui.jar 版本: {}", version);
//        String jarPath = servletContext.getRealPath("/WEB-INF/lib/csb-ui-" + version + ".jar");
//        _log.debug("csb-ui.jar 包路径: {}", jarPath);
//        String resources = servletContext.getRealPath("/") + "/resources/csb-ui";
//        _log.debug("csb-ui.jar 解压到: {}", resources);
//        JarUtil.decompress(jarPath, resources);
//        _log.debug("===== 解压csb-ui完成 =====");
        servletContext.setAttribute("resourcePath",PropertiesFileUtil.getInstance().get("resource.path"));
        _log.debug("resourcePath:{}",PropertiesFileUtil.getInstance().get("resource.path"));
        servletContext.setAttribute("ssoServerUrl",PropertiesFileUtil.getInstance("csb-upms-client").get("sso.server.url"));
        _log.debug("ssoServerUrl:{}",PropertiesFileUtil.getInstance("csb-upms-client").get("sso.server.url"));
    }

}
