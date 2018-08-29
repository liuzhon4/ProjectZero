package demo.projectZero;

public class MerchantDisplayInfo {
    private String merchantLicenseNum = "";
    private String merchantName = "";
    private String merchantAddress = "";
    private Integer index = 0;

    public void setMerchantLicenseNum(String licenseNum) {
        this.merchantLicenseNum = licenseNum;
    }

    public void setMerchantName(String name) {
        this.merchantName = name;
    }

    public void setMerchantAddress(String address) {
        this.merchantAddress = address;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getMerchantLicenseNum() {
        return this.merchantLicenseNum;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getMerchantAddress() {
        return this.merchantAddress;
    }

    public Integer getIndex() {
        return this.index;
    }
}
