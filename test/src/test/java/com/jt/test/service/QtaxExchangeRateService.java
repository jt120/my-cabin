package com.jt.test.service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @className:QtaxExchangeRateService.java
 * @classDescription:
 * @author:xing.zhao
 * @createTime:2013-8-14 下午3:40:32
 */
@Component
public class QtaxExchangeRateService {

    private static final Logger logger = LoggerFactory.getLogger(QtaxExchangeRateService.class);

    public int qrateUpRoleCalc(double num, int upRole) {
        return (int) (Math.ceil(num / upRole) * upRole);
    }

    public double[] getRet(Currency cny, double num) {
        double ret[] = {0, 0};
        if (cny != null) {
            double uprolePrice = (double) cny.getUprole_price();
            if (uprolePrice < 0) {
                uprolePrice = 1 / uprolePrice * -1;
            }
            ret[0] = Math.ceil(num / uprolePrice) * uprolePrice;
            ret[1] = cny.getUprole_price();
            logger.warn("Currency parseCurrency(response) fail! use cache currency[qroe="
                    + ret[0] + ";uprole=" + ret[1] + "]");
        } else {
            ret[0] = Math.ceil(num / 10) * 10;
            ret[1] = 10;
            logger.warn("Currency parseCurrency(response) fail! use default 10 uprole[qroe="
                    + ret[0] + "]");
            return ret;
        }
        return ret;
    }

    public Currency parseCurrency(String xsfscMsg) {
        Currency cny = new Currency();
//		FSC 100/USD/CNY/S/24APR12                                                       
//		RATE BSR 1USD=6.3254CNY                                                         
//		CNY          632.5 TRUNCATED                                                    
//		CNY            640 ROUNDED UP TO NEXT 10.00 - FARES                             
//		CNY            633 ROUNDED UP TO NEXT 1.00 - TAXES/OTHERS                       
//		RFSONLN/1E /DB1/PAGE 1/1

        try {
            Matcher matcher = Pattern.compile("=([\\d\\.]+)[A-Z]{3}").matcher(xsfscMsg);
            if (matcher.find()) {
                cny.setRate(Float.parseFloat(matcher.group(1)));
            }
            Matcher matcher2 = Pattern.compile("([\\d\\.]+)\\s*\\-\\s*FARES").matcher(xsfscMsg);
            if (matcher2.find()) {
                String uprole_fare_str = matcher2.group(1);
                double uprolefareDou = Double.parseDouble(uprole_fare_str);
                if (uprolefareDou < 1) {
                    cny.setUprole_price((int) (1 / uprolefareDou) * -1);
                } else {
                    cny.setUprole_price((int) uprolefareDou);
                }
                //cny.setUprole_price((int) (1/Double.parseDouble(uprole_fare_str.startsWith("0.")?"-"+Math.pow(10, uprole_fare_str.substring(uprole_fare_str.indexOf(".")+1).length()):uprole_fare_str));
            }
            Matcher matcher3 = Pattern.compile("([\\d\\.]+)\\s*\\-\\s*TAXES/OTHERS").matcher(xsfscMsg);
            if (matcher3.find()) {
                String uprole_tax_str = matcher3.group(1);
                double uproletaxDou = Double.parseDouble(uprole_tax_str);
                if (uproletaxDou < 1) {
                    cny.setUprole_tax((int) (1 / uproletaxDou) * -1);
                } else {
                    cny.setUprole_tax((int) uproletaxDou);
                }
                //int uprole_tax=(int) Double.parseDouble(uprole_tax_str.startsWith("0.")?"-"+Math.pow(10, uprole_tax_str.substring(uprole_tax_str.indexOf(".")+1).length()):uprole_tax_str);
            }
            cny.setUpdate_time(new Date());
        } catch (Exception e) {
            logger.info("Currency parseCurrency(String response):", e);
            return null;
        }

        return cny;
    }
    //
    //private Currency getCurrencyByPid(String mainCur, String changeCur, String officeId) {
    //    Currency pidCny = null;
    //    try {
    //        String xsfscMsg = qtaxIBE.xsfscMsg(mainCur, changeCur, officeId);
    //        pidCny = this.parseCurrency(xsfscMsg);
    //    } catch (Exception e) {
    //        logger.error("", e);
    //    }
    //    return pidCny;
    //}

}
