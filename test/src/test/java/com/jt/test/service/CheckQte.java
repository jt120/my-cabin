package com.jt.test.service;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ze.liu on 2014/9/19.
 */
public class CheckQte {

    private static final Logger logger = LoggerFactory.getLogger(CheckQte.class);

    private static Pattern WITHTAX_GT0 = Pattern.compile("(\\d{2})[ \\S]*[ ]+(\\d+)([ ]|[A-Z]{2,})*INCL TAX");
    private static Pattern WITHOUTTAX_EQ0 = Pattern.compile("^(\\d{2})[ \\s]{1,}[ \\S]*[ ]+(\\d+)([ ]|[A-Z]{2,})*", Pattern.MULTILINE);


    private static Pattern FSQWITHFARE = Pattern.compile("[\\s\\S]*^FARE[ \\s]+[\\s\\S]*?[ \\s]+\\d+[\\s\\S]*?$[\\s\\S]*", Pattern.MULTILINE);
    private static Pattern FSQWITHTAX = Pattern.compile("[\\s\\S]*^TAX[ \\s]+[\\s\\S]*?[ \\s]+[\\s\\S]*?$[\\s\\S]*", Pattern.MULTILINE);

    private QtaxExchangeRateService qtaxExchangeRateService = new QtaxExchangeRateService();

    /**
     * 此段逻辑已经废除，原因是税费为0时要计算q*roe
     *
     * @param xsfsqMsg
     * @param type
     * @return
     */
    @Deprecated
    public String getQTaxMsg(String xsfsqMsg, String type) {
        if (StringUtils.isBlank(xsfsqMsg)) {
            return null;
        }
        boolean hasFare = FSQWITHFARE.matcher(xsfsqMsg).matches();
        boolean hasTax = false;
        if (hasFare) { //有报价
            hasTax = FSQWITHTAX.matcher(xsfsqMsg).matches();
            if (!hasTax) { //没有Tax开头的记录就是没有税费
                return "0";
            } else {
                return xsfsqMsg;
            }
        } else {
            logger.info("can't find FSQ FARE info:" + xsfsqMsg);
        }
        return null;
    }

    public String getXFSQNum(String qte_or_xsfsi_Msg, List<String> totalfareList) {
        if (StringUtils.isBlank(qte_or_xsfsi_Msg) || !this.isValidQTaxCMDResp(qte_or_xsfsi_Msg)) {
            return "";
        }
        totalfareList.clear();
        Matcher taxGt0Matcher = WITHTAX_GT0.matcher(qte_or_xsfsi_Msg);
        String fsqNo = "";
        while (taxGt0Matcher.find()) {
            fsqNo = taxGt0Matcher.group(1);
            totalfareList.add(taxGt0Matcher.group(2));
        }
        if (StringUtils.isBlank(fsqNo)) {
            Matcher taxEq0Matcher = WITHOUTTAX_EQ0.matcher(qte_or_xsfsi_Msg);
            totalfareList.clear();
            while (taxEq0Matcher.find()) {
                fsqNo = taxEq0Matcher.group(1);
                totalfareList.add(taxEq0Matcher.group(2));
            }
        }
        return fsqNo;
    }

    public String getXFSQNumAdapterNofare(String qte_or_xsfsi_Msg, List<String> totalfareList) {
        boolean isNofare = StringUtils.isNotBlank(qte_or_xsfsi_Msg) && noFarePattern.matcher(qte_or_xsfsi_Msg).find();
        if (!isNofare && (StringUtils.isBlank(qte_or_xsfsi_Msg) || !this.isValidQTaxCMDResp(qte_or_xsfsi_Msg))) {
            return "";
        }
        totalfareList.clear();
        Matcher taxGt0Matcher = WITHTAX_GT0.matcher(qte_or_xsfsi_Msg);
        String fsqNo = "";
        while (taxGt0Matcher.find()) {
            fsqNo = taxGt0Matcher.group(1);
            totalfareList.add(taxGt0Matcher.group(2));
        }
        if (StringUtils.isBlank(fsqNo)) {
            Matcher taxEq0Matcher = WITHOUTTAX_EQ0.matcher(qte_or_xsfsi_Msg);
            totalfareList.clear();
            while (taxEq0Matcher.find()) {
                fsqNo = taxEq0Matcher.group(1);
                totalfareList.add(taxEq0Matcher.group(2));
            }
        }
        return fsqNo;
    }

    public int parseQfeeByxfsqMsg(String xfsqMsg, Map<String, String> allCityCodeMap, Set<String> allCarriers) {
        double ret = 0;
        if (StringUtils.equals(xfsqMsg, "0")) {
            return 0;
        }
        if (StringUtils.equals(xfsqMsg, "-1") || StringUtils.isBlank(xfsqMsg) || !this.isValidQTaxCMDResp(xfsqMsg)) {
            return -1;
        }

        String taxStr = "";
        String other = "";

        Matcher m = Pattern.compile("FARE\\s+.*?\\nTAX([\\s\\S]*?)TOTAL([\\s\\S]*)").matcher(xfsqMsg);
        if (m.find()) {
            taxStr = m.group(1);
            other = m.group(2);
            if (logger.isInfoEnabled()) {
                logger.info("parseQFeeStr TAXSTR : " + taxStr);
            }
        }

		/* xing.zhao 2013-10-22
         * if(StringUtils.isBlank(taxStr)) {
			return 0;
		}*/
        // xing.zhao 2013-10-22 修改，junhui.du需求，要求没有税值的情况，计算q值*ROE值
        if (StringUtils.isBlank(taxStr)) {
            other = xfsqMsg;
        }
        if (!this.isValidXFSQMsg(other, allCarriers)) {
            return -1;
        }
        Matcher mTax = Pattern.compile("([\\d\\.]+)").matcher(taxStr);
        while (mTax.find()) {
            ret += Double.parseDouble(mTax.group(1));
        }
        other = other.replaceAll("b\\n", "");
        for (String city : allCityCodeMap.values()) {
            other = other.replaceAll(city, "");
        }
        if (allCityCodeMap.values().contains("PEK")) {
            other = other.replaceAll("BJS", "");
        }
        if (allCityCodeMap.values().contains("XIY")) {
            other = other.replaceAll("SIA", "");
        }
        for (String carrier : allCarriers) {
            other = other.replaceAll(carrier, "");
        }

        if (logger.isInfoEnabled()) {
            logger.info("parseQFeeStr QSTR : " + other);
        }

        // 解析Q值
        double q = numberParser(xfsqMsg, other, "Q");

        // 解析S值
        double s = numberParser(xfsqMsg, other, "S");
        q += s;

        double qroe[] = {};
        //浮点比较
        if (q > 0.001) {
            Matcher mROE = Pattern.compile("ROE([\\d\\.]+)").matcher(other);
            if (mROE.find()) {
                double roe = Double.parseDouble(mROE.group(1));
                q *= roe;
                if (logger.isInfoEnabled()) {
                    logger.info("parseQFeeStr roe : " + roe);
                }
            }

            Matcher mRatio = Pattern.compile("([A-Z]{3})\\s*=([\\d\\.]+)CNY").matcher(other);
            if (mRatio.find()) {
                //qroe = qtaxExchangeRateService.qroeUpRoleCalc(q, "USD", mRatio.group(1), Constant.qtax_officeid);
                if (logger.isInfoEnabled()) {
                    logger.info("parseQFeeStr calc qroe: " + qroe[0] + ";upRole :" + qroe[1]);
                }
                double ratio = Double.parseDouble(mRatio.group(2));
                ret += qtaxExchangeRateService.qrateUpRoleCalc(qroe[0] * ratio, 10);
                if (logger.isInfoEnabled()) {
                    logger.info("parseQFeeStr ratio : " + ratio);
                }
            } else {
                ret += qtaxExchangeRateService.qrateUpRoleCalc(q, 10);
            }

        }
        if (logger.isInfoEnabled()) {
            logger.info("parseQFeeStr final ret: " + ret);
        }
        return (int) ret;
    }

    /**
     * 解析金额
     *
     * @param xfsqMsg
     * @param other
     * @param key     "Q"：解析Q值, "S"：解析S值
     * @return
     */
    private double numberParser(String xfsqMsg, String other, String key) {
        double result = 0;

        try {
            other = "#" + other.replaceAll("([^A-Z])" + key, "$1#" + key);

            Matcher mQ = Pattern.compile("[^A-Z]" + key + "\\s*(\\d+(\\.\\d{0,2})?)").matcher(other);
            while (mQ.find()) {
                String qvalue = mQ.group(1);
                if (qvalue.indexOf(".") == -1) {//报警邮件，q值不能为整数
                    if (!"S".equals(key)) {
                    }
                } else {
                    result += Double.parseDouble(qvalue);
                    if (logger.isInfoEnabled()) {
                        logger.info("parseQFeeStr " + key + " : " + result);
                        logger.info("S xsfsq is :" + xfsqMsg);
                        if ("S".equals(key)) {
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("parse xsfsq" + key + "error", e);
        }

        return result;
    }


    /**
     * 验证qte指令返回报文，内容错误
     *
     * @param qtaxInfo
     * @return
     */
    private boolean isValidQTaxCMDResp(String qtaxInfo) {
        if (StringUtils.isNotBlank(qtaxInfo)) {
            List<String> errMsg = new ArrayList<String>();
            errMsg.add("EXCEPTION: COULD NOT CONNECT TO HOST");
            errMsg.add("EFEP ROUTE NAME NOT FOUND");
            errMsg.add("PAGING REQUEST DATA NOT FOUND");
            errMsg.add("RETRANSMIT");
            errMsg.add("NO FARES/RBD/CARRIER");
            errMsg.add("EXCEPTION: COULD NOT READ FROM SOCKET");
            errMsg.add("SESSION CURRENTLY LOCKED");
            errMsg.add("FARE OFFER NOT FOUND");
            for (String err : errMsg) {
                if (qtaxInfo.contains(err)) {
                    logger.info("qte switch status is open, but qte info is error");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证报文是否有窜税现象
     *
     * @param xfsqMsg
     * @param airlineCodes
     * @return
     */
    private boolean isValidXFSQMsg(String xfsqMsg, Set<String> airlineCodes) {
        boolean rs = true;
        xfsqMsg = StringUtils.replace(xfsqMsg, "<", " ");
        for (String airCode : airlineCodes) {
            String rule = StringUtils.upperCase(airCode) + " ";
            rs &= (xfsqMsg.contains(rule));  //与操作,只要有一个航空公司没有,验证失败
        }
        if (!rs) {
            logger.error("可能出现指令串税" + xfsqMsg + "\n airlineCodes:" + airlineCodes.toString());
        }
        return rs;
    }

    private static Pattern noFarePattern = Pattern.compile(
            "\\*NO\\s+FARES/RBD/CARRIER", Pattern.CASE_INSENSITIVE);

    /**
     * copy自parseQfeeByxfsqMsg，修改了部分逻辑，isNofare的时候，跳过一些检查
     *
     * @param xsfsqMsg
     * @param allCityCodeMap
     * @param allCarriers
     * @return
     * @author lingzhen.kong
     */
    public int parseQfeeByxfsqMsgAdapterNofare(String xsfsqMsg) {
        double ret = 0;
        if (StringUtils.equals(xsfsqMsg, "0")) {
            return 0;
        }
        boolean isNofare = StringUtils.isNotBlank(xsfsqMsg) && noFarePattern.matcher(xsfsqMsg).find();
        if (!isNofare && (StringUtils.equals(xsfsqMsg, "-1") || StringUtils.isBlank(xsfsqMsg)
                || !this.isValidQTaxCMDResp(xsfsqMsg))) {
            return -1;
        }
        if (isNofare && logger.isInfoEnabled()) {
            logger.info("the xsfsqMsg is no fare");
        } else {
            logger.info("the xsfsqMsg is not no fare");
        }
        String taxStr = "";
        String other = "";

        Matcher m = Pattern.compile(
                "FARE\\s+.*?\\nTAX([\\s\\S]*?)TOTAL([\\s\\S]*)").matcher(
                xsfsqMsg);
        if (m.find()) {
            taxStr = m.group(1);
            other = m.group(2);
            if (logger.isInfoEnabled()) {
                logger.info("parseQFeeStr TAXSTR : " + taxStr);
            }
        }

		/*
		 * xing.zhao 2013-10-22 if(StringUtils.isBlank(taxStr)) { return 0; }
		 */
        // xing.zhao 2013-10-22 修改，junhui.du需求，要求没有税值的情况，计算q值*ROE值
        if (StringUtils.isBlank(taxStr)) {
            other = xsfsqMsg;
        }

        Matcher mTax = Pattern.compile("([\\d\\.]+)").matcher(taxStr);
        while (mTax.find()) {
            ret += Double.parseDouble(mTax.group(1));
        }
        other = other.replaceAll("b\\n", "");


        if (logger.isInfoEnabled()) {
            logger.info("parseQFeeStr QSTR : " + other);
        }

        // 解析Q值
        double q = numberParser(xsfsqMsg, other, "Q");

        // 解析S值
        double s = numberParser(xsfsqMsg, other, "S");
        q += s;

        double qroe[] = {};
        // 浮点比较
        if (q > 0.001) {
            Matcher mROE = Pattern.compile("ROE([\\d\\.]+)").matcher(other);
            if (mROE.find()) {
                double roe = Double.parseDouble(mROE.group(1));
                q *= roe;
                if (logger.isInfoEnabled()) {
                    logger.info("parseQFeeStr roe : " + roe);
                }
            }

            Matcher mRatio = Pattern.compile("([A-Z]{3})\\s*=([\\d\\.]+)CNY")
                    .matcher(other);
            if (mRatio.find()) {
                //qroe = qtaxExchangeRateService.qroeUpRoleCalc(q, "USD", mRatio.group(1), Constant.qtax_officeid);
                if (logger.isInfoEnabled()) {
                    logger.info("parseQFeeStr calc qroe: " + qroe[0]
                            + ";upRole :" + qroe[1]);
                }
                double ratio = Double.parseDouble(mRatio.group(2));
                ret += qtaxExchangeRateService.qrateUpRoleCalc(qroe[0] * ratio,
                        10);
                if (logger.isInfoEnabled()) {
                    logger.info("parseQFeeStr ratio : " + ratio);
                }
            } else {
                ret += qtaxExchangeRateService.qrateUpRoleCalc(q, 10);
            }

        }
        if (logger.isInfoEnabled()) {
            logger.info("parseQFeeStr final ret: " + ret);
        }
        return (int) ret;
    }

    @Test
    public void test01() {

        String s = "XSIN YIF             NVB      NVA29SEP 30K  ESCb\n" +
                " PAR YIF             NVB      NVA29SEP 30K  ESCb\n" +
                "FARE  CNY   28450   ESCb\n" +
                "TAX   CNY     90CN CNY     15OO CNY   1719XTESCb\n" +
                "TOTAL CNY   30274   ESCb\n" +
                "29SEP14CAN SQ X/SIN SQ PAR10M4555.36NUC4555.36END ROE6.24450ESCb\n" +
                "0   ESCb\n" +
                "XT CNY 44SG CNY 1675YQ  ESCb\n" +
                "*AUTO BAGGAGE INFORMATION AVAILABLE - SEE FSB   ESCb\n" +
                "RFSONLN/1E /EFEP_11/FCC=T/  ESCb\n" +
                "^^ESCb^C";

        int n = parseQfeeByxfsqMsgAdapterNofare(s);
        System.out.println(n);

    }

    @Test
    public void test2() {
        String taxStr = "TAX   CNY     90CN CNY     15OO CNY   1719XTESCb";
        Matcher mTax = Pattern.compile("([\\d\\.]+)").matcher(taxStr);
        double ret = 0;
        while (mTax.find()) {
            System.out.println(mTax.group(1));
            ret += Double.parseDouble(mTax.group(1));
        }
        System.out.println(ret);
    }

}