package com.rktuhinbd.sslsandbox;

import android.content.Context;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class PaymentGatewaySSLCommerz {

    private SSLCommerzInitialization sslCommerzInitialization;
    private SSLCCustomerInfoInitializer customerInfoInitializer;
    private SSLCProductInitializer productInitializer;

    // = = = Declare payment gateway data properties = = = //
    private Context context;
    private SSLCTransactionResponseListener listener;


    public PaymentGatewaySSLCommerz(Context context, SSLCTransactionResponseListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setSslCommerzInitialization(String storeId, String password, Integer amount,
                                            String transactionId, String productCategory) {
        sslCommerzInitialization = new SSLCommerzInitialization(storeId, password, amount,
                SSLCCurrencyType.BDT, transactionId, productCategory, SSLCSdkType.TESTBOX);
    }

    public void setCustomerInfoInitializer(String name, String email, String address, String city,
                                           String postCode, String country, String phoneNumber) {
        customerInfoInitializer = new SSLCCustomerInfoInitializer(name, email, address, city,
                postCode, country, phoneNumber);
    }

    public void setProductInitializer(String productName, String productCategory, String productProfile,
                                      String hotelName, String lengthOfStay, String checkInTime, String hotelCity) {
        productInitializer = new SSLCProductInitializer(productName, productCategory,
                new SSLCProductInitializer.ProductProfile.TravelVertical(productProfile, hotelName,
                        lengthOfStay, checkInTime, hotelCity));
    }

    public void integrateSSLCommerz() {
        IntegrateSSLCommerz
                .getInstance(context)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .addCustomerInfoInitializer(customerInfoInitializer)
                .addProductInitializer(productInitializer)
                .buildApiCall(listener);
    }
}
