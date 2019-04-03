package com.framework.modules.sys.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuMeta {

    private boolean hideInMenu;

    private boolean notCache;

    private boolean hideInBread;

    private String redirect;

    private String title;

    private String beforeCloseName;

    private String icon;

    private String href;

}
