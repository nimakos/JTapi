package gr.nikolis.JTapi.config;

import gr.nikolis.JTapi.constants.mappings.HomeMappings;
import gr.nikolis.JTapi.constants.views.ViewNames;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Rooting the first open the url this direct us to home page
     * == used for home view ==
     * e.x. http://localhost:8888/
     *
     * @param registry The registry object
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(HomeMappings.HOME_DEFAULT).setViewName(ViewNames.HOME);
        registry.addViewController(HomeMappings.HOME).setViewName(ViewNames.HOME);
    }
}
