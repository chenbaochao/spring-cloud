package com.play001.cloud.cms.api.navigation;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.NavigationBarService;
import com.play001.cloud.cms.service.NavigationService;
import com.play001.cloud.support.entity.Navigation;
import com.play001.cloud.support.entity.NavigationBar;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/navigation")
public class NavigationRestController {

    @Autowired
    private NavigationService navigationService;

    /**
     * 获取所有导航
     */
    @PermissionCode("navigation_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> getList(){
        return navigationService.getList();
    }

}
