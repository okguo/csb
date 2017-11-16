package com.csb.common.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xujiawei on 2017/3/22.
 */
public class HTMLUtil {

    /**
     * 根据网页模板,填充网页
     *
     * @param title       网页标题
     * @param subTitle    子标题
     * @param createTime
     * @param source      网页来源
     * @param content     网页正文
     * @param templateUrl 模板页地址
     * @return
     */
    public static String getTemplateHTML(String title, String subTitle, Date createTime, String source, String content, String templateUrl) {
        //获取静态页路径,读入内存
        Document doc = null;
        try {
//            doc = Jsoup.connect(templateUrl).get();
            doc = Jsoup.parse(new URL(templateUrl).openStream(), "UTF-8", templateUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置标题
        Element eTitle = doc.getElementById("title");
        eTitle.text(title);
        //设置副标题
        Element eSubTitle = doc.getElementById("id");
        eSubTitle.text(subTitle);
        //设置时间
        Element eCreateTime = doc.getElementById("createtime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        eCreateTime.text(sdf.format(createTime));
        //设置来源
        Element eSource = doc.getElementById("from");
        eSource.text(source);
        //设置正文
        Element eContent = doc.getElementById("content");
        eContent.html(content);
        return doc.html();
    }

    /**
     * 根据赶集模板，填充赶集页面
     *
     * @param actName 标题
     * @param actBrief 简介
     * @param createTime 发布时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param detailAddress 详细地址
     * @param actType 赶集类型
     * @param actSponsor 赶集主办方
     * @param actLogo 赶集LOGO
     * @param actContent 赶集内容
     * @param templateUrl 模板地址
     * @return 填充
     */
    public static String getGanjiTemplateHTML(String actName, String actBrief, String createTime, String startTime, String endTime, String detailAddress,
                                              String actType, String actSponsor, String actLogo, String actContent, String templateUrl) {

        Document document = null;
        try {
            document = Jsoup.parse(new URL(templateUrl).openStream(), "UTF-8", templateUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置赶集名称
        Element eActName = document.getElementById("actName");
        eActName.text(actName);

        //设置赶集简介
        Element eActBrief = document.getElementById("actBrief");
        eActBrief.text(actBrief);

        //设置赶集发布时间
        Element eCreateTime = document.getElementById("createTime");
        eCreateTime.text(createTime);

        //设置赶集开始时间
        Element eStartTime = document.getElementById("startTime");
        eStartTime.text(startTime);

        //设置赶集结束时间
        Element eEndTime = document.getElementById("endTime");
        eEndTime.text(endTime);

        //设置赶集详细地址
        Element eDetailAddress = document.getElementById("detailAddress");
        eDetailAddress.text(detailAddress);

        //设置赶集赶集类型
        Element eActType = document.getElementById("actType");
        eActType.text(actType);

        //设置赶集主办方
        Element eActSponsor = document.getElementById("actSponsor");
        eActSponsor.text(actSponsor);

        //设置赶集LOGO
        Element eActLogo = document.getElementById("actLogo");
        eActLogo.attr("src",actLogo);

        //设置赶集内容
        Element eEctContent = document.getElementById("actContent");
        eEctContent.html(actContent);

        return document.html();
    }

}
