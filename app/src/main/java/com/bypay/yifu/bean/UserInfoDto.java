package com.bypay.yifu.bean;

import java.io.Serializable;

public class UserInfoDto implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String userName;
  private String userPassword;
  private String puthId;
  private String terminalId;
  private String terminalInfo;

  private String merchantId;
  private String jSessionId;

  private String sign;

  private String rspCode;
  private String rspDesc;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getPuthId() {
    return puthId;
  }

  public void setPuthId(String puthId) {
    this.puthId = puthId;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public String getTerminalInfo() {
    return terminalInfo;
  }

  public void setTerminalInfo(String terminalInfo) {
    this.terminalInfo = terminalInfo;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public String getjSessionId() {
    return jSessionId;
  }

  public void setjSessionId(String jSessionId) {
    this.jSessionId = jSessionId;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getRspCode() {
    return rspCode;
  }

  public void setRspCode(String rspCode) {
    this.rspCode = rspCode;
  }

  public String getRspDesc() {
    return rspDesc;
  }

  public void setRspDesc(String rspDesc) {
    this.rspDesc = rspDesc;
  }

}
