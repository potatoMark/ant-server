package com.framework.common.utils;

import com.framework.modules.tool.vo.SchemaTableColumnVO;
import com.framework.modules.tool.vo.SchemaTableVO;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器 工具类
 */
public class GenerateUtils {

	protected static Logger logger = LoggerFactory.getLogger(GenerateUtils.class);

	public static List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("templates/Controller.java.vm");
		templates.add("templates/Service.java.vm");
		templates.add("templates/ServiceImpl.java.vm");
		templates.add("templates/Pojo.java.vm");
		templates.add("templates/VO.java.vm");
		templates.add("templates/Dao.java.vm");
		templates.add("templates/Dao.xml.vm");

		templates.add("templates/web/local.js.vm");
		templates.add("templates/web/list.vue.vm");
		templates.add("templates/web/edit.vue.vm");
		templates.add("templates/web/search.vue.vm");
		templates.add("templates/web/api.js.vm");
		templates.add("templates/web/route.js.vm");
		return templates;
	}

	/**
	 * 生成代码
	 */
	public static void generatorCode(SchemaTableVO schemaTableVO, ZipOutputStream zip) throws Exception {
		// 设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);

		//数据转换
		//列 类型转换
		Configuration config = getConfig();
		List<SchemaTableColumnVO> schemaTableColumnVOList = schemaTableVO.getSchemaTableColumnVOList();
		for (SchemaTableColumnVO schemaTableColumnVO : schemaTableColumnVOList) {
			schemaTableColumnVO.setDataType(config.getString(schemaTableColumnVO.getDataType(),"String"));
		}

		//主键设定
		SchemaTableColumnVO pk = null;
		for (SchemaTableColumnVO s : schemaTableColumnVOList) {

			if ("PRI".equals(s.getColumnKey())) {
				pk = s;
				break;
			}
		}
		//判断是否有修改履历字段
		boolean iscrtUser = false,iscrtDate = false, isupdUser = false,isupdDate = false;
		for (SchemaTableColumnVO item : schemaTableColumnVOList) {
			if (item.getAttrname().equals("createuser")) {
				iscrtUser = true;
			} else if (item.getAttrname().equals("updateuser")) {
				isupdUser = true;
			} else if (item.getAttrname().equals("createdate")) {
				iscrtDate = true;
			} else if (item.getAttrname().equals("updatedate")) {
				isupdDate = true;
			}
		}
		boolean isHistory = false;
		if (iscrtUser && iscrtDate && isupdUser && isupdDate) {
			isHistory = true;
		}

		//查找需要编辑的列
		List<SchemaTableColumnVO> showEditList = new ArrayList<>();
		List<SchemaTableColumnVO> showEditShowList = new ArrayList<>();
		for (SchemaTableColumnVO item : schemaTableColumnVOList) {
			if (item.isShowEdit()) {
				showEditList.add(item);
				if ("show".equals(item.getShowEditModel())) {
					showEditShowList.add(item);
					if ("select".equals(item.getEditComponent())
							) {
						item.setOptions(Arrays.asList(item.getSelectLocalItem().split(",")));
					}
				}
			}
		}
		//查询需要search的列
		List<SchemaTableColumnVO> searchList = new ArrayList<>();
		for (SchemaTableColumnVO item : schemaTableColumnVOList) {
			if (item.isCondition()) {
				searchList.add(item);
			}
		}


		//未设置PK，默认第一个列为PK
		if (pk == null) {
			pk = schemaTableColumnVOList.get(0);
		}
		//获取当前表的外键名称
		String fkName="";
		String[] strs = schemaTableVO.getTableName().split("_");
		if (strs.length == 1) {
			fkName = strs[0];
		} else {
			int loop = 1;
			boolean flg = true;
			for ( ; loop<strs.length; loop++ ) {

				if (flg){

					fkName = strs[loop].toLowerCase();
					flg=false;
				} else {

					fkName = fkName.concat(strs[loop].substring(0,1).toUpperCase().concat(strs[loop].substring(1).toLowerCase()));
				}
			}

		}
		fkName = fkName.concat("_id");


		// 封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("table", schemaTableVO);
		map.put("moduleName", schemaTableVO.getModuleName().substring(0,1).toUpperCase().concat(schemaTableVO.getModuleName().substring(1)));
		map.put("pk", pk);
		map.put("package", config.getString("packageName"));
		map.put("columns", schemaTableVO.getSchemaTableColumnVOList());
		map.put("editColumns", showEditList);
		map.put("searchColumns", searchList);
		map.put("editShowColumns", showEditShowList);
		map.put("history", isHistory);
		map.put("dynamic", schemaTableVO.isDynamicTab());
		map.put("fk",fkName);
		VelocityContext context = new VelocityContext(map);

		// 获取模板列表
		List<String> templates = getTemplates();
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);

			try {
				// 添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, schemaTableVO.getClassName(), config.getString("packageName"), schemaTableVO.getModuleName())));
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				logger.error("模板渲染失败".concat(template),e);
			}
		}
	}

	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig() throws Exception {
			return new PropertiesConfiguration("generator.yml");
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String className, String packageName, String moduleName) {
		String packagePath = "main" + File.separator + "java" + File.separator;
		String webpath = "web"+ File.separator + "src" + File.separator;
		if (StringUtils.isNotBlank(packageName)) {
			packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
		}

		if (template.contains("Pojo.java.vm")) {
			return packagePath + "pojo" + File.separator + className + ".java";
		}

		if (template.contains("VO.java.vm")) {
			return packagePath + "vo" + File.separator + className + "VO.java";
		}

		if (template.contains("Dao.java.vm")) {
			return packagePath + "dao" + File.separator + className + "Dao.java";
		}

		if (template.contains("Service.java.vm")) {
			return packagePath + "service" + File.separator + "I"+className + "Service.java";
		}

		if (template.contains("ServiceImpl.java.vm")) {
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}

		if (template.contains("Controller.java.vm")) {
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}

		if (template.contains("Dao.xml.vm")) {
			return packagePath + "dao" + File.separator + className + "Dao.xml";
		}

		if (template.contains("local.js.vm")) {
			return webpath+"views"+ File.separator +"modules"+ File.separator +moduleName+ File.separator +className.toLowerCase()+ File.separator +className.toLowerCase() + "-local.js";
		}

		if (template.contains("list.vue.vm")) {
			return webpath+"views"+ File.separator +"modules"+ File.separator +moduleName+ File.separator +className.toLowerCase()+ File.separator +className.toLowerCase() + "-list.vue";
		}

		if (template.contains("edit.vue.vm")) {
			return webpath+"views"+ File.separator +"modules"+ File.separator +moduleName+ File.separator +className.toLowerCase()+ File.separator +className.toLowerCase() + "-edit.vue";
		}

		if (template.contains("search.vue.vm")) {
			return webpath+"views"+ File.separator +"modules"+ File.separator +moduleName+ File.separator +className.toLowerCase()+ File.separator +className.toLowerCase() + "-search.vue";
		}

		if (template.contains("api.js.vm")) {
			return webpath+"api"+ File.separator +"modules"+ File.separator +moduleName+ File.separator +className.toLowerCase() + ".js";
		}

		if (template.contains("route.js.vm")) {
			return webpath+"router"+ File.separator +"modules"+ File.separator +moduleName+ File.separator +moduleName.toLowerCase() + ".js";
		}

		return null;
	}
}
